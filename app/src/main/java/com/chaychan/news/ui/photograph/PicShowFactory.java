package com.chaychan.news.ui.photograph;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class PicShowFactory {

    /**
     * 带文本的大图浏览
     *
     * @param id       主id
     * @param （产品库需要传）
     * @param title    用于分享的标题
     * @param url      用于分享的图片url
     * @param isPro    用于区分评论0.产品库评论（id前需要加"d"）
     *                 1.资讯评论
     *                 2，产品库单个点评图库
     *                 3，产品库点评所有图片
     *                 4, 产品库问答项图片展示
     */
    public static void openGraphic(String id, String title, String url, String isPro, Context context) {
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        intent.putExtra("isPro", isPro);
//        intent.setClass(context, GraphicShowActi.class);
        context.startActivity(intent);
    }



    /**
     * 未带文本的大图浏览网络数据
     *
     * @param map     参数集合
     * @param type    数据模式类别
     * @param context 当前上下文对象
     */
    public static void openPicturNetwork(Map<String, String> map, int type, Context context) {
        Intent intent = new Intent(context, PicturShowActi.class);
        switch (type) {
            case PicturShowActi.NETWORK_BBS://论坛
                intent.putExtra("bbs", map.get("bbs"));
                intent.putExtra("boardid", map.get("boardid"));
                intent.putExtra("bookid", map.get("bookid"));
                intent.putExtra("position", map.get("position"));
                intent.putExtra("type", type);
                break;
        }
        context.startActivity(intent);
    }
}
