package com.caspar.baidu;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.SupportMapFragment;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Marker mMarkerA;
    BitmapDescriptor bdA = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_jkwg_poto);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mMapView = findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(14.0f);
        mBaiduMap.setMapStatus(msu);
        LocationBean lb1 = new LocationBean(29.5698552055, 106.4749669914);
        LocationBean lb2 = new LocationBean(106.537596,29.580744);
        LocationBean lb3 = new LocationBean(106.542339,29.595443);
        List<LocationBean> mLocationData=new ArrayList<>();
//        for处理对象
        mLocationData.add(lb1);
        mLocationData.add(lb2);
        mLocationData.add(lb3);

        for (LocationBean mLocationDatum : mLocationData) {
            LatLng  latLng=new LatLng(mLocationDatum.getLongitude(),mLocationDatum.getLatitude());
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item_main, null);
            BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
            MarkerOptions ooA = new MarkerOptions().position(latLng).icon(bitmap)
                    .zIndex(9).draggable(true);
            ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
            mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));

        }
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.item_main, null);
//        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromView(view);
//        MarkerOptions ooA = new MarkerOptions().position(llA).icon(bitmap)
//                .zIndex(9).draggable(true);
//        ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
//        mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));
        LatLng  latLng=new LatLng(29.5698552055, 106.4749669914);
        // 定义地图状态(精确到50米)
        MapStatus mMapStatus = new MapStatus.Builder().target(llA).zoom(18).build();
        // 定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        // 改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }


    @Override
    protected void onDestroy() {
        // MapView的生命周期与Activity同步，当activity销毁时需调用MapView.destroy()
        mMapView.onDestroy();
        super.onDestroy();
        // 回收 bitmap 资源
        bdA.recycle();
    }
}
