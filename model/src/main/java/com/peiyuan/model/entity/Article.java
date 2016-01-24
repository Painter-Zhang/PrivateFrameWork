package com.peiyuan.model.entity;


import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {

    private long id;
    //标题
    private String title;
    //简介
    private String brief;
    //内容
    private String content;
    //发布人ID
    private long userID;
    //发布人真实姓名
    private String trueName;
    //发布人头像
    private String userPortrait;
    //图片list
    private List<String> picLists;
    //audio地址
    private String audioUrl;
    private long audioDuration;
    //发布时间：UNIX时间
    private long postTimeStamp;
    private String postTimeStampStr;
    //最后更新时间【目前主要是删除，预留】
    private long updateTimeStamp;
    //审核状态 0未通过审核 1正常
    private int checkStatus;
    //敏感词状态 0有敏感词 1安全
    private int safeStatus;
    //文章状态 0删除  1正常  2草稿
    private int articleStatus;
    //冗余：点赞数量
    private int agreeCount;
    //冗余：【预留】 分享次数
    private int shareCount;
    //显示天
    private String dayDate;
    //显示月
    private String monthDate;
    //是否显示日期
    private boolean showDate;

    private int isCollection;
    private int isRecommend;
    private int commentCount;

    private boolean isFollowing;

    public String getDayDate() {
        return dayDate;
    }

    public void setDayDate(String dayDate) {
        this.dayDate = dayDate;
    }

    public String getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(String monthDate) {
        this.monthDate = monthDate;
    }


    public boolean isShowDate() {
        return showDate;
    }


    public void setShowDate(boolean showDate) {
        this.showDate = showDate;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public List<String> getPicLists() {
        return picLists;
    }

    public void setPicLists(List<String> picLists) {
        this.picLists = picLists;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public long getPostTimeStamp() {
        return postTimeStamp;
    }

    public void setPostTimeStamp(long postTimeStamp) {
        this.postTimeStamp = postTimeStamp;
    }

    public long getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(long updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getSafeStatus() {
        return safeStatus;
    }

    public void setSafeStatus(int safeStatus) {
        this.safeStatus = safeStatus;
    }

    public int getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(int articleStatus) {
        this.articleStatus = articleStatus;
    }

    public int getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(int agreeCount) {
        this.agreeCount = agreeCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public String getUserPortrait() {
        return userPortrait;
    }

    public void setUserPortrait(String userPortrait) {
        this.userPortrait = userPortrait;
    }

    public String getPostTimeStampStr() {
        return postTimeStampStr;
    }

    public void setPostTimeStampStr(String postTimeStampStr) {
        this.postTimeStampStr = postTimeStampStr;
    }

    public int getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(int isCollection) {
        this.isCollection = isCollection;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public boolean isFollowing() {
        return isFollowing;
    }

    public void setIsFollowing(boolean isFollowing) {
        this.isFollowing = isFollowing;
    }

    public long getAudioDuration() {
        return audioDuration;
    }

    public void setAudioDuration(long audioDuration) {
        this.audioDuration = audioDuration;
    }
}
