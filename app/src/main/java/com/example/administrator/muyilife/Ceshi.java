package com.example.administrator.muyilife;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Ceshi extends AppCompatActivity {

    public String News1, News2, News3, News4, News5, News6;
    String urll = "https://www.muyilife2016.com/news";
    private ImageView imageView, imageView1;
    javabean javabea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceshi);
        imageView = (ImageView) findViewById(R.id.iv_mu);
        imageView1 = (ImageView) findViewById(R.id.iv_mu1);
        Connection();
       new Image().ShowImage(imageView,News1);
        Log.e("sdasd",News1);

}


    public String Connection() {
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


                 /* Json(respone.toString());
                    bitmap = getImage(News1);
                    Message message = Message.obtain();
                    message.what = SHOW;
                    message.obj = bitmap;
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
return null;
    }
 public void Json(String jsondata) {
     try {
         JSONArray jsonArray = new JSONArray(jsondata);
         for (int i = 0; i < jsonArray.length(); i++) {
             JSONObject jsonObject = jsonArray.getJSONObject(i);
             String news = jsonObject.getString("news");
             String news1 = jsonObject.getString("news1");
             String news2 = jsonObject.getString("news2");
             String news3 = jsonObject.getString("news3");
             String news4 = jsonObject.getString("news4");
             String news5 = jsonObject.getString("news5");
             String news6 = jsonObject.getString("news5");
            News1 = news1;
         }

     } catch (Exception e) {
         e.printStackTrace();
     }
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
