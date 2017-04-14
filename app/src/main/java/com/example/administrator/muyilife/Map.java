package com.example.administrator.muyilife;

import android.app.Activity;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;


public class Map extends Activity {

    private BaiduMap baidumap;
    private MapView mapView;
    private ImageView imageView;
    private String provider;
    private boolean isFirstLocate = true;
    private LocationManager locationmanager;
private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_map);
        mapView = (MapView) findViewById(R.id.flmap);
        button = (Button)findViewById(R.id.back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map.this,MainActivity.class);
                startActivity(intent);
            }
        });
        BaiduMap baidumap = mapView.getMap();
        LatLng ll = new LatLng(39.908780, 116.297500);
        MapStatusUpdate update1 = MapStatusUpdateFactory.zoomTo(17);
        baidumap.animateMapStatus(update1);
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
        baidumap.animateMapStatus(update);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }
}
