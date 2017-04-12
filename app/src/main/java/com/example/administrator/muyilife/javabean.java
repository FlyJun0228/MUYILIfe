package com.example.administrator.muyilife;

/**
 * Created by Administrator on 2017/4/7.
 */

public class javabean {
    public String newsIconUrl;
    private String news;
    private String newsIconUrl1;
    private String newsIconUrl2;
    private String newsIconUrl3;
    private String newsIconUrl4;
    private String newsIconUrl5;

    public javabean(String newsIconUrl,String newsIconUrl1,String newsIconUrl2,String newsIconUrl3,String newsIconUrl4,String newsIconUrl5, String news)
    {
        this.newsIconUrl = newsIconUrl;
        this.newsIconUrl1 = newsIconUrl1;
        this.newsIconUrl2 = newsIconUrl2;
        this.newsIconUrl3 = newsIconUrl3;
        this.newsIconUrl4 = newsIconUrl4;
        this.newsIconUrl5 = newsIconUrl5;
        this.news = news;
    }
    public void setNewsIconUrl1(String newsIconUrl){
        this.newsIconUrl = newsIconUrl;
    }
    public String getNewsIconUrl() {
        return newsIconUrl;
    }
    public String getNewsIconUrl1() {
        return newsIconUrl1;
    }
    public String getNewsIconUrl2() {
        return newsIconUrl2;
    }
    public String getNewsIconUrl3() {
        return newsIconUrl3;
    }
    public String getNewsIconUrl4() {
        return newsIconUrl4;
    }
    public String getNewsIconUrl5() {
        return newsIconUrl5;
    }
    public void setNewsIconUrl(String newsIconUrl) {
        this.newsIconUrl = newsIconUrl;
    }
    public String getNewsName() {
        return news;
    }
    public void setNewsName(String news) {
        this.news = news;
    }
}
