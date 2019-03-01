/**
 * @FILE:BBSTopItem.java
 * @AUTHOR:wangweiwei
 * @DATE:2014-6-3 上午11:24:52
 **/
package com.chaychan.news.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/*******************************************
 * @CLASS:BBSTopItem 逛逛item
 * @DESCRIPTION:
 * @AUTHOR:wangweiwei
 * @VERSION:v1.0
 * @DATE:2014-6-3 上午11:24:52
 *******************************************/
public class BBSTopItem implements Parcelable {
    private String boardId;//板块id
    private String boardName;//板块名称
    private int type;//类型(推荐3，收藏2，浏览记录1)
    private String bbsName;//所属论坛名称
    private String bbsNameEn;//所属论坛英文名称id
    private String boardIconUrl;//板块图片
    private String postCount;
    private String followCount;
    private String shareUrl;
    private boolean isFollow;
    private String bgColor;

    private String bbsType;//热门板块 ,我关注的板块。
    private String bbsId;
    private String manuId;
    private String subId;

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public void setIsFollow(boolean _isFollow) {
        isFollow = _isFollow;
    }

    public boolean getIsFollow() {
        return isFollow;
    }


    public String getSubId() {
        return subId;
    }

    public void setSubId(String subId) {
        this.subId = subId;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getManuId() {
        return manuId;
    }

    public void setManuId(String manuId) {
        this.manuId = manuId;
    }

    public BBSTopItem() {
    }

    ;

    public BBSTopItem(Parcel in) {
        boardId = in.readString();
        boardName = in.readString();
        type = in.readInt();
        bbsName = in.readString();
        bbsNameEn = in.readString();
        boardIconUrl = in.readString();
        postCount = in.readString();
        followCount = in.readString();
        bbsType = in.readString();
        pinpaiid = in.readString();
        productid = in.readString();
        bbsId = in.readString();
        manuId = in.readString();
        shareUrl = in.readString();
        bgColor = in.readString();
    }

    public static final Creator<BBSTopItem> CREATOR = new Creator<BBSTopItem>() {
        @Override
        public BBSTopItem createFromParcel(Parcel in) {
            return new BBSTopItem(in);
        }

        @Override
        public BBSTopItem[] newArray(int size) {
            return new BBSTopItem[size];
        }
    };

    public String getBbsType() {
        return bbsType;
    }

    public void setBbsType(String bbsType) {
        this.bbsType = bbsType;
    }

    public String getPostCount() {
        return postCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }

    public String getFollowCount() {
        return followCount;
    }

    public void setFollowCount(String followCount) {
        this.followCount = followCount;
    }

    private String pinpaiid;

    public String getBoardIconUrl() {
        return boardIconUrl;
    }

    public void setBoardIconUrl(String boardicon) {
        this.boardIconUrl = boardicon;
    }

    private String productid;

    public String getPinpaiid() {
        return pinpaiid;
    }

    public void setPinpaiid(String pinpaiid) {
        this.pinpaiid = pinpaiid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBbsName() {
        return bbsName;
    }

    public void setBbsName(String bbsName) {
        this.bbsName = bbsName;
    }

    public String getBbsNameEn() {
        return bbsNameEn;
    }

    public void setBbsNameEn(String bbsNameEn) {
        this.bbsNameEn = bbsNameEn;
    }

    private String needBoardId;

    public String getNeedBoardId() {
        return needBoardId;
    }

    public void setNeedBoardId(String needBoardId) {
        this.needBoardId = needBoardId;
    }

    public String getBbsId() {
        return bbsId;
    }

    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(boardId);
        dest.writeString(boardName);
        dest.writeInt(type);
        dest.writeString(bbsName);
        dest.writeString(bbsNameEn);
        dest.writeString(boardIconUrl);
        dest.writeString(postCount);
        dest.writeString(followCount);
        dest.writeString(bbsType);
        dest.writeString(pinpaiid);
        dest.writeString(productid);
        dest.writeString(bbsId);
        dest.writeString(manuId);
        dest.writeString(shareUrl);
        dest.writeString(bgColor);
    }
}

