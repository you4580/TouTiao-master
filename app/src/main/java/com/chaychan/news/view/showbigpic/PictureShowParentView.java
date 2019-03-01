package com.chaychan.news.view.showbigpic;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.chaychan.news.utils.UIUtils;

/**
 * Created by sujian on 2017/4/21.
 */
public class PictureShowParentView extends RelativeLayout{

    private final float ALPHA_MAX    = 1.0F;
    private final float ALPHA_MIN    = 0.0F;
    private final float ALPHA_MULTIPLE = 1.0F;

    private final int DURATION_FINSH = 300;
    private final int DURATION_RESET = 700;
    private final int DETAL_FINSH    = 300;

    private final int IMAGE_DEFAULT_HEIGHT = 0;

    private final int DEFAULT_X = -1;
    private final int DEFAULT_Y = -1;

    private int mDownX = DEFAULT_X;
    private int mDownY = DEFAULT_Y;
    private int mLastX = DEFAULT_X;
    private int mLastY = DEFAULT_Y;

    private int mScreenHeight;
    /** 系统是在move情况下当移动间距为多少时才算移动*/
    private int mSlop;
    /** 默认的translateY*/
    private int mDefaultTranslateY;
    /** */
    private float mOtherAlphaMultiple = 4.0F;
    /** 是否初始化了mDefaultTranslateY*/
    private boolean isInitTranslateY;
    /** */
    private boolean isStartAnimation;
    /** */
    private MoveEnum mCurrrentMoveEnum = MoveEnum.NONE;

    private IImageScaleListener mImageListener;
    private IMoveAlphaListener mMoveAlphaListener;
    private IFinishListener mFinishListener;

    private enum MoveEnum{
        VERTICAL,
        HORIZONTAL,
        NONE
    }

    public PictureShowParentView(Context context) {
        super(context);

        this.init();
    }

    public PictureShowParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();

    }

    public PictureShowParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        this.init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PictureShowParentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        this.init();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean dispatchFlag = true;

        int pointCount = ev.getPointerCount();
        //多手指情况和图片已经缩放了的情况下 不做操作
        if(pointCount > 1
                || this.isImageScaled()
                || this.isLargeImg()){
            return super.dispatchTouchEvent(ev);
        }

        if(!this.isInitTranslateY){
            this.initTranslateY();

            this.isInitTranslateY = true;
        }

        int action = ev.getAction();
        int pX = (int) ev.getRawX();
        int pY = (int) ev.getRawY();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                this.mDownX = pX;
                this.mDownY = pY;
                break;

            case MotionEvent.ACTION_MOVE:
                if(this.mLastX != DEFAULT_X
                        && this.mLastY != DEFAULT_Y){

                    pX = (int) ev.getRawX();
                    pY = (int) ev.getRawY();

                    int deltaX = pX - this.mDownX;
                    int deltaY = pY - this.mDownY;

                    boolean flagX = Math.abs(deltaX) >= this.mSlop;
                    boolean flagY = Math.abs(deltaY) >= this.mSlop;

                    if(flagX || flagY){
                        if(this.mCurrrentMoveEnum == MoveEnum.NONE){
                            if(flagX){
                                this.mCurrrentMoveEnum = MoveEnum.HORIZONTAL;
                            } else {
                                this.mCurrrentMoveEnum = MoveEnum.VERTICAL;
                            }
                        }
                    } else if(!flagX && !flagY){
                        if(this.mCurrrentMoveEnum == MoveEnum.NONE){
                            this.mCurrrentMoveEnum = MoveEnum.NONE;
                        }
                    }

                    if(this.mCurrrentMoveEnum == MoveEnum.VERTICAL){
                        dispatchFlag = false;

                        int moveY = pY - this.mLastY;
                        this.move(moveY);

                        if(this.mMoveAlphaListener != null){
                            float bgAlpha = getMoveAplha();
                            float otherAlpha = getMoveAplha(mOtherAlphaMultiple);
                            if(bgAlpha >= 0){
                                this.mMoveAlphaListener.alpha(otherAlpha, bgAlpha);
                            }

                        }
                    }
                }

                this.mLastX = pX;
                this.mLastY = pY;

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(isMoved()){
                    dispatchFlag = false;
                }

                this.resetOrFinish();
                this.resetAttribute();
                break;
        }

        boolean flag = false;
        if(dispatchFlag){
            flag = super.dispatchTouchEvent(ev);
        }

        return flag;
    }

    private void init(){
        ViewConfiguration vc = ViewConfiguration.get(this.getContext());
        this.mSlop = vc.getScaledTouchSlop();

        this.mScreenHeight = UIUtils.getResolution()[1] / 2;
    }

    private void initTranslateY(){
        this.mDefaultTranslateY = (int) this.getTranslationY();
    }

    private void move(int detalY){
        this.setTranslationY(this.getTranslationY() + detalY);
    }

    private float getMoveAplha() {
        int translateY = (int) this.getTranslationY();

        return ALPHA_MAX - (Math.abs(translateY) / (this.mScreenHeight + 0.0F) * ALPHA_MULTIPLE);
    }

    private float getMoveAplha(float alphaMultiple) {
        int translateY = (int) this.getTranslationY();

        return ALPHA_MAX - (Math.abs(translateY) / (this.mScreenHeight + 0.0F) * alphaMultiple);
    }

    private void resetLocation(){
        if(!this.isStartAnimation){
            int translateY = (int) this.getTranslationY();
            float alpha = Math.abs(translateY) / (this.mScreenHeight + 0.0F);
            int duration = (int) (DURATION_RESET * (alpha));

            this.moveByAnimation(duration, translateY, this.mDefaultTranslateY, null);
        }
    }

    private void finish(){
        if(!this.isStartAnimation){
            int translateY = (int) this.getTranslationY();
            float alpha = Math.abs(translateY) / (this.mScreenHeight);
            int duration = (int) (DURATION_FINSH * (1.0F - alpha));

            if(duration <= 30){
                duration = 30;
            }

            int end = 0;
            int imgHeight = this.getImageHeight();
            if(imgHeight > IMAGE_DEFAULT_HEIGHT){
                end = this.mScreenHeight + imgHeight;

                if(translateY < 0){
                    end = -end;
                }
            } else {
                if(translateY > 0){
                    end = this.mScreenHeight;
                } else {
                    end = -this.mScreenHeight;
                }

                end *= 2;
            }

            this.moveByAnimation(duration, translateY, end, new IMoveFinishListener() {
                @Override
                public void end() {
                    if(null != mFinishListener){
                        mFinishListener.finish();
                    }
                }
            });
        }
    }

    private void moveByAnimation(int duration, int start, int end, final IMoveFinishListener moveFinishListener) {
        ValueAnimator animator = ValueAnimator.ofInt(start,end);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();

                setTranslationY(value);

                if(mMoveAlphaListener != null){
                    float bgAlpha = getMoveAplha();
                    float otherAlpha = getMoveAplha(mOtherAlphaMultiple);

                    if(bgAlpha >= 0){
                        mMoveAlphaListener.alpha(otherAlpha, bgAlpha);
                    }
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

                if(null != moveFinishListener){
                    moveFinishListener.end();
                }
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

    private void resetOrFinish(){
        if(this.mCurrrentMoveEnum == MoveEnum.VERTICAL){
            int detaY = Math.abs((int)this.getTranslationY() - this.mDefaultTranslateY);

            int detalFinsh = DETAL_FINSH;
            int imgHeight = this.getImageHeight();
            if(imgHeight > IMAGE_DEFAULT_HEIGHT){
                //detalFinsh = imgHeight / 3;
                detalFinsh = this.mScreenHeight / 3;
            }

            if(detaY > detalFinsh){ //finish
                this.finish();
            } else { //resetLocation
                this.resetLocation();
            }
        }
    }

    private void resetAttribute(){
        this.mDownX = DEFAULT_X;
        this.mDownY = DEFAULT_Y;
        this.mLastX = DEFAULT_X;
        this.mLastY = DEFAULT_Y;

        this.isInitTranslateY = false;
        this.mCurrrentMoveEnum = MoveEnum.NONE;
    }

    private boolean isImageScaled(){
        boolean flag = false;

        if(null != this.mImageListener){
            flag = this.mImageListener.scale();
        }

        return flag;
    }

    private boolean isLargeImg(){
        return this.getImageHeight() > this.mScreenHeight * 2 ? true : false;
    }

    private boolean isMoved(){
        boolean flag = false;

        if(this.getTranslationY() != this.mDefaultTranslateY){
            flag = true;
        }

        return flag;
    }

    private int getImageHeight(){
        int height = IMAGE_DEFAULT_HEIGHT;

        if(null != this.mImageListener){
            height = this.mImageListener.getImgHeight();
        }

        return height;
    }

    public void setImageScaleListener(IImageScaleListener imageListener) {
        this.mImageListener = imageListener;
    }

    public void setMoveAlphaListener(IMoveAlphaListener moveAlphaListener) {
        this.mMoveAlphaListener = moveAlphaListener;
    }

    public void setFinishListener(IFinishListener finishListener) {
        this.mFinishListener = finishListener;
    }

    private interface IMoveFinishListener{
        void end();
    }

    public interface IImageScaleListener{
        boolean scale();
        int getImgHeight();
    }

    public interface IMoveAlphaListener {
        void alpha(float otherAlpha, float bgAlpha);
    }

    public interface IFinishListener{
        void finish();
    }
}
