package com.example.administrator.muyilife;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/4/9.
 */

public class Image {

    private Handler handler;
    private static final int SHOW = 0;

    public void ShowImage(final ImageView image,String url){
        ReShowimage(url);
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SHOW:
                    image.setImageBitmap((Bitmap) msg.obj);
                }
            }
        };
    }
    public void ReShowimage( final String ull){
        new Thread(){
            public void run(){
                Bitmap bitmapp = getImage(ull);
                Message message = Message.obtain();
                message.what = SHOW;
                message.obj = bitmapp;
                handler.sendMessage(message);
            }
        }.start();

    }
    public Bitmap getImage(String url) {
                try {

                    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                    connection.setRequestMethod("GET");
                    InputStream is = new BufferedInputStream(connection.getInputStream());
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                   return bitmap;
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                return null;
    }

    }