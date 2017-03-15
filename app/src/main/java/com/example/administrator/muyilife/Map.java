package com.example.administrator.muyilife;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;


public class Map extends Activity {

    private MapView mapView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        mapView = (MapView)findViewById(R.id.flmap);
    }
@Override
    protected void onDestroy(){
    super.onDestroy();
    mapView.onDestroy();
}
@Override
    protected void onPause(){
    super.onPause();
    mapView.onPause();
}
@Override
    protected void onResume(){
    super.onResume();
    mapView.onResume();
}
}
