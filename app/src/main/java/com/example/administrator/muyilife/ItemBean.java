package com.example.administrator.muyilife;

/**
 * Created by Administrator on 2017/4/3.
 */

public class ItemBean {
    public String newsIconUrl;
    private String newsName;


    public ItemBean(String newsIconUrl, String newsName)
    {
        this.newsIconUrl = newsIconUrl;
        this.newsName = newsName;
    }
    public String getNewsIconUrl() {
        return newsIconUrl;
    }
    public void setNewsIconUrl(String newsIconUrl) {
        this.newsIconUrl = newsIconUrl;
    }
    public String getNewsName() {
        return newsName;
    }
    public void setNewsName(String newsName) {
        this.newsName = newsName;
    }
}
