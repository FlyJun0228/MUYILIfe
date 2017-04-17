package com.example.administrator.muyilife.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.muyilife.R;
import com.example.administrator.muyilife.Vip;

public class Fourth extends Fragment  {

    private EditText editText;
    private CheckBox checkBox;
    private ImageView imageView;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_fourth, container, false);
        initView(view);
        initListerner();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("data",Context.MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean("check",false);
        if (isChecked){
            checkBox.setChecked(isChecked);
            String account = sharedPreferences.getString("account","");
            editText.setText(account);
        }
        return view;
    }
    public void initView(View view){
        imageView = (ImageView)view.findViewById(R.id.vip);
        editText = (EditText)view.findViewById(R.id.editText);
        checkBox = (CheckBox)view.findViewById(R.id.checkBox2);
    }
    public void initListerner(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = editText.getText().toString();
                boolean isChecked = checkBox.isChecked();
                if (TextUtils.isEmpty(account)){
                    Toast.makeText(getContext(), "请输入微信号", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor editor = (SharedPreferences.Editor)getContext().getSharedPreferences("data",Context.MODE_PRIVATE).edit();
                    editor.putString("account",account);
                    editor.putBoolean("check",isChecked);
                    editor.commit();
                }
                Intent intent = new Intent(getActivity(),Vip.class);
                intent.putExtra("wechat",editText.getText().toString());
                startActivity(intent);
            }
        });
    }

}
