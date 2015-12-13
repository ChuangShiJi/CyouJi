package chsj.chanyouji;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

import org.xutils.x;

/**
 * ProjectName : chsj.chanyouji
 * Created by : zhaoQiang
 * Email : zhaoq_hero163.com
 * On 2015/11/10 // 18:05
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        SDKInitializer.initialize(this);
        super.onCreate();
        x.Ext.init(this);
    }
}
