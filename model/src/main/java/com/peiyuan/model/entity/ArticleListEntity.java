package com.peiyuan.model.entity;


import java.util.ArrayList;


public class ArticleListEntity {

    private String error;
    private int ishome;
    private int newcount;
    private long serverTime;
    private int status;

    private ArrayList<Article> articles;

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

    public void setArticles(ArrayList<Article> articles) {
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

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
