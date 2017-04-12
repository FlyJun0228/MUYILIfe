package com.example.administrator.muyilife.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.muyilife.NewAdapter;
import com.example.administrator.muyilife.R;
import com.example.administrator.muyilife.javabean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Third extends Fragment {

 private ListView mListView;

    String urll = "https://www.muyilife2016.com/news";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_third, container, false);
mListView = (ListView)view.findViewById(R.id.lv);
        new NewsAsyncTask().execute(urll);


        return view;

    }
    public List<javabean> Json(String jsondata) {

        List<javabean> javabeanList = new ArrayList<javabean>();
        try {
            String jsonString = readStream(new URL(urll).openStream());
            javabean java;
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {;
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String   news = jsonObject.getString("news");
                String  news1 = jsonObject.getString("news1");
                String  news2 = jsonObject.getString("news2");
                String  news3 = jsonObject.getString("news3");
                String  news4 = jsonObject.getString("news4");
                String  news5= jsonObject.getString("news5");
                String  news6 = jsonObject.getString("news5");
                java = new javabean(news1,news2,news3,news4,news5,news6,news);
                javabeanList.add(java);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return javabeanList;

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
    class NewsAsyncTask extends AsyncTask<String, Void, List<javabean>>
    {

        @Override
        protected List<javabean> doInBackground(String... params) {
            return Json(params[0]);
        }

        @Override
        protected void onPostExecute(List<javabean> result) {
            super.onPostExecute(result);
            NewAdapter adapter = new NewAdapter(Third.this, result, mListView);
            mListView.setAdapter(adapter);

        }
    }

}