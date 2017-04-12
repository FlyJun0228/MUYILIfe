package com.example.administrator.muyilife;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

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

public class Ceshi extends AppCompatActivity {
    public  String news, news1, news2, news3, news4, news5, news6;
    private String urll = "https://www.muyilife2016.com/news";
    private ImageView imageView, imageView1;
    private static final int SHOW =0;
    private Handler handler;
    Bundle bundle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceshi);
        imageView = (ImageView) findViewById(R.id.iv_mu);
        imageView1 = (ImageView) findViewById(R.id.iv_mu1);
        Connection();

        handler =new Handler(){
            public void handleMessage(Message msg){
                final ArrayList<String> list = msg.getData().getStringArrayList("new");
                        new Image().ShowImage(imageView,list.get(0));
                new Image().ShowImage(imageView1,list.get(1));

            }
        };
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
                    Json(respone.toString());


                 // Json(respone.toString());
                    ArrayList<String> ne = new ArrayList<String>();
                    ne.add(news1);
                    ne.add(news3);
                    ne.add(news4);
                    ne.add(news5);
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
                news = jsonObject.getString("news");
                news1 = jsonObject.getString("news1");
                news2 = jsonObject.getString("news2");
                news3 = jsonObject.getString("news3");
                news4 = jsonObject.getString("news4");
                news5 = jsonObject.getString("news5");
                news6 = jsonObject.getString("news5");
                Log.e("asdasd",news);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
