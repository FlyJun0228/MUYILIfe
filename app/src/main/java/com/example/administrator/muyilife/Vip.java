package com.example.administrator.muyilife;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

    Handler handler = null;
    String wechat,number,name,jifen,Inten;
    String url = "https://www.muyilife2016.com/wechat";
    Bundle bundle = null;
    private TextView textView,textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        Inten = this.getIntent().getStringExtra("wechat");
        initView();
        Connection(url,"wechat");
        handler =new Handler(){
            public void handleMessage(Message msg){
                    final ArrayList<String> list = msg.getData().getStringArrayList("new");

                    textView.setText(list.get(0));
                    textView1.setText(list.get(1));
                    textView2.setText(list.get(2));
                    textView3.setText(list.get(3));

            }
        };
    }
    public void Connection(final String url,final String param) {
        new Thread() {
            public void run() {
                try {
                    String urlNameString = url +"?"+param+"="+ Inten;
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
                    Json(respone.toString());
                    ArrayList<String> ne = new ArrayList<String>();
                    ne.add(name);
                    ne.add(wechat);
                    ne.add(jifen);
                    ne.add(number);
                    Message message = new Message();
                    bundle = new Bundle();
                    bundle.putStringArrayList("new",ne);
                    message.setData(bundle);
                    handler.sendMessage(message);
                       /* runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                   Toast.makeText(MainActivity.this,reader.readLine(),Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });*/
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
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
                name = jsonObject.getString("name");
                number = jsonObject.getString("number");
                jifen = jsonObject.getString("jifen");
                wechat = jsonObject.getString("weichat");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
public void initView(){
    textView = (TextView)findViewById(R.id.tv_name);
    textView1 = (TextView)findViewById(R.id.tv_wechat);
    textView2 = (TextView)findViewById(R.id.tv_grade);
    textView3 = (TextView)findViewById(R.id.number);
}
}
