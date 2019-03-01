package com.demo.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import flyn.Eyes;

public class MainActivity extends AppCompatActivity {

    private int[] mStatusColors = new int[]{
            R.color.color_BDBDBD,
            R.color.color_BDBDBD,
            R.color.color_BDBDBD,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setStatusBarColor(3);//设置状态栏颜色
    }

    private void setStatusBarColor(int position) {
        if (position == 3){
            //如果是我的页面，状态栏设置为透明状态栏
            Eyes.translucentStatusBar(MainActivity.this,true);
        }else{
            Eyes.setStatusBarColor(MainActivity.this, UIUtils.getColor(mStatusColors[position]));
        }
    }
}
