package com.chaychan.news.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chaychan.news.R;
import com.chaychan.news.utils.GlideUtils;
import com.chaychan.news.utils.UIUtils;

import java.util.List;

/**
 * @created at 2016/9/18 09:00
 */
public class BBSListImageAdapter extends BaseAdapter{
    private Context mContext;

    private List<String> mCount;
    public BBSListImageAdapter(Context context){
        mContext = context;
    }
    public void updateData(List<String> count) {
        mCount = count;
    }

    @Override
    public int getCount() {
        if (mCount == null) {
            return 0;
        }
        return mCount.size();
    }

    @Override
    public Object getItem(int position) {
        return mCount.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bbs_main_list_item_image_four, parent, false);
            holder.itemImage = (ImageView) convertView.findViewById(R.id.image);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) holder.itemImage.getLayoutParams();
//            if (mCount.size() == 4) {
//                int size = 278 * Constants.screenWidth / 720;
//                layoutParams.height =size;
//            } else {
                int size = 184 * UIUtils.getResolution()[0] / 720;
                layoutParams.height =size;
//            }
            holder.itemImage.setLayoutParams(layoutParams);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        Glide.with(mContext).load(mCount.get(position)).override(184,184)
//                .thumbnail(0.1f).placeholder(R.drawable.pdplaceholder)
//                .dontAnimate().error(R.drawable.pdplaceholder)
//                .diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.itemImage);
        GlideUtils.load(mContext, mCount.get(position), holder.itemImage);


        return convertView;
    }

    static class ViewHolder{
        ImageView itemImage;
    }
}
