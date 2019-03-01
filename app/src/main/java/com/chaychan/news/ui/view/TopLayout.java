package com.chaychan.news.ui.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chaychan.news.R;


/**
 * TopLayout
 * <p>
 */
public class TopLayout extends RelativeLayout {

    private final String TAG = "===TopLayout";

    /**
     * 顶部广告
     */
    private ImageView mTopAdImg;
    /**
     * 扫一扫
     */
    private View mSearchView;

    /**
     * View是否attached到window
     */
    private boolean isAttached;
    /**
     * 跳转的url
     */
    private String mClickUrl;
    /**
     * 广告id
     */
    private String mAdId;
    private long openTime;

    public TopLayout(Context context) {
        super(context);

        this.init(context);
    }

    public TopLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.init(context);

    }

    public TopLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TopLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        this.init(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        this.isAttached = true;

//        this.requestAdInfo();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        this.isAttached = false;
    }

    private void init(Context context) {
        this.initView(context);
//        this.setListener();
        openTime = System.currentTimeMillis();
    }

    private void initView(Context context) {
        inflate(context, R.layout.renews_top_layout, this);

        this.mTopAdImg = (ImageView) this.findViewById(R.id.top_ad_img);
        this.mSearchView = this.findViewById(R.id.search);
    }

    private void setListener() {
//        this.mTopAdImg.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startWebAct();
//            }
//        });
//
//        this.mSearchView.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startSearchAct();
//            }
//        });
    }

    private boolean canLoad() {
        boolean flag = false;

        if (this.isAttached && !this.isDestroyed()) {
            flag = true;
        }

        return flag;
    }

    private boolean isDestroyed() {
        boolean flag = false;

        if (Build.VERSION.SDK_INT >= 17) {
            flag = this.isDestroyed17();
        } else {
            flag = this.isDestroyed16();
        }

        return flag;
    }

    private boolean isFinishing() {
        boolean flag = false;

        Context context = this.getContext();
        if (null != context && context instanceof Activity) {
            Activity act = (Activity) context;
            flag = act.isFinishing();
        }

        return flag;
    }

    private boolean isDestroyed16() {
        return this.isFinishing();
    }

    @TargetApi(17)
    private boolean isDestroyed17() {
        boolean flag = false;

        Context context = this.getContext();
        if (null != context && context instanceof Activity) {
            Activity act = (Activity) context;
            if (act.isFinishing() || act.isDestroyed()) {
                flag = true;
            }
        }

        return flag;
    }

//    private void showAdImg(boolean isShow) {
//        this.mTopAdImg.setVisibility(isShow ? View.VISIBLE : View.GONE);
//    }
//
//    private void showSearchImg(boolean isShow) {
//        this.mSearchView.setVisibility(isShow ? View.VISIBLE : View.GONE);
//    }
//
//    private void resetClickUrl(String url) {
//        this.mClickUrl = url;
//    }
//
//    private void setAdId(String adId) {
//        this.mAdId = adId;
//    }
//
//    /**
//     * 请求顶部广告信息
//     */
//    private void requestAdInfo() {
//        if (this.canLoad()) {
//            NetContent.getJsonObject(NewsAccessor.NEWS_TOP_AD_URL, new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    String id = response.optString("id");
//                    String img = response.optString("src");
//                    String clickUrl = response.optString("click_url");
//
//                    if (!TextUtils.isEmpty(img)
//                            && canLoad()) {
//
//                        showAdImg(true);
//                        loadAdImg(img);
//
//                        showSearchImg(false);
//                    } else {
//                        showAdImg(false);
//
//                        showSearchImg(true);
//                    }
//
//                    setAdId(id);
//                    resetClickUrl(clickUrl);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    showAdImg(false);
//                    resetClickUrl("");
//                }
//            });
//        }
//    }
//
//    private void loadAdImg(String adUrl) {
//        try {
//            Glide.with(this.getContext())
//                    .load(adUrl)
//                    .into(this.mTopAdImg);
//        } catch (Exception e) {
//        }
//    }
//
//    private PermissionsUtil permissionsUtil;
//
//    private void startSearchAct() {
//        final FragmentActivity context = (FragmentActivity) getContext();
//        try {
//            permissionsUtil = new PermissionsUtil(context, new PermissionsStatusListener() {
//                @Override
//                public void permissionSuccessful(String permission) {
//                    getContext().startActivity(new Intent(context, CaptureActivity.class));
//                    permissionsUtil.dispose();
//                }
//
//                @Override
//                public void permissionFail(String permission) {
//
//                }
//            });
//            permissionsUtil.checkCameraPermissions();
//        } catch (RuntimeException e) {
//        } catch (Exception e) {
//        }
//    }
//
//    private void startWebAct() {
//        if (!TextUtils.isEmpty(this.mClickUrl)) {
//            Intent intent = new Intent(this.getContext(), MyWebActivity.class);
//            intent.putExtra("AdsID", this.mAdId);
//            intent.putExtra("url", this.mClickUrl);
//            intent.putExtra("textLength", 20);
//            this.getContext().startActivity(intent);
//        }
//    }
}
