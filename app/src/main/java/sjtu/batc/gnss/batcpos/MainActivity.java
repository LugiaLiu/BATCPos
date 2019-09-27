package sjtu.batc.gnss.batcpos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CoordinateConverter;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //UI
    private ViewPager viewPager;
    private List<View> viewList;
    private View mapView,statusView,configView;
    //mapPage
    private MapView mMapView = null;
    //高德地图相关
    private AMap aMap = null;
    private Marker marker = null;
    private LatLng gpsLatLng = null;
    private LatLng gaoDeLatLng = null;
    private CameraUpdate cameraUpdate = null;
    private CoordinateConverter converter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUI(savedInstanceState);
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

    private void setUI(Bundle savedInstanceState){
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater = getLayoutInflater();
        mapView = inflater.inflate(R.layout.map_layout,null);
        statusView = inflater.inflate(R.layout.status_layout,null);
        configView = inflater.inflate(R.layout.config_layout,null);
        viewList = new ArrayList<View>();
        viewList.add(mapView);
        viewList.add(statusView);
        viewList.add(configView);
        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));


                return viewList.get(position);
            }
        };
        viewPager.setAdapter(pagerAdapter);
        setMapPage(savedInstanceState);
    }

    private void setMapPage(Bundle savedInstanceState){
        mMapView = (MapView) mapView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        if (aMap == null){
            aMap = mMapView.getMap();
        }
    }
}
