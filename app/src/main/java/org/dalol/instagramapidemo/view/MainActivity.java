package org.dalol.instagramapidemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import org.dalol.instagramapidemo.R;
import org.dalol.instagramapidemo.model.Constants;
import org.dalol.instagramapidemo.model.adapter.RecyclerGridAdapter;
import org.dalol.instagramapidemo.model.pojo.Image;
import org.dalol.instagramapidemo.model.pojo.TagResponse;
import org.dalol.instagramapidemo.model.pojo.TokenResponse;
import org.dalol.instagramapidemo.services.ServiceManager;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mLayoutManager;
    private RecyclerGridAdapter mAdapter;
    private SearchView mSearchView;
    private MenuItem mSearchMenuItem;
    private TokenResponse mTokenResponse;
    private boolean loading;
    private int visibleThreshold = 5;
    private int visibleItemCount;
    private int pastVisibleItems;
    private String mMaxId, mMinId;
    private String mQuery;

    @Override
    protected void configViews(Intent intent, Bundle savedInstanceState) {

        if (intent != null) {
            mTokenResponse = (TokenResponse) intent.getSerializableExtra(Constants.TOKEN_RESPONSE);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayoutManager = new StaggeredGridLayoutManager(Constants.VR_SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new RecyclerGridAdapter();

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = mLayoutManager.getChildCount();
                //totalItemCount = mLayoutManager.getItemCount();

                int[] firstVisibleItems = null;
                firstVisibleItems = mLayoutManager.findFirstVisibleItemPositions(firstVisibleItems);
                if (firstVisibleItems != null && firstVisibleItems.length > 0) {
                    pastVisibleItems = firstVisibleItems[0];
                }

                if (!loading && visibleItemCount <= (pastVisibleItems + visibleThreshold)) {
                    // End has been reached
                    // Do something

                    Log.d(TAG, "onLoadMoreListener ");

                    getTagResults(mQuery, mMinId, mMaxId);

//                    if (onLoadMoreListener != null) {
//                        onLoadMoreListener.onLoadMore();
//                    }


                    loading = true;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        mSearchMenuItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) mSearchMenuItem.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

//                UserFeedback.show( "SearchOnQueryTextSubmit: " + query);
                mQuery = query;
                getTagResults(query, "", "");
                mAdapter.reset();
                if (!mSearchView.isIconified()) {
                    mSearchView.setIconified(true);
                }
                mSearchMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        return true;
    }

    private void getTagResults(String query, String minId, String maxId) {

        String token = "\\u003d" + mTokenResponse.getAccess_token();

        Call<ResponseBody> response = ServiceManager.createService().getResponse(query, "273700138.f359f2c.f6d5dd3e37704cb8b976331d56706931", minId, maxId);
        response.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccess()) {

                    StringBuilder sb = new StringBuilder();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));
                        String line;

                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }

                        JSONObject tagResponse = new JSONObject(sb.toString());

                        for (int i = 0; i < tagResponse.length(); i++) {
                            JSONObject pagination = tagResponse.getJSONObject("pagination");

                            mMaxId = pagination.getString("next_max_id");
                            mMinId = pagination.getString("next_min_id");

                            JSONObject meta = tagResponse.getJSONObject("meta");
                            JSONArray data = tagResponse.getJSONArray("data");

                            for (int j = 0; j < data.length(); j++) {

                                JSONArray tags = data.getJSONObject(j).getJSONArray("tags");


                                JSONObject images = data.getJSONObject(j).getJSONObject("images").getJSONObject("low_resolution");

                                TagResponse tr = new TagResponse();

                                Image image = new Image();
                                image.setUrl(images.getString("url"));
                                image.setHeight(images.getInt("width"));
                                image.setWidth(images.getInt("height"));

                                String[] tagT = new String[tags.length()];
                                for (int z = 0; z < tags.length(); z++) {
                                    tagT[z] = (String) tags.get(i);
                                }

                                image.setTags(tagT);

                                mAdapter.addImage(image);

                                tr.setImage(image);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Log.d(TAG, "CallonResponse isSuccess " + sb.toString() + " ----- ");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "CallonResponse onFailure! " + t.getMessage());
                t.printStackTrace();
            }
        });


//        response.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<Response> response) {
//
//
//
//
////                StringBuilder sb = new StringBuilder();
////                try {
////
////                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.body().));
////
////                    String line;
////
////                    try {
////                        while ((line = reader.readLine()) != null) {
////                            sb.append(line);
////                        }
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//                Log.d(TAG, "onResponse onFailure! " + t.getMessage());
//                t.printStackTrace();
//            }
//        });
//        response.enqueue(new Callback<FeedResponse>() {
//            @Override
//            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
//                if (response.isSuccess()) {
//
//                    FeedResponse feedResponse = response.body();
//                    Log.d(TAG, "onResponse isSuccess " + feedResponse.getMeta().getCode() + " ----- " + feedResponse.getPagination().getNext_max_id() + " :: " + feedResponse.getPagination().getNext_url());
//                }
//                Log.d(TAG, "onResponse success!");
//            }
//
//            @Override
//            public void onFailure(Call<FeedResponse> call, Throwable t) {
//                Log.d(TAG, "onResponse onFailure! " + t.getMessage());
//                t.printStackTrace();
//            }
//        });
    }

    //
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//

//
//        //AuthenticationDialog dialog = new AuthenticationDialog(MainActivity.this, this);
//       //dialog.show();
//    }

    @Override
    protected Toolbar getToolBarView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Instagram Autorization");
        return mToolbar;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

//    @Override
//    public void onCodeReceived(String code) {
//
//        TokenRequest request = new TokenRequest();
//        request.setClient_id(Constants.CLIENT_ID);
//        request.setClient_secret(Constants.CLIENT_SECRET);
//        request.setGrant_type(Constants.AUTORISATION_CODE);
//        request.setRedirect_uri(Constants.REDIRECT_URI);
//        request.setCode(code);
//
//
//    }

    @Override
    public void onClick(View v) {

    }
}
