package com.chaychan.news.model.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class BBSPostItem implements Serializable {

    public static String PLACEHOLDER = "";

/*    {
        "errorCode": 1,
            "postList": [
        {
            "postType": "0",
                "post": {
            "bookId": "458911",
                    "userIcon": "https://mypp-fd.zol-img.com.cn/t_s100x100/g5/M00/00/0C/ChMkJ1qR-KGIDXBxAAAPJfSBldIAAk9xgMAOYMAAA89777.jpg",
                    "title": "红梅花儿开",
                    "content": "",
                    "userId": "swldx",
                    "username": "swldx",
                    "userGrade": 3,
                    "bbs": "dcbbs",
                    "boardId": 16,
                    "manuId": "0",
                    "productId": "0",
                    "subId": "0",
                    "boardname": "人像摄影",
                    "good": "0",
                    "lastdate": "2019-01-29 17:30:51",
                    "reply": "0",
                    "watch": "10",
                    "isAdmin": false,
                    "imageList": [
            "http://i2.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/0A/ChMkJ1xQHUuIWmrUAAFGEAAv3l4AAuopQIPpyoAAUYo362.jpg",
                    "http://i3.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/0A/ChMkJ1xQHUuIG9rPAAI4hRKvY00AAuopQIZK3cAAjid723.jpg",
                    "http://i2.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/0A/ChMkJ1xQHUyIQE2wAAGar5i9q8EAAuopQIdMMIAAZrH582.jpg",
                    "http://i0.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/0A/ChMkJlxQHUyIFET9AAHLCJwMFVcAAuopQIhMqIAAcsg816.jpg",
                    "http://i0.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/0A/ChMkJlxQHU2IHuiLAAGy5tbTQGgAAuopQIlpYUAAbL-426.jpg",
                    "http://i4.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/0A/ChMkJ1xQHUyIapDvAAGft8teT98AAuopQIpNbsAAZ_P944.jpg"
                ],
            "replys": []
        }
        },
        {
            "postType": "0",
                "post": {
            "bookId": "458897",
                    "userIcon": "https://mypp-fd.zol-img.com.cn/t_s100x100/g5/M00/0C/0C/ChMkJllrdd2IAobHAAAokJIzngUAAeuXwJ9sZ4AACio261.jpg",
                    "title": "徐汇滨江女孩",
                    "content": "器材:佳能6D(单机)[Canon（佳能）数码相机]镜头:EF16-35mm f/2.8L II USM时间:2019-01-26 14:40:32.00快门:1/250光圈:F/2.8焦距:35毫米感光度:100器材:佳能6D(单机)[Canon（佳能）数码相机]镜头:EF16-35mm f/2.8L II USM时间:2019-01-26 14:44:32.00快门:1/250光圈:F/3.2焦距:16毫米感光度:100器材:佳能6D(单机)[Canon（佳能）数码相机]镜头:EF16-35mm f/2.8L II USM时间:2019-01-26 14:47:03.00快门:1/320光圈:F/2.8焦距:16毫米感光度:100器材:佳能6D(单机)[Canon（佳能）数码相机]镜头:EF16-35mm f/2.8L II USM时间:2019-01-26 14:50:35.00快门:1/250光圈:F/2.8焦距:35毫米感光度:100器材:佳能6D(单机)[Canon（佳能）数码相机]镜头:EF16-35mm f/2.8L II USM时间:2019-01-26 14:52:52.00快门:1/160光圈:F/2.8焦距:35毫米感光度:100器材:佳能6D(单机)[Canon（佳能）数码相机]镜头:EF16-35mm f/2.8L II USM时间:2019-01-26 14:53:27.00快门:1/160光圈:F/2.8焦距:35毫米感光度:100器材:佳能6D(单机)[Canon（佳能）数码相机]镜头:EF70-200mm f/2.8L IS II USM时间:2019-01-26 15:10:05.00快门:1/200光圈:F/2.8焦距:200毫米感光度:100器材:佳能6D(单机)[Canon（佳能）数码相机]镜头:EF70-200mm f/2.8L IS II USM时间:2019-01-26 15:17:20快门:1/160光圈:F/2.8焦距:200毫米感光度:100器材:佳能6D(单机)[Canon（佳能）数码相机]镜头:EF70-200mm f/2.8L IS II USM时间:2019-01-26 15:32:46.00快门:1/200光圈:F/2.8焦距:200毫米感光度:100器材:佳能6D(单机)[Canon（佳能）数码相机]镜头:EF16-35mm f/2.8L II USM时间:2019-01-26 16:21:48.62快门:1/320光圈:F/4.0焦距:16毫米感光度:100",
                    "userId": "4pvo14",
                    "username": "海鸥展翅",
                    "userGrade": 6,
                    "bbs": "dcbbs",
                    "boardId": 16,
                    "manuId": "0",
                    "productId": "0",
                    "subId": "0",
                    "boardname": "人像摄影",
                    "good": "0",
                    "lastdate": "2019-01-27 16:52:55",
                    "reply": "2",
                    "watch": "74",
                    "isAdmin": false,
                    "imageList": [
            "http://i5.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/02/ChMkJ1xNcUKIR3CHAAHzWxOWV7IAAumuwMRNwIAAfNz105.jpg",
                    "http://i3.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/02/ChMkJlxNcUKIGg7UAAH0xiz0FY4AAumuwMW0J4AAfTe439.jpg",
                    "http://i1.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/02/ChMkJlxNcUOIXldNAAJfvpNuAasAAumuwMZCn4AAl_W791.jpg",
                    "http://i1.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/02/ChMkJ1xNcUSILKbMAAFh-3-rCFsAAumuwMbqUoAAWIT171.jpg",
                    "http://i4.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/02/ChMkJlxNcUSIRckqAAGgqWuYqG0AAumuwMf_jAAAaDB594.jpg",
                    "http://i5.bbswater.fd.zol-img.com.cn/t_s184x184c5/g5/M00/06/02/ChMkJ1xNcUWId-7TAAKw9u7iOkIAAumuwMksr4AArEO895.jpg"
                ],
            "replys": []
        }
        }
    ]
    }*/

    /**
     *
     */

    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private String title;//帖子标题
    private String bbsName;//所属论坛名称
    private String bbsNameEn;//所属论坛英文名称id
    private String icon_url;//帖子图片
    private String author;//帖子作者
    private String hits;//点击数
    private String postedTime;//帖子时间
    private String replyTime;//回复时间
    private String replyCount;//回复数
    private String good = PLACEHOLDER;
    private String toptype = PLACEHOLDER;//置顶
    private String boardId;//板块id
    private String bbsCName;//论坛中文名字
    private String boardName;//板块名称
    private int type;//类型(推荐3，收藏2，浏览记录1)
    private String isLike;//是否收藏(1是，0否)
    private String bookId;//帖子id
    private String createTime;//创建时间，用于收藏排序
    private String userIcon; //用户头像
    private String userName; //用户昵称
    private String content; //内容
    private String userGrade;
    private String userId;//用户ID
    private String layoutType; //0,帖子布局，1.是感兴趣板块 ,2,3 banner样式 ,4,板块集合 5，话题 6,精选刷下UI 7,广点通广告
    private String watch;
    private String manuId;
    private String productid;
    private BBSH5Info bbsh5Info;
//    private NativeExpressADView nativeExpressADView;  //广点通-View
//    private ProductPlain productPlain; //自主广告电商
    private String primaryKey;//去重使用,板块id+论坛id+帖子id

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

//    public ProductPlain getProductPlain() {
//        return productPlain;
//    }
//
//    public void setProductPlain(ProductPlain productPlain) {
//        this.productPlain = productPlain;
//    }
//
//    public NativeExpressADView getNativeExpressADView() {
//        return nativeExpressADView;
//    }
//
//    public void setNativeExpressADView(NativeExpressADView nativeExpressADView) {
//        this.nativeExpressADView = nativeExpressADView;
//    }

    /**
     * ”是否是管理员”
     **/
    private boolean isAdmin;

    private ArrayList<BBSTopItem> topItemArrayList;

    private ArrayList<BBSPostItem> postItemArrayList;

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public BBSH5Info getBbsh5Info() {
        return bbsh5Info;
    }

    public void setBbsh5Info(BBSH5Info bbsh5Info) {
        this.bbsh5Info = bbsh5Info;
    }

    public ArrayList<BBSTopItem> getTopItemArrayList() {
        return topItemArrayList;
    }

    public void setTopItemArrayList(ArrayList<BBSTopItem> topItemArrayList) {
        this.topItemArrayList = topItemArrayList;
    }

    public String getManuId() {
        return manuId;
    }

    public void setManuId(String manuId) {
        this.manuId = manuId;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWatch() {
        return watch;
    }

    public void setWatch(String watch) {
        this.watch = watch;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public ArrayList<String> getImageArrayList() {
        return imageArrayList;
    }

    public void setImageArrayList(ArrayList<String> imageArrayList) {
        this.imageArrayList = imageArrayList;
    }

    public ArrayList<BBSPostReplyItem> getReplyItemArrayList() {
        return replyItemArrayList;
    }

    public void setReplyItemArrayList(ArrayList<BBSPostReplyItem> replyItemArrayList) {
        this.replyItemArrayList = replyItemArrayList;
    }

    private ArrayList<String> imageArrayList; //图片集合

    private ArrayList<BBSPostReplyItem> replyItemArrayList; //回复集合

    private String bbsId;

    public String getBbsId() {
        return bbsId;
    }

    public void setBbsId(String bbsId) {
        this.bbsId = bbsId;
    }

    private String actStartTime;//活动贴活动开始时间
    private String actEndTime;//活动贴活动结束时间
    private String actSignStartTime;////活动贴报名开始时间
    private String actSignEndTime;//活动贴报名结束时间
    private String actType;//活动贴类型
    private String actStatus;//活动状态1 未开始 2 进行中 3 已结束 4已公布获奖名单
    private String activityId;//活动id（391暂时没有用到）
    private String actSignTotalNum;//活动感兴趣人数
    private String readed;//帖子是否读过，0已读 1未读

    private boolean checked;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getReaded() {
        return readed;
    }

    public void setReaded(String readed) {
        this.readed = readed;
    }

    public String getActSignTotalNum() {
        return actSignTotalNum;
    }

    public void setActSignTotalNum(String actSignTotalNum) {
        this.actSignTotalNum = actSignTotalNum;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActStatus() {
        return actStatus;
    }

    public void setActStatus(String actStatus) {
        this.actStatus = actStatus;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getActStartTime() {
        return actStartTime;
    }

    public void setActStartTime(String actStartTime) {
        this.actStartTime = actStartTime;
    }

    public String getActEndTime() {
        return actEndTime;
    }

    public void setActEndTime(String actEndTime) {
        this.actEndTime = actEndTime;
    }

    public String getActSignStartTime() {
        return actSignStartTime;
    }

    public void setActSignStartTime(String actSignStartTime) {
        this.actSignStartTime = actSignStartTime;
    }

    public String getActSignEndTime() {
        return actSignEndTime;
    }

    public void setActSignEndTime(String actSignEndTime) {
        this.actSignEndTime = actSignEndTime;
    }


    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPosted_time() {
        return postedTime;
    }

    public void setPosted_time(String posted_time) {
        this.postedTime = posted_time;
    }

    public String getReply_count() {
        return replyCount;
    }

    public void setReply_count(String reply_count) {
        this.replyCount = reply_count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBbsName() {
        return bbsName;
    }

    public void setBbsName(String bbsName) {
        this.bbsName = bbsName;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getToptype() {
        return toptype;
    }

    public void setToptype(String toptype) {
        this.toptype = toptype;
    }

    public String getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public String getBbsCName() {
        return bbsCName;
    }

    public void setBbsCName(String bbsCName) {
        this.bbsCName = bbsCName;
    }

    public String getBbsNameEn() {
        return bbsNameEn;
    }

    public void setBbsNameEn(String bbsNameEn) {
        this.bbsNameEn = bbsNameEn;
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

    public Integer getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getIsLike() {
        return isLike;
    }

    public void setIsLike(String isLike) {
        this.isLike = isLike;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLayoutType() {
        return layoutType;
    }

    public void setLayoutType(String layoutType) {
        this.layoutType = layoutType;
    }

    public ArrayList<BBSPostItem> getPostItemArrayList() {
        return postItemArrayList;
    }

    public void setPostItemArrayList(ArrayList<BBSPostItem> postItemArrayList) {
        this.postItemArrayList = postItemArrayList;
    }
}
