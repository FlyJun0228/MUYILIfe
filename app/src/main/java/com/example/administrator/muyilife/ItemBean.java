package com.example.administrator.muyilife;

/**
 * Created by Administrator on 2017/4/3.
 */

public class ItemBean {

    public String newsIconUrl;
    public String newsName;

    public ItemBean(String newsIconUrl, String newsName) {
        this.newsIconUrl = newsIconUrl;
        this.newsName = newsName;
    }
    public String getNewsName() {
        return newsName;
    }
}
