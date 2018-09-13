package com.caspar.baidu;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Marker mMarkerA;
    private TextView mTvLocationDeviceContent;
    private RecyclerView mRvLocationDevices;
    private List<LocationBean> mLocationData;
    private FrameLayout mFlPullUpOrDown;
    private boolean isFold = true;
    private LinearLayout mLlLocationDevicesMain;
    private int listHeight;
    private BitmapDescriptor bdA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initMap();
    }

    private void initMap() {

        mBaiduMap = mMapView.getMap();
        for (LocationBean mLocationDatum : mLocationData) {
            LatLng latLng = new LatLng(mLocationDatum.getLongitude(), mLocationDatum.getLatitude());
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.item_main, null);
            bdA = BitmapDescriptorFactory.fromView(view);
            MarkerOptions ooA = new MarkerOptions().position(latLng).icon(bdA)
                    .zIndex(9).draggable(true);
            ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
            mMarkerA = (Marker) (mBaiduMap.addOverlay(ooA));

        }
        LatLng latLng = new LatLng(29.5698552055, 106.4749669914);
        MapStatus mMapStatus = new MapStatus.Builder().target(latLng).zoom(18).build();
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        mBaiduMap.setMapStatus(mMapStatusUpdate);
    }


    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
        bdA.recycle();
    }

    private void initView() {
        mTvLocationDeviceContent = findViewById(R.id.tv_location_device_content);
        mFlPullUpOrDown = findViewById(R.id.fl_pull_up_or_down);
        mRvLocationDevices = findViewById(R.id.rv_location_devices);
        mLlLocationDevicesMain = findViewById(R.id.ll_location_devices_main);
        mMapView = findViewById(R.id.mapView);
        /***假数据**/
        LocationBean lb1 = new LocationBean(1, 1, 29.5698552055, 106.4749669914, "人造人2号", "金山大道", "第一群组人造人2号", true);
        LocationBean lb2 = new LocationBean(2, 1, 29.580744, 106.537596, "人造人15号", "金开大道", "第一群组人造人15号", false);
        LocationBean lb3 = new LocationBean(3, 2, 29.595443, 106.542339, "人造人18号", "通天大道", "第二群组人造人18号", false);
        /***假数据**/
        mLocationData = new ArrayList<>();
//        for处理对象
        mLocationData.add(lb1);
        mLocationData.add(lb2);
        mLocationData.add(lb3);
        measureHeight();
        mRvLocationDevices.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRvLocationDevices.setAdapter(new CommonAdapter<LocationBean>(this, R.layout.item_location_device_info, mLocationData) {
            @Override
            protected void convert(ViewHolder holder, final LocationBean locationBean, int position) {
                holder.setText(R.id.tv_item_device_name, locationBean.getDeviceName());
                holder.setText(R.id.tv_item_device_desc, locationBean.getGroupDescribe());
                if (locationBean.isChecked()) {
                    holder.getView(R.id.iv_item_device_checked).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.iv_item_device_checked).setVisibility(View.GONE);
                }
                holder.getView(R.id.rl_item_location_single_device).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LatLng cenpt = new LatLng(locationBean.getLongitude(), locationBean.getLatitude());
                        MapStatus mMapStatus = new MapStatus.Builder().target(cenpt).zoom(18).build();
                        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                        mBaiduMap.animateMapStatus(mMapStatusUpdate);
                        for (LocationBean mLocationDatum : mLocationData) {
                            mLocationDatum.setChecked(false);
                        }
                        locationBean.setChecked(true);
                        notifyDataSetChanged();
                        if (isFold) {
                            mLlLocationDevicesMain.animate().translationY(listHeight).setDuration(500);
                            isFold = false;
                        }
                    }
                });
            }

        });
        mFlPullUpOrDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foldAnimate();
            }
        });
    }

    private void foldAnimate() {
        if (!isFold) {
            mLlLocationDevicesMain.animate().translationY(0).setDuration(500);
        } else {
            mLlLocationDevicesMain.animate().translationY(listHeight).setDuration(500);
        }
        isFold = !isFold;
    }

    private void measureHeight() {
        LinearLayout.LayoutParams LayoutParams = (LinearLayout.LayoutParams) mRvLocationDevices.getLayoutParams();
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        listHeight= mLocationData.size() * dip2px(this, 110);
        LayoutParams.height =listHeight;
        LayoutParams.width = width;
        mRvLocationDevices.setLayoutParams(LayoutParams);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
