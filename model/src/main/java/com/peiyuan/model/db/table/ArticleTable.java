package com.peiyuan.model.db.table;

import com.peiyuan.model.entity.Article;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by hexun on 2016/1/28.
 */
public class ArticleTable extends RealmObject{

    @PrimaryKey
    private int _id;
    private int agreeCount;
    private int articleStatus;
    private int audioDuration;
    private String audioUrl;
    private String brief;
    private int checkStatus;
    private int commentCount;
    private String content;
    private int isCollection;
    private int isRecommend;
    private long postTimeStamp;
    private String postTimeStampStr;
    private int safeStatus;
    private int shareCount;
    private String title;
    private String trueName;
    private int updateTimeStamp;
    private int userID;
    private String userLogo;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(int agreeCount) {
        this.agreeCount = agreeCount;
    }

    public int getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(int articleStatus) {
        this.articleStatus = articleStatus;
    }

    public int getAudioDuration() {
        return audioDuration;
    }

    public void setAudioDuration(int audioDuration) {
        this.audioDuration = audioDuration;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public int getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(int checkStatus) {
        this.checkStatus = checkStatus;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public long getPostTimeStamp() {
        return postTimeStamp;
    }

    public void setPostTimeStamp(long postTimeStamp) {
        this.postTimeStamp = postTimeStamp;
    }

    public String getPostTimeStampStr() {
        return postTimeStampStr;
    }

    public void setPostTimeStampStr(String postTimeStampStr) {
        this.postTimeStampStr = postTimeStampStr;
    }

    public int getSafeStatus() {
        return safeStatus;
    }

    public void setSafeStatus(int safeStatus) {
        this.safeStatus = safeStatus;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public int getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(int updateTimeStamp) {
        this.updateTimeStamp = updateTimeStamp;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }
}
