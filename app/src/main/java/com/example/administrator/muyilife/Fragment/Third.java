package com.example.administrator.muyilife.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.muyilife.R;
import com.example.administrator.muyilife.Image;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Third extends Fragment {

    private Handler handler;
    private TextView textView;
    private String urll = "https://www.muyilife2016.com/news";
    private ImageView imageView, imageView1,imageView2,imageView3,imageView4,imageView5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_third, container, false);
        initView(view);
        Connection();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Json(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        };
        return view;
    }
    public void initView(View v){
        textView = (TextView)v.findViewById(R.id.tv_show);
        imageView = (ImageView)v.findViewById(R.id.new1);
        imageView1 = (ImageView)v.findViewById(R.id.new2);
        imageView2 = (ImageView)v.findViewById(R.id.new3);
        imageView3 = (ImageView)v.findViewById(R.id.new4);
        imageView4 = (ImageView)v.findViewById(R.id.new5);
        imageView5 = (ImageView)v.findViewById(R.id.new6);
    }
    public void Connection() {
        new Thread() {
            public void run() {
                try {
                    URL url = new URL(urll);
                    final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream inputstream = connection.getInputStream();
                    final BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
                    StringBuilder respone = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        respone.append(line);
                    }
                    Message msg = Message.obtain();
                    msg.what = 0;
                    msg.obj = respone.toString();
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void Json(String jsondata) {
        try {
            JSONArray jsonArray = new JSONArray(jsondata);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                textView.setText(jsonObject.getString("news"));
                new Image().ShowImage(imageView, jsonObject.getString("news1"));
                new Image().ShowImage(imageView1, jsonObject.getString("news2"));
                new Image().ShowImage(imageView2, jsonObject.getString("news3"));
                new Image().ShowImage(imageView3, jsonObject.getString("news4"));
                new Image().ShowImage(imageView4, jsonObject.getString("news5"));
                new Image().ShowImage(imageView5, jsonObject.getString("news6"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}