package com.chaychan.news.utils;

import android.text.TextUtils;

import com.chaychan.news.model.entity.BBSH5Info;
import com.chaychan.news.model.entity.BBSPostReplyItem;
import com.chaychan.news.model.entity.BBSTopItem;
import com.chaychan.news.model.entity.BBSPostItem;
import com.chaychan.news.model.entity.ShowPhotoItem;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：TouTiao-master
 * 类描述：
 * 创建人：you4580
 * 创建时间：2019/1/29 16:58
 * 修改人：you4580
 * 修改时间：2019/1/29 16:58
 * 修改备注：
 */

public class ParseUtil {

    /**
     * 解析论坛列表信息
     */
    public static Map parserPost(String resource) {
        Map bbsMap = new HashMap();
        if (resource != null) {
            try {
                JSONObject jsonObject = new JSONObject(resource);
                if (jsonObject != null) {
                    if (jsonObject.has("errorCode"))
                        bbsMap.put("errorCode", jsonObject.optString("errorCode"));
                    if (jsonObject.has("postList")) {
                        bbsMap.put("postList", parserBBSPostInfo(jsonObject.optJSONArray("postList")));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return bbsMap;
    }


    /**
     * 解析帖子
     */
    public static ArrayList<BBSPostItem> parserBBSPostInfo(JSONArray response) {
        ArrayList<BBSPostItem> arrayList = new ArrayList();
        if (response != null && response.length() > 0) {
            for (int a = 0; a < response.length(); a++) {
                BBSPostItem bbsPostItem = new BBSPostItem();
                JSONObject jsonObject1 = response.optJSONObject(a);
                if (jsonObject1.has("postType")) bbsPostItem.setLayoutType(jsonObject1.optString("postType"));
                if (!TextUtils.isEmpty(bbsPostItem.getLayoutType()) && bbsPostItem.getLayoutType().equals("3")) {
                    JSONObject jsonObject = null;
                    if (jsonObject1.has("post")) jsonObject = jsonObject1.optJSONObject("post");
                    BBSH5Info bbsh5Info = new BBSH5Info();
                    bbsPostItem.setBbsh5Info(parserH5InfoItem(jsonObject,bbsh5Info));
                } else if (!TextUtils.isEmpty(bbsPostItem.getLayoutType()) && bbsPostItem.getLayoutType().equals("1")) {
                    JSONArray jsonArray = jsonObject1.optJSONArray("post");
                    if (jsonArray != null && jsonArray.length() > 0) {
                        ArrayList<BBSTopItem> list = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length();i++){
                            list.add(parserBoardItem(jsonArray.optJSONObject(i)));
                        }
                        bbsPostItem.setTopItemArrayList(list);
                    }
                } else {
                    JSONObject jsonObject = null;
                    if (jsonObject1.has("post")) jsonObject = jsonObject1.optJSONObject("post");
                    bbsPostItem = parserPostItem(jsonObject,bbsPostItem);
                }
                arrayList.add(bbsPostItem);
            }
        }
        return arrayList;
    }

    public static BBSPostItem parserPostItem(JSONObject jsonObject,BBSPostItem bbsPostItem) {
        if (jsonObject != null) {
            if (jsonObject.has("bookId"))
                bbsPostItem.setBookId(jsonObject.optString("bookId"));
            if (jsonObject.has("userIcon"))
                bbsPostItem.setUserIcon(jsonObject.optString("userIcon"));
            if (jsonObject.has("title"))
                bbsPostItem.setTitle(jsonObject.optString("title"));
            if (jsonObject.has("content"))
                bbsPostItem.setContent(jsonObject.optString("content"));
            if (jsonObject.has("userId"))
                bbsPostItem.setUserId(jsonObject.optString("userId"));
            if (jsonObject.has("username"))
                bbsPostItem.setUserName(jsonObject.optString("username"));
            if (jsonObject.has("userGrade"))
                bbsPostItem.setUserGrade(jsonObject.optString("userGrade"));
            if (jsonObject.has("bbs"))
                bbsPostItem.setBbsName(jsonObject.optString("bbs"));
            if (jsonObject.has("boardId"))
                bbsPostItem.setBoardId(jsonObject.optString("boardId"));
            if (jsonObject.has("manuId"))
                bbsPostItem.setManuId(jsonObject.optString("manuId"));
            if (jsonObject.has("productId"))
                bbsPostItem.setProductid(jsonObject.optString("productId"));
            if (jsonObject.has("boardname"))
                bbsPostItem.setBoardName(jsonObject.optString("boardname"));
            if (jsonObject.has("lastdate"))
                bbsPostItem.setPosted_time(jsonObject.optString("lastdate"));
            if (jsonObject.has("reply"))
                bbsPostItem.setReply_count(jsonObject.optString("reply"));
            if (jsonObject.has("good"))
                bbsPostItem.setGood(jsonObject.optString("good"));
            if (jsonObject.has("watch"))
                bbsPostItem.setWatch(jsonObject.optString("watch"));
            if (jsonObject.has("isAdmin"))
                bbsPostItem.setIsAdmin(jsonObject.optBoolean("isAdmin"));
            if (jsonObject.has("imageList")) {
                JSONArray imageList = jsonObject.optJSONArray("imageList");
                if (imageList != null) {
                    ArrayList<String> list = new ArrayList<>();
                    for (int b = 0; b < imageList.length(); b++) {
                        list.add(imageList.optString(b));
                    }
                    bbsPostItem.setImageArrayList(list);
                }
            }
            if (jsonObject.has("replys")) {
                JSONArray replysList = jsonObject.optJSONArray("replys");
                if (replysList != null) {
                    ArrayList<BBSPostReplyItem> list = new ArrayList<>();
                    for (int c = 0; c < replysList.length(); c++) {
                        BBSPostReplyItem bbsPostReplyItem = new BBSPostReplyItem();
                        JSONObject replyObject = replysList.optJSONObject(c);
                        if (replyObject == null) {
                            break;
                        }
                        if (replyObject.has("reply"))
                            bbsPostReplyItem.setReply(replyObject.optString("reply"));
                        if (replyObject.has("replyContent"))
                            bbsPostReplyItem.setReplyContent(replyObject.optString("replyContent"));
                        if (replyObject.has("post"))
                            bbsPostReplyItem.setPost(replyObject.optString("post"));
                        list.add(bbsPostReplyItem);
                    }
                    bbsPostItem.setReplyItemArrayList(list);
                }
            }
        }
        return bbsPostItem;
    }

    private static BBSH5Info parserH5InfoItem(JSONObject jsonObject,BBSH5Info bbsh5Info) {
        if (jsonObject != null) {
            if (jsonObject.has("title"))bbsh5Info.setTitle(jsonObject.optString("title"));
            if (jsonObject.has("url"))bbsh5Info.setUrl(jsonObject.optString("url"));
            if (jsonObject.has("pic"))bbsh5Info.setPic(jsonObject.optString("pic"));
        }
        return bbsh5Info;
    }


    public static BBSTopItem parserBoardItem(JSONObject postObject) {
        BBSTopItem postItem = new BBSTopItem();
        if (postObject != null) {
            if (postObject.has("bbs"))
                postItem.setBbsName(postObject.optString("bbs"));
            if (postObject.has("boardId"))
                postItem.setBoardId(postObject.optString("boardId"));
            if (postObject.has("manuId"))
                postItem.setManuId(postObject.optString("manuId"));
            if (postObject.has("productId"))
                postItem.setProductid(postObject.optString("productId"));
            if (postObject.has("boardName"))
                postItem.setBoardName(postObject.optString("boardName"));
            if (postObject.has("boardIcon"))
                postItem.setBoardIconUrl(postObject.optString("boardIcon"));
            if (postObject.has("postCount"))
                postItem.setPostCount(postObject.optString("postCount"));
            if (postObject.has("subId"))
                postItem.setSubId(postObject.optString("subId"));
            if (postObject.has("follow"))
                postItem.setFollowCount(postObject.optString("follow"));
            if (postObject.has("shareUrl"))
                postItem.setShareUrl(postObject.optString("shareUrl"));
            if (postObject.has("bgColor"))
                postItem.setBgColor(postObject.optString("bgColor"));
            if (postObject.has("isFollow"))
                if (!TextUtils.isEmpty(postObject.optString("isFollow")) && postObject.optString("isFollow").equals("1")) {
                    postItem.setIsFollow(true);
                } else {
                    postItem.setIsFollow(false);
                }
        }
        return postItem;
    }

    /**
     * @param @param  string
     * @param @return
     * @param @throws JSONException
     * @return List<String>
     * @throws
     * @Title: parserBbsPicList
     * @Description: TODO解析帖子详情页图片列表
     * @author cigar
     */
    public static ArrayList<ShowPhotoItem> parserBbsPicList(String string){
        ArrayList<ShowPhotoItem> list = new ArrayList<ShowPhotoItem>();
        if (!TextUtils.isEmpty(string)) {
            try{
                JSONArray array = new JSONArray(string);
                for (int i = 0; i < array.length(); i++) {
                    ShowPhotoItem showPhotoItem = new ShowPhotoItem();
                    showPhotoItem.setImageUrl(array.getString(i));
                    list.add(showPhotoItem);
                }
            }catch (Exception e){

            }
        }
        return list;
    }
}
