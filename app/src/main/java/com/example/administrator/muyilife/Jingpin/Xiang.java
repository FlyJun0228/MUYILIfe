package com.example.administrator.muyilife.Jingpin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.muyilife.Json.Json_xiang;
import com.example.administrator.muyilife.R;

public class Xiang extends AppCompatActivity implements View.OnClickListener{
    private Button mBtn_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        initView();
        initListener();
    }
    public void initView(){
        mBtn_show = (Button)findViewById(R.id.button_xiang);
    }
    public void initListener(){
        mBtn_show.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_xiang:
                Intent intent = new Intent(Xiang.this, Json_xiang.class);
                startActivity(intent);
        }
    }
}
