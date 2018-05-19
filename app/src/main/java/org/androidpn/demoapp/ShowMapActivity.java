package org.androidpn.demoapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;

import org.androidpn.adapter.ShopAdapter;
import org.androidpn.info.ShopInfo;

import java.util.List;

public class ShowMapActivity extends BaseActivity {

    private final static int UPDATE_UI = 0;

    private MapView mMapView = null;
    private AMap aMap = null;
    private MyLocationStyle myLocationStyle;

    private LinearLayout searchView;

    private List<ShopInfo> shopInfoList = null;

    private ListView listView;
    private ShopAdapter adapter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == UPDATE_UI) {
                for (ShopInfo shopInfo : shopInfoList) {
                    double latitude = shopInfo.getLatitude();
                    double longitude = shopInfo.getLongitude();

                    LatLng latLng = new LatLng(latitude, longitude);
                    Marker marker = aMap.addMarker(new MarkerOptions().position(latLng).title(shopInfo.getStype()).snippet(shopInfo.getSname()));
                    marker.setObject(shopInfo);

                    listView.setVisibility(View.VISIBLE);
                    adapter = new ShopAdapter(shopInfoList, ShowMapActivity.this);
                    listView.setAdapter(adapter);

                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_map);


        initData();


        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
//        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式

        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        aMap.setOnMarkerClickListener(new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                ShopInfo shopInfo = (ShopInfo) marker.getObject();
//                Toast.makeText(ShowMapActivity.this, shopInfo.getSname(), Toast.LENGTH_LONG).show();
                shopInfoList.clear();
                shopInfoList.add(shopInfo);
                listView.setVisibility(View.VISIBLE);
                adapter = new ShopAdapter(shopInfoList, ShowMapActivity.this);
                listView.setAdapter(adapter);

                return false;
            }
        });

    }

    @Override
    public void setContentList(Object contentList) {
        super.setContentList(contentList);

        if (contentList instanceof List) {
            this.shopInfoList = (List<ShopInfo>) contentList;
            Message msg = new Message();
            msg.what = UPDATE_UI;
            handler.sendMessage(msg);
        }
    }

    public void initData() {
        searchView = (LinearLayout) findViewById(R.id.Search_search);
        listView = (ListView) findViewById(R.id.list_bussinesses);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowMapActivity.this, SearchViewActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(ShowMapActivity.this, ShopDetailsActivity.class);
                Bundle bund = new Bundle();
                bund.putSerializable("ShopInfo",shopInfoList.get(position));
                intent.putExtra("value",bund);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

}
