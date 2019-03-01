package com.chaychan.news.ui.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 */
public class NewSearchTopLayout extends RelativeLayout{
    public static final String ACTION_SHOW_HIDE = "action:show_hide";
    public static final String VALUE_SHOW_HIDE = "value:show_hide";
    public static final String VALUE_MOVE = "value:move";
    private static final int MOVE_DEFAULT = -1;

    private final int ANIMATION_DURATION = 300;
    private int mDefaultTranslateY = Integer.MIN_VALUE;
    private int mHeight;

    private boolean isShow = true;
    private boolean isStartAnimation;
    private boolean isSetMoveHeight;

    private IMoveListener iMoveListener;

    private TopReceiver topReceiver;

    public NewSearchTopLayout(Context context) {
        super(context);
    }

    public NewSearchTopLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewSearchTopLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NewSearchTopLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if(!this.isSetMoveHeight){
            this.mHeight = this.getMeasuredHeight();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

//        if(null == this.topReceiver){
//            IntentFilter filter = new IntentFilter();
//            filter.addAction(ACTION_SHOW_HIDE);
//
//            this.topReceiver = new TopReceiver();
//            this.getContext().registerReceiver(this.topReceiver, filter);
//        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

//        if(null != this.topReceiver){
//            this.getContext().unregisterReceiver(this.topReceiver);
//        }
    }

    public void show(){
        if(this.isStartAnimation || this.isShow){
            return;
        }

        if(this.mDefaultTranslateY == Integer.MIN_VALUE){
            this.mDefaultTranslateY = (int) this.getTranslationY();
        }

        this.isShow = true;

        this.move(-mHeight, this.mDefaultTranslateY, true);
    }

    public void hide(){
        if(this.isStartAnimation || !this.isShow){
            return;
        }

        if(this.mDefaultTranslateY == Integer.MIN_VALUE){
            this.mDefaultTranslateY = (int) this.getTranslationY();
        }

        this.isShow = false;

        this.move(this.mDefaultTranslateY, -mHeight, false);
    }

    public void showOrHide(boolean src, int move){
        if((this.isShow && move > 0) || (!this.isShow && move < 0)){
            return;
        }

        if(src){
            this.show();
        } else {
            this.hide();
        }
    }

    private void move(int start, int end, final boolean isShow){
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(ANIMATION_DURATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                LayoutParams layoutParams = (LayoutParams) getLayoutParams();
                layoutParams.topMargin = value;
                requestLayout();

                if(null != iMoveListener){
                    iMoveListener.move(isShow, (Math.abs(value) / (mHeight + 0.0F)));
                }
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                isStartAnimation = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isStartAnimation = false;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                isStartAnimation = false;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animator.start();
    }

    public void setMoveHeight(int moveHeight){
        if(moveHeight > 0){
            this.isSetMoveHeight = true;
            this.mHeight = moveHeight;
        }
    }

    public void setIMoveListener(IMoveListener moveListener){
        this.iMoveListener = moveListener;
    }

    public interface IMoveListener{
        void move(boolean isShow, float percentage);
    }

    private class TopReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(!TextUtils.isEmpty(action) && action.equals(ACTION_SHOW_HIDE)){
                boolean showHide = intent.getBooleanExtra(VALUE_SHOW_HIDE, false);
                int move = intent.getIntExtra(VALUE_MOVE, MOVE_DEFAULT);

                showOrHide(showHide, move);
            }
        }
    }
}
