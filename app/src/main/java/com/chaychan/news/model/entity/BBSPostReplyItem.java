package com.chaychan.news.model.entity;

/**
 * @author xq
 * @created at 2016/9/20 11:40
 */
public class BBSPostReplyItem {
    //回复人
    private String reply;
    //回复内容
    private String replyContent;
    //评论人
    private String post;

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}


