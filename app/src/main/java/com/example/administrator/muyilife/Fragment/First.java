package com.example.administrator.muyilife.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.muyilife.Logo.Logo;
import com.example.administrator.muyilife.Map;
import com.example.administrator.muyilife.R;

public class First extends Fragment implements View.OnClickListener {

    private ImageView mIVmap;
    private ImageView mLogo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_first, container, false);
        initView(view);
        initListener();
        return view;
    }

    public void initView(View view) {
        mIVmap = (ImageView) view.findViewById(R.id.iVmap);
        mLogo = (ImageView) view.findViewById(R.id.iVlogo);
    }

    public void initListener() {
        mIVmap.setOnClickListener(this);
        mLogo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iVmap:
                Intent intent = new Intent(getActivity(), Map.class);
                startActivity(intent);
                break;
            case R.id.iVlogo:
                Intent intent1 = new Intent(getActivity(), Logo.class);
                startActivity(intent1);
                break;
        }
    }
}
