package com.chaychan.news.model.pictour;

/**
 * Created by xueqiang on 2015/10/27.
 *
 * 图赏推荐相关信息
 */
public class PicRelative {
    /**文章ID**/
    private String docId;
    /**图片地址**/
    private String pic;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsTitle() {
        return sTitle;
    }

    public void setsTitle(String sTitle) {
        this.sTitle = sTitle;
    }

    /**文章头**/
    private String title;

    private String id;
    private String sTitle;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
