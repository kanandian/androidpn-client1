package org.androidpn.utils;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by pro1 on 18/3/7.
 */

public class LocationHolder {

    private static LocationHolder locationHolder = null;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener(){
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功
                if (location == null) {
                    location = new Location();
                }
                location.setAddress(aMapLocation.getAddress());
                location.setLatitude(aMapLocation.getLatitude());
                location.setLongitude(aMapLocation.getLongitude());
            } else {
                //定位失败
                Log.d("Location Service", "onLocationChanged: 定位失败");
            }
        }
    };

    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;

    private Location location;


    private LocationHolder() {
        //初始化定位
        mLocationClient = new AMapLocationClient(ActivityHolder.getInstance().getCurrentActivity());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        mLocationOption.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        if (mLocationClient != null) {
            mLocationClient.setLocationOption(mLocationOption);

            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
    }

    public static LocationHolder getInstance() {
        if (locationHolder == null) {
            synchronized (LocationHolder.class) {
                if (locationHolder == null) {
                    locationHolder = new LocationHolder();
                }
            }
        }

        return locationHolder;
    }

    public Location getLocation() {
        if (location == null) {
            location = new Location("嘉兴学院越秀校区:120.71762,30.735478");
        }
        return location;
    }
}
