package com.chaychan.news.view.extral;

/**
 * 产品综合外观广告model
 *
 * Created by sujian on 2017/9/13.
 */
public class AdProductModel {

    /** 广告所属商城*/
    private String mShopName;

    /** 广告图url*/
    private String mImgUrl;

    /** 跳转的url*/
    private String mUrl;

    /** zol统计用到的信息*/
    private String mEvent;

    /** 价格*/
    private int mPrice;

    public String getShopName() {
        return mShopName;
    }

    public void setShopName(String shopName) {
        this.mShopName = shopName;
    }

    public String getImgUrl() {
        return mImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.mImgUrl = imgUrl;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public int getPrice() {
        return mPrice;
    }

    public void setPrice(int price) {
        this.mPrice = price;
    }

    public String getEvent() {
        return mEvent;
    }

    public void setEvent(String event) {
        this.mEvent = event;
    }
}
