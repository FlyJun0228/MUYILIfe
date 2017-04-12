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

import com.example.administrator.muyilife.Image;
import com.example.administrator.muyilife.R;

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


public class Third extends Fragment {

    private TextView textView;
    private   String news, news1, news2, news3, news4, news5, news6;
    private String urll = "https://www.muyilife2016.com/news";
    private ImageView imageView, imageView1,imageView2,imageView3,imageView4,imageView5;
    private static final int SHOW =0;
    private Handler handler;
    Bundle bundle = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_third, container, false);
        initView(view);
        Connection();

        handler =new Handler(){
            public void handleMessage(Message msg){
                final ArrayList<String> list = msg.getData().getStringArrayList("new");
                textView.setText(list.get(0));
                new Image().ShowImage(imageView, list.get(1));
                new Image().ShowImage(imageView1, list.get(2));
                new Image().ShowImage(imageView2, list.get(3));
                new Image().ShowImage(imageView3, list.get(4));
                new Image().ShowImage(imageView4, list.get(5));
                new Image().ShowImage(imageView5, list.get(6));

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
                    Json(respone.toString());


                    // Json(respone.toString());
                    ArrayList<String> ne = new ArrayList<String>();
                    ne.add(news);
                    ne.add(news1);
                    ne.add(news2);
                    ne.add(news3);
                    ne.add(news4);
                    ne.add(news5);
                    ne.add(news6);
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
                news6 = jsonObject.getString("news6");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}