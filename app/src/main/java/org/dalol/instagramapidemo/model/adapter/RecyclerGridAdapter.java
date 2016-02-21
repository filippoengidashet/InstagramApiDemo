package org.dalol.instagramapidemo.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.dalol.instagramapidemo.R;
import org.dalol.instagramapidemo.model.pojo.Image;
import org.dalol.instagramapidemo.view.SquareImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/21/2016
 */
public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerGridAdapter.Holder> {

    private static final int ITEM_TYPE_TAG = 0;
    private static final int ITEM_TYPE_MORE = 1;
    private List<Image> mImageList;

    public RecyclerGridAdapter() {
        mImageList = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        return mImageList.get(position) != null ? ITEM_TYPE_TAG : ITEM_TYPE_MORE;
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_TAG) {
            return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false));
        }
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        if (getItemViewType(position) == ITEM_TYPE_TAG) {
            Image image = mImageList.get(position);
            holder.mTagDescription.setText(image.toString());
            SquareImageView tagImage = holder.mTagImage;
            //tagImage.getLayoutParams().width = image.getWidth();
            //tagImage.getLayoutParams().height = image.getHeight();
            Glide.with(holder.itemView.getContext()).load(image.getUrl()).placeholder(R.mipmap.ic_launcher).into(holder.mTagImage);
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public void addImage(Image image) {
        mImageList.add(image);
        notifyDataSetChanged();
    }

    public void reset() {
        mImageList.clear();
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private SquareImageView mTagImage;
        private TextView mTagDescription;

        public Holder(View itemView) {
            super(itemView);
            mTagImage = (SquareImageView) itemView.findViewById(R.id.tagImage);
            mTagDescription = (TextView) itemView.findViewById(R.id.tagDescription);
        }
    }
}
