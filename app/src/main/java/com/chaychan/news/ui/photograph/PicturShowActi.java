package com.chaychan.news.ui.photograph;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chaychan.news.R;
import com.chaychan.news.model.entity.ShowPhotoItem;
import com.chaychan.news.ui.base.BaseActivity;
import com.chaychan.news.ui.presenter.ShowPhotoListPresenter;
import com.chaychan.news.utils.UIUtils;
import com.chaychan.news.view.lShowPhotoListView;
import com.chaychan.news.view.showbigpic.HackyViewPager;
import com.chaychan.news.view.showbigpic.LodingTouchImageView;
import com.chaychan.news.view.showbigpic.PictureShowParentView;


import java.util.ArrayList;
import java.util.List;

import flyn.Eyes;

/**
 * 不带文案的大图页展示
 *
 * @author anzh
 * @since 2015-03-11
 */
@SuppressLint("NewApi")
public class PicturShowActi extends BaseActivity<ShowPhotoListPresenter> implements lShowPhotoListView, OnClickListener{

    public final static int LOCAL = 1;//本地数据
    public final static int NETWORK_BBS = 2;//论坛

    private ArrayList<String> imageurls;

    /**
     * 屏幕宽度
     */
    private int screenWidth;
    private int position = 0;
    private View donwImage;
    //图片大小
    String size;

    //bbs参数
    private String bbs;
    private String boardid;
    private String bookid;

    /**
     * 来源
     */
    private int mType;
    private HackyViewPager mViewPager;
    private TextView indexView, total;
    private int ind;

    /**
     * adapter
     */
    private ImageLoadPagerAdapter pagerAdapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.imageviewpagelayout;
    }

    @Override
    public void initView() {
        super.initView();
        screenWidth = UIUtils.getResolution()[0];
        setContentView(R.layout.imageviewpagelayout);
        Bundle b = getIntent().getExtras();
        mType = b.getInt("type");
        if (mType != LOCAL && savedInstanceState != null) {
            ind = savedInstanceState.getInt("seindex");
            position = ind;
        }

        String tempOption = b.getString("position");
        if (!TextUtils.isEmpty(tempOption)) {
            try {
                position = Integer.parseInt(tempOption.trim());
            } catch (Exception e) {
                position = 0;
            }
        }
        resolveIntent(b);

        Eyes.setStatusBarColor(this, UIUtils.getColor(R.color.black));//设置状态栏的颜色为灰色
        final RelativeLayout rl_pic_bg = (RelativeLayout) findViewById(R.id.rl_pic_bg);
        rl_pic_bg.setBackgroundColor(getResources().getColor(R.color.black));

        donwImage = findViewById(R.id.imageviewpage_download);
        mViewPager = (HackyViewPager) findViewById(R.id.imgeviewpages);
        //点击事件-点击图片关闭图片展示窗体
        donwImage.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                downLoad();
            }
        });
        indexView = (TextView) findViewById(R.id.pageindex);
        total = (TextView) findViewById(R.id.total);
        imageurls = new ArrayList<String>();

        mViewPager.setOnPageChangeListener(new PageChangeListener());

        final View otherLayout = this.findViewById(R.id.other_layout);

        PictureShowParentView root = (PictureShowParentView) this.findViewById(R.id.picture_move_layout);
        root.setFinishListener(new PictureShowParentView.IFinishListener() {
            @Override
            public void finish() {
                PicturShowActi.this.finish();
            }
        });
        root.setMoveAlphaListener(new PictureShowParentView.IMoveAlphaListener() {
            @Override
            public void alpha(float otherAlpha, float bgAlpha) {
                otherLayout.setAlpha(otherAlpha);

                rl_pic_bg.getBackground().setAlpha((int) (bgAlpha * 255));
            }
        });

        root.setImageScaleListener(new PictureShowParentView.IImageScaleListener() {
            @Override
            public boolean scale() {
                boolean flag = false;

                LodingTouchImageView view = this.getLodingTouchImageView();
                if (null != view) {
                    Matrix displayMatrix = view.getImageView().getDisplayMatrix();
                    float[] displayValues = new float[9];
                    displayMatrix.getValues(displayValues);

                    Matrix imageMatrix = view.getImageView().getImageMatrix();
                    float[] imageValues = new float[9];
                    imageMatrix.getValues(imageValues);

                    if (displayValues[0] != imageValues[0]) {
                        flag = true;
                    }
                }

                return flag;
            }

            @Override
            public int getImgHeight() {
                LodingTouchImageView view = this.getLodingTouchImageView();

                int height = 0;
                if (null != view
                        && null != view.getImageView()
                        && null != view.getImageView().getDrawable()) {
                    height = view.getImageView().getDrawable().getIntrinsicHeight();
                }

                return height;
            }

            private LodingTouchImageView getLodingTouchImageView() {
                LodingTouchImageView view = null;

                int currentItem = mViewPager.getCurrentItem();
                View tempView = mViewPager.findViewWithTag(Integer.valueOf(currentItem));
                if (null != tempView
                        && tempView instanceof LodingTouchImageView) {
                    view = (LodingTouchImageView) tempView;
                }

                return view;
            }
        });
    }

    @Override
    public void initListener() {
        super.initListener();
    }


    @Override
    protected ShowPhotoListPresenter createPresenter() {
        return new ShowPhotoListPresenter(this);
    }

    @Override
    public void onGetShowPhotoListSuccess(List<ShowPhotoItem> showPhotoList, String tipInfo) {
        if(showPhotoList!=null && showPhotoList.size()>0){
            ArrayList<String> list = new ArrayList<>();
            for(ShowPhotoItem showPhotoItem:showPhotoList){
                list.add(showPhotoItem.getImageUrl());
            }
            if(list!=null && list.size()>0){
                imageurls = list;
                mViewPager.setOnPageChangeListener(new PageChangeListener());
                donwImage.setVisibility(View.VISIBLE);
                indexView.setText(position + 1 + "");

                //FIXME
                setAdapter();
                resetTotalPageNum();
            }
        }
    }

    @Override
    public void onError() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        outState.putInt("seindex", position);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 解析intent数据
     *
     * @param bundle
     */
    private void resolveIntent(Bundle bundle) {
        switch (mType) {
            case LOCAL:
                imageurls = bundle.getStringArrayList("imageList");
                setAdapter();
                donwImage.setVisibility(View.VISIBLE);
                indexView.setText((1 + position) + "");
                total.setText("/" + imageurls.size());
                break;
            case NETWORK_BBS:
                boardid = bundle.getString("boardid");
                bookid = bundle.getString("bookid");
                bbs = bundle.getString("bbs");
                mPresenter.getShowPhotoList(bbs, boardid, bookid);
                break;
        }
    }

    private void setAdapter() {
        this.pagerAdapter = new ImageLoadPagerAdapter(this, imageurls, screenWidth, size, ImageLoadPagerAdapter.PICTUR);
        this.pagerAdapter.setMyOnClickListener(new ImageLoadPagerAdapter.MyViewOnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        this.pagerAdapter.setMyViewOnTapClickListener(new ImageLoadPagerAdapter.MyViewOnTapClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(position);
    }

    /**
     * 下载
     */
    private void downLoad() {
//        if (imageurls != null && imageurls.size() > 0 && imageurls.size() > position) {
//            String url = imageurls.get(position);
//            PicShowUtil.downlaod(url, this);
//        } else {
//            Toast.makeText(PicturShowActi.this, getString(R.string.picshow_no_data), Toast.LENGTH_SHORT).show();
//        }
    }


    private void resetTotalPageNum() {
        if (this.imageurls != null && !this.imageurls.isEmpty()) {
            this.total.setText("/" + this.imageurls.size());
        }
    }

    class PageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int p) {
            position = p;
            indexView.setText(p + 1 + "");
        }
    }
}
