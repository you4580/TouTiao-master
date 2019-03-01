package com.chaychan.news.view.showbigpic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.chaychan.news.R;
import com.chaychan.news.utils.GlideUtils;
import com.chaychan.news.utils.UIUtils;
import com.chaychan.news.view.extral.AdProductModel;
import com.chaychan.news.view.extral.AdProductView;
import com.chaychan.news.view.photoview.PhotoView;
import com.chaychan.news.view.photoview.PhotoViewAttacher;

import java.util.List;

public class LodingTouchImageView extends RelativeLayout {
    protected Context mContext;
    protected ProgressBar mProgressBar;
    protected PhotoView mImageView;
    protected ImageView progress;
    private PhotoViewAttacher mAttacher;
    /** 广告*/
    private AdProductView adProductView;

    private int resId ;//站位图id值
    /** 广告数据*/
    private List<AdProductModel> listAd;

    private MyOnClickListener mListener;
    private MyOnViewTapClickListener mViewTapListener;
    public LodingTouchImageView(Context ctx,int resId)
    {
        super(ctx);
        mContext = ctx;
        this.resId = resId ;
        init();

    }
    public LodingTouchImageView(Context ctx, AttributeSet attrs)
    {
        super(ctx, attrs);
        mContext = ctx;
        init();
    }
    public PhotoView getImageView() { return mImageView; }

    protected void init() {
        LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        progress = new ImageView(mContext) ;
        progress.setLayoutParams(params) ;
        try {
            progress.setBackgroundResource(resId);
        }catch (Exception e){

        }
        progress.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(v);
                }
            }
        });
        this.addView(progress);

        int id = Integer.parseInt("1");
        mImageView = new PhotoView(mContext);
        mImageView.setId(id);
        mAttacher = new PhotoViewAttacher(mImageView,mContext);
        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

            @Override
            public void onPhotoTap(View view, float x, float y) {
                if (mListener != null) {
                    mListener.onClick(view);
                }
            }
        });
        mAttacher.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                if (mViewTapListener != null) {
                    mViewTapListener.onClick(view);
                }
            }
        });
        mAttacher.setOnViewTouchListener(new PhotoViewAttacher.OnViewTouchListener() {
            @Override
            public void onTouch(MotionEvent event) {
                //FIXME v6.4.4版本应产品需要暂时去掉，点击页面隐藏广告功能
                //hideAdView();
            }
        });
        mImageView.setLayoutParams(params);
        mImageView.setVisibility(View.GONE);
        this.addView(mImageView);

        //广告
        this.adProductView = new AdProductView(this.getContext());
        this.adProductView.setVisibility(View.GONE);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.BELOW, id);
        this.addView(adProductView);
    }

    /**
     * 设置参数
     * @param imageUrl imgUrl
     */
    public void setUrl(String imageUrl){
        try {
            if(!TextUtils.isEmpty(imageUrl)){
                boolean isGif = imageUrl.endsWith(".gif") || imageUrl.endsWith(".gif.webp");
                if(isGif){
                    this.loadGif(imageUrl, this.createListener());
                } else {
                    this.loadJpg(imageUrl, this.createListener());
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setAdInfo(List<AdProductModel> listAd){
        this.listAd = listAd;
    }

    private void hideAdView(){
        if(null != this.adProductView){
            this.adProductView.setVisibility(View.GONE);
        }
    }

    private boolean hasAd(){
        boolean flag = false;

        if(null != this.adProductView
                && null != this.listAd
                && !this.listAd.isEmpty()
                && this.listAd.size() <= 2){
            flag = true;
        }

        return flag;
    }

    private void resetAdLocation(int imgHeight){
        int h = imgHeight / 2;
        int temp = UIUtils.getResolution()[1] / 2;
        if(temp > 0){
            int top = h + temp;
            float scale = 1.0075F;

            if(Build.MANUFACTURER.equalsIgnoreCase("nubia")
                    && Build.MODEL.equalsIgnoreCase("NX506J")){
                scale = 1.079F;

            } else if(Build.MANUFACTURER.equalsIgnoreCase("samsung")
                    && Build.MODEL.equalsIgnoreCase("SM-G9200")){
                scale = 1.081F;
            }

            top *= scale;

            RelativeLayout.LayoutParams layoutParams = (LayoutParams) this.adProductView.getLayoutParams();
            layoutParams.topMargin = top;
            this.adProductView.setLayoutParams(layoutParams);

            this.adProductView.setVisibility(View.VISIBLE);
        }
    }

    private IImageLoadListener createListener(){
        IImageLoadListener listener = new IImageLoadListener(){

            @Override
            public void resultJpg(Bitmap bit) {
                mImageView.setVisibility(View.VISIBLE);
                mImageView.setImageBitmap(bit);

                this.setImgAndAd();
            }

            @Override
            public void resultGif(Drawable d) {
                this.setImgAndAd();
            }

            private void setImgAndAd(){
                mImageView.setVisibility(View.VISIBLE);

                Drawable tempD = mImageView.getDrawable();
                if (null != tempD && hasAd()) {
                    int h = tempD.getIntrinsicHeight();
                    resetAdLocation(h);
                }
            }
        };

        return listener;
    }

    private void loadJpg(String imageUrl, final IImageLoadListener listener){
        if(!TextUtils.isEmpty(imageUrl)){
            RequestOptions myOptions = new RequestOptions().fitCenter().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
            this.mImageView.setVisibility(View.VISIBLE);
            Glide.with(this.getContext())
                    .load(imageUrl)
                    .apply(myOptions)
                    .into(mImageView);
//                    .asBitmap()
//                    .fitCenter()
//                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
//                            if (null != listener
//                                    && null != bitmap) {
//                                listener.resultJpg(resource);
//                            }
//                        }
//                    });
        }
    }

    private void loadGif(String url, final IImageLoadListener listener){
        if(!TextUtils.isEmpty(url)){
            //FIXME
            this.mImageView.setVisibility(View.INVISIBLE);

//            Glide.with(this.getContext())
//                    .load(url)
//                    .asGif()
//                    .fitCenter()
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//                    .listener(new RequestListener<String, GifDrawable>() {
//                        @Override
//                        public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                            if (null != listener
//                                    && null != resource) {
//                                listener.resultGif(resource);
//                            }
//
//                            return false;
//                        }
//                    })
//                    .into(mImageView);
        }
    }

    public void setMyOnClickListener(MyOnClickListener listener){
        this.mListener = listener;
    }

    public void setMyOnViewTapListener(MyOnViewTapClickListener viewTapListener) {
        this.mViewTapListener = viewTapListener;
    }

    public interface MyOnClickListener{
        void onClick(View v);
    }

    public interface MyOnViewTapClickListener{
        void onClick(View v);
    }

    private interface IImageLoadListener{
        void resultJpg(Bitmap bit);
        void resultGif(Drawable d);
    }
}
