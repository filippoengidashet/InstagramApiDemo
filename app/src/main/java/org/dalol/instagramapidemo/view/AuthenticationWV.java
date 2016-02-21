package org.dalol.instagramapidemo.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.dalol.instagramapidemo.R;
import org.dalol.instagramapidemo.model.Constants;
import org.dalol.instagramapidemo.model.pojo.TokenResponse;
import org.dalol.instagramapidemo.services.GetTokenService;
import org.dalol.instagramapidemo.services.ServiceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/20/2016
 */
public class AuthenticationWV extends BaseActivity {

    private static final String TAG = AuthenticationWV.class.getSimpleName();

    private WebView mAuthenticationView;
    private Toolbar mToolbar;
    private String mUrl;
    private ProgressDialog mLoadingSpinner;

    @Override
    protected Toolbar getToolBarView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Instagram Autorization");
        return mToolbar;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_authentication;
    }

    @Override
    protected void configViews(Intent intent, Bundle savedInstanceState) {

        mUrl = "https://www.instagram.com/oauth/authorize/?client_id=" + Constants.CLIENT_ID
                + "&redirect_uri=" + Constants.REDIRECT_URI + "&response_type=code&scope=basic+public_content+follower_list+comments+relationships+likes";

        mLoadingSpinner = new ProgressDialog(AuthenticationWV.this);
        mLoadingSpinner.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mLoadingSpinner.setMessage("Getting authentication code....");

        mAuthenticationView = (WebView) findViewById(R.id.webView);
        mAuthenticationView.setVerticalScrollBarEnabled(false);
        mAuthenticationView.setHorizontalScrollBarEnabled(false);
        mAuthenticationView.setWebViewClient(new AuthenticationClient());
        mAuthenticationView.getSettings().setJavaScriptEnabled(true);
        mAuthenticationView.loadUrl(mUrl);
    }

    private class AuthenticationClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            if (url.startsWith(Constants.REDIRECT_URI)) {
                String urls[] = url.split("=");

                mLoadingSpinner.setMessage("Getting Access Token....");

                String code = urls[2];

                GetTokenService tokenService = ServiceManager.createTokenService();
                Call<TokenResponse> accessToken = tokenService.getAccessToken(Constants.CLIENT_ID, Constants.CLIENT_SECRET,
                        Constants.REDIRECT_URI, Constants.AUTORISATION_CODE, code);

                accessToken.enqueue(new Callback<TokenResponse>() {
                    @Override
                    public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                        if (response.isSuccess()) {
                            mLoadingSpinner.setMessage("Got Access Token successfully!");
                            TokenResponse tokenResponse = response.body();
                            Intent intent = new Intent(AuthenticationWV.this, MainActivity.class);
                            intent.putExtra(Constants.TOKEN_RESPONSE, tokenResponse);
                            startActivity(intent);
                            finish();
                            Log.d(TAG, tokenResponse.toString());
                        }
                        mLoadingSpinner.dismiss();
                    }

                    @Override
                    public void onFailure(Call<TokenResponse> call, Throwable t) {
                        mLoadingSpinner.setMessage("Unable to get Access Token!");
                        Log.d(TAG, "Failure " + t.getMessage());
                        t.printStackTrace();
                        mLoadingSpinner.dismiss();
                    }
                });
                return true;
            }
            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            mLoadingSpinner.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mLoadingSpinner.dismiss();
        }
    }
}
