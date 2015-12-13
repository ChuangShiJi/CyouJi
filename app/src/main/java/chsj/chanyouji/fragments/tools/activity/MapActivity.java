package chsj.chanyouji.fragments.tools.activity;

import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.tools.entity.City;

public class MapActivity extends AppCompatActivity{

    private MapView mMapView = null; //百度地图的 ui控件
    private BaiduMap baiduMap = null;   //百度地图的 管理类

    private BDLocation location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //初始化百度：
        mMapView = (MapView) findViewById(R.id.bmapView);
        baiduMap = mMapView.getMap();

        location = new BDLocation();

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Log.i("info", latitude +"::::"+longitude);

        initMap();

    }

    private void initMap() {

        //实例化标注图层参数对象
        MarkerOptions options=new MarkerOptions()
                .position(new LatLng(location.getLongitude(),location.getLatitude()))
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_gcoding))
                .title("自定义的标注");

        //将此图层增加到地图中
        baiduMap.addOverlay(options);

        //将地图的中心位置移动到  标注的位置上，并缩放图层的级别至15级
        baiduMap.setMapStatus(MapStatusUpdateFactory
                .newMapStatus(new MapStatus.Builder()
                        .target(new LatLng(location.getLongitude(),location.getLatitude())) //中心点
                        .zoom(15)
                        .rotate(45)
                        .overlook(45)
                        .build()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

}
