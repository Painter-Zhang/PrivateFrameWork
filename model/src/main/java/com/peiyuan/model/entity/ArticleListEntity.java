package com.peiyuan.model.entity;


import java.util.List;

public class ArticleListEntity {

    private String error;
    private int ishome;
    private int newcount;
    private long serverTime;
    private int status;

    private List<Article> articles;

    public void setError(String error) {
        this.error = error;
    }

    public void setIshome(int ishome) {
        this.ishome = ishome;
    }

    public void setNewcount(int newcount) {
        this.newcount = newcount;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public String getError() {
        return error;
    }

    public int getIshome() {
        return ishome;
    }

    public int getNewcount() {
        return newcount;
    }

    public long getServerTime() {
        return serverTime;
    }

    public int getStatus() {
        return status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public static class Article {
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
        private List<?> picLists;

        public void set_id(int _id) {
            this._id = _id;
        }

        public void setAgreeCount(int agreeCount) {
            this.agreeCount = agreeCount;
        }

        public void setArticleStatus(int articleStatus) {
            this.articleStatus = articleStatus;
        }

        public void setAudioDuration(int audioDuration) {
            this.audioDuration = audioDuration;
        }

        public void setAudioUrl(String audioUrl) {
            this.audioUrl = audioUrl;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public void setCheckStatus(int checkStatus) {
            this.checkStatus = checkStatus;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setIsCollection(int isCollection) {
            this.isCollection = isCollection;
        }

        public void setIsRecommend(int isRecommend) {
            this.isRecommend = isRecommend;
        }

        public void setPostTimeStamp(long postTimeStamp) {
            this.postTimeStamp = postTimeStamp;
        }

        public void setPostTimeStampStr(String postTimeStampStr) {
            this.postTimeStampStr = postTimeStampStr;
        }

        public void setSafeStatus(int safeStatus) {
            this.safeStatus = safeStatus;
        }

        public void setShareCount(int shareCount) {
            this.shareCount = shareCount;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public void setUpdateTimeStamp(int updateTimeStamp) {
            this.updateTimeStamp = updateTimeStamp;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public void setUserLogo(String userLogo) {
            this.userLogo = userLogo;
        }

        public void setPicLists(List<?> picLists) {
            this.picLists = picLists;
        }

        public int get_id() {
            return _id;
        }

        public int getAgreeCount() {
            return agreeCount;
        }

        public int getArticleStatus() {
            return articleStatus;
        }

        public int getAudioDuration() {
            return audioDuration;
        }

        public String getAudioUrl() {
            return audioUrl;
        }

        public String getBrief() {
            return brief;
        }

        public int getCheckStatus() {
            return checkStatus;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public String getContent() {
            return content;
        }

        public int getIsCollection() {
            return isCollection;
        }

        public int getIsRecommend() {
            return isRecommend;
        }

        public long getPostTimeStamp() {
            return postTimeStamp;
        }

        public String getPostTimeStampStr() {
            return postTimeStampStr;
        }

        public int getSafeStatus() {
            return safeStatus;
        }

        public int getShareCount() {
            return shareCount;
        }

        public String getTitle() {
            return title;
        }

        public String getTrueName() {
            return trueName;
        }

        public int getUpdateTimeStamp() {
            return updateTimeStamp;
        }

        public int getUserID() {
            return userID;
        }

        public String getUserLogo() {
            return userLogo;
        }

        public List<?> getPicLists() {
            return picLists;
        }
    }
}
