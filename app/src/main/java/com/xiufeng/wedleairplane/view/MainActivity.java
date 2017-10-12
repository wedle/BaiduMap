package com.xiufeng.wedleairplane.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.xiufeng.wedleairplane.R;
import com.xiufeng.wedleairplane.common.StaticData;
import com.xiufeng.wedleairplane.models.MapMod;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.map_view_search)
    private TextureMapView mMapView ;

    private BaiduMap mBaiduMap;
    private double lLng = 121.132726;
    private double lLat = 31.28198;
    private List<MapMod> bikeAdressList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //初始化地图控件
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        initData();
        showMap(lLat,lLng);
    }

    private void initData() {
        lLat = getIntent().getDoubleExtra("lLat",31.28198);
        lLng = getIntent().getDoubleExtra("lLng",121.132726);
        bikeAdressList = new ArrayList<>();
        bikeAdressList.add(new MapMod("",30.28198,120.131726));
        bikeAdressList.add(new MapMod("",30.28298,120.132726));
        bikeAdressList.add(new MapMod("",30.28398,120.133726));
        bikeAdressList.add(new MapMod("",30.28498,120.134726));
        bikeAdressList.add(new MapMod("",30.28598,120.135726));
        bikeAdressList.add(new MapMod("",30.28698,120.136726));
        bikeAdressList.add(new MapMod("",30.28798,120.137726));

    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
        //在地图上定位
//        showMap(StaticData.lLat, StaticData.lLng);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
    //通过传入经纬度实现定位
    private void showMap(double latitude, double longtitude) {
        LatLng point = new LatLng(latitude, longtitude);
        BitmapDescriptor bitmap = null;
        bitmap = BitmapDescriptorFactory.fromResource(R.drawable.little_airplane);
        for(int i=0;i<bikeAdressList.size();i++){
            OverlayOptions option = new MarkerOptions().position(new LatLng(bikeAdressList.get(i).getLats(),bikeAdressList.get(i).getLngs())).icon(bitmap);
            // 在地图上添加Marker，并显示
            mBaiduMap.addOverlay(option);
        }
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(point));
        //设置缩放级别为20米
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newMapStatus(new MapStatus.Builder().zoom(19).build()));
    }

}
