package com.xiufeng.wedleairplane.common;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xiufeng.wedleairplane.customerize.WBApplication;

/*
 */
public class LocationHelper {
    private LocationCallBack callBack;


    private static LocationHelper helper;

    private LocationClient locationClient;
    private BDLocationListener locationListener = new MyBDLocationListener();

    //2
    private LocationHelper() {
        //第一步实例化定位核心类
        locationClient = new LocationClient(WBApplication.getInstance(), getLocOption());
        //第二步设置位置变化回调监听
        locationClient.registerLocationListener(locationListener);
    }

    //1
    public static LocationHelper getInstance() {
        if (helper == null) {
            helper = new LocationHelper();
        }
        return helper;
    }
    //3
    public void start() {
//        第三步开始定位
        if(locationClient!=null) {
            locationClient.start();
        }
    }

    public void stop() {
        if (locationClient != null) {
            locationClient.unRegisterLocationListener(locationListener);
            locationClient.stop();
            locationClient = null;
        }
    }


    /**
     * 获取定位初始化参数核心类
     *
     * @return
     */
    private LocationClientOption getLocOption() {
        LocationClientOption option = new LocationClientOption();
        //设置定位模式
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置定位坐标系
        option.setCoorType("bd09ll");
        //必须设置，否则得不到
        option.setAddrType("all");
       option.setIsNeedAddress(true);
        //设置是否打开gps
        option.setOpenGps(true);
        // 设置发起定位请求的间隔时间为3000ms
        option.setScanSpan(3000);
        return option;
    }


    private class MyBDLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (callBack != null) {
                callBack.callBack(bdLocation.getAddrStr(), bdLocation.getLatitude(), bdLocation.getLongitude());

            }
        }
    }

    public  interface LocationCallBack {
        void callBack(String addr, double lat, double lng);
    }

    public LocationCallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(LocationCallBack callBack) {
        this.callBack = callBack;
    }
}
