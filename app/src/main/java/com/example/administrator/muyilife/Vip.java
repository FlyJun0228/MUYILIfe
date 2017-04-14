package com.example.administrator.muyilife;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class Vip extends AppCompatActivity {

    private String intentData;      // 接收跳转传值

    private TextView mName;         // 姓名
    private TextView mWechat;       // 微信号
    private TextView mPoint;        // 积分
    private TextView mNumber;       // 会员卡号

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        initView();
        Connection();

        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        parseJson(msg.obj.toString());
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public void initView() {
        intentData = this.getIntent().getStringExtra("wechat");
        mName = (TextView) findViewById(R.id.tv_name);
        mWechat = (TextView) findViewById(R.id.tv_wechat);
        mPoint = (TextView) findViewById(R.id.tv_grade);
        mNumber = (TextView) findViewById(R.id.number);
    }

    public void Connection() {
        new Thread() {
            public void run() {
                try {
                    String urlNameString = "https://www.muyilife2016.com/wechat?wechat=" + intentData;
                    URL url = new URL(urlNameString);
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

    public void parseJson(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            if (jsonArray == null || jsonArray.length() != 1) {
                Toast.makeText(getApplicationContext(), "小帅哥，该帐号不存在！", Toast.LENGTH_SHORT).show();
                this.finish();
                return;
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                mName.setText(jsonObject.getString("name"));
                mNumber.setText(jsonObject.getString("number"));
                mPoint.setText(jsonObject.getString("jifen"));
                mWechat.setText(jsonObject.getString("weichat"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
