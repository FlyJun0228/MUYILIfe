package com.example.administrator.muyilife;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.List;


public class Map extends Activity {

    private LocationManager locationmanager;
    private ToggleButton toggleButton;
    private BaiduMap baidumap;
    private MapView mapView;
    private String provider;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        initView();
        initListener();
        Location();

    }
    public void Location(){
        baidumap = mapView.getMap();
        baidumap.setMyLocationEnabled(true);
        locationmanager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        List<String> providerList = locationmanager.getProviders(true);
        if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        }else if (providerList.contains(LocationManager.NETWORK_PROVIDER)){
            provider = LocationManager.NETWORK_PROVIDER;
        }else {
            Toast.makeText(Map.this,"要想定位自己的位置需要在手机高级设置里打开定位服务哦",Toast.LENGTH_SHORT).show();
            return;
        }
        Location location = locationmanager.getLastKnownLocation(provider);
        LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
        MapStatusUpdate update1 = MapStatusUpdateFactory.zoomTo(17);
        baidumap.animateMapStatus(update1);
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
        baidumap.animateMapStatus(update);
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baidumap.setMyLocationData(locationData);
        locationmanager.requestLocationUpdates(provider,1,1,locationListener);
    }
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
        @Override
        public void onProviderEnabled(String provider) {

        }
        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        baidumap.setMyLocationEnabled(false);
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
    public void initView(){
        toggleButton = (ToggleButton)findViewById(R.id.location) ;
        mapView = (MapView) findViewById(R.id.flmap);
        button = (Button)findViewById(R.id.back);
    }
    public void initListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Map.this,MainActivity.class);
                startActivity(intent);
                Map.this.finish();
            }
        });
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Muyi();
                    if (locationmanager != null){
                        locationmanager.removeUpdates(locationListener);
                    }
                }else {
                    Location();
                }
            }
        });

    }
    public void Muyi(){
        BaiduMap baidumap = mapView.getMap();
        LatLng ll = new LatLng(39.908780, 116.297500);
        MapStatusUpdate update1 = MapStatusUpdateFactory.zoomTo(17);
        baidumap.animateMapStatus(update1);
        MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
        baidumap.animateMapStatus(update);
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(39.908780);
        locationBuilder.longitude(116.297500);
        MyLocationData locationData = locationBuilder.build();
        baidumap.setMyLocationData(locationData);
    }
}
