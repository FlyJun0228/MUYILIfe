package com.example.administrator.muyilife.Json;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.administrator.muyilife.ItemBean;
import com.example.administrator.muyilife.Myadapter;
import com.example.administrator.muyilife.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Json_xiu extends AppCompatActivity {

    private ListView mListView;
    private String  urll = "https://www.muyilife2016.com/xiu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_xiu);
        mListView = (ListView) findViewById(R.id.lv_main);
        new Json_xiu.NewsAsyncTask().execute(urll);
        /* handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SHOW:
                        mTvId.setText((CharSequence) msg.obj);
                }
            }
        };
        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Connection();
                Connection();
            }
        });*/
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
                    /*  Message message = Message.obtain();
                    message.what = SHOW;
                    a=respone.toString();
                    message.obj = respone.toString();
                    handler.sendMessage(message);*/
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
    public List<ItemBean> Json(String jsondata) {
        List<ItemBean> itemBeanList = new ArrayList<ItemBean>();
        try {
            String jsonString = readStream(new URL(urll).openStream());
            ItemBean itemBean;
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String   name = jsonObject.getString("name");
                String  img = "http://www.muyilife2016.com/"+jsonObject.getString("img");
                // String img = img1.replaceAll(" ", "");
                itemBean = new ItemBean(img, name);
                itemBeanList.add(itemBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return itemBeanList;
    }
    private String readStream(InputStream is)
    {
        InputStreamReader isReader;
        String result = "";
        String line = "";
        try {
            isReader = new InputStreamReader(is, "utf-8");
            BufferedReader buffReader = new BufferedReader(isReader);
            while ((line = buffReader.readLine()) != null) {
                result += line;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    class NewsAsyncTask extends AsyncTask<String, Void, List<ItemBean>> {

        @Override
        protected List<ItemBean> doInBackground(String... params) {
            return Json(params[0]);
        }

        @Override
        protected void onPostExecute(List<ItemBean> result) {
            super.onPostExecute(result);
            Myadapter adapter = new Myadapter(Json_xiu.this, result, mListView);
            mListView.setAdapter(adapter);
        }
    }
}