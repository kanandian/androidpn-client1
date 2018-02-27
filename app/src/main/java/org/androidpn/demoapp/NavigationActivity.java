package org.androidpn.demoapp;

import android.app.Activity;
import android.os.Bundle;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;

import org.androidpn.nav.callback.MyNaviInfoCallback;
import org.androidpn.utils.ActivityHolder;

/**
 * Created by pro1 on 18/2/8.
 */

public class NavigationActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        INaviInfoCallback myNaviInfoCallback = new MyNaviInfoCallback();

        Poi start = new Poi("三元桥", new LatLng(39.96087,116.45798), "");
/**终点传入的是北京站坐标,但是POI的ID "B000A83M61"对应的是北京西站，所以实际算路以北京西站作为终点**/
        Poi end = new Poi("北京站", new LatLng(39.904556, 116.427231), "B000A83M61");
        AmapNaviPage.getInstance().showRouteActivity(NavigationActivity.this, new AmapNaviParams(start, null, end, AmapNaviType.DRIVER), myNaviInfoCallback);
    }
}
