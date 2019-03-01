package com.chaychan.news.view.extral;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;


/**
 * Created by sujian on 2017/9/13.
 */
public class AdProductView extends RelativeLayout{

    public AdProductView(Context context) {
        super(context);

    }

    public AdProductView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public AdProductView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AdProductView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }


}
