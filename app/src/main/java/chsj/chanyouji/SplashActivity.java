package chsj.chanyouji;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * 启动扉页
 */
public class SplashActivity extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        Thread thread = new Thread(this);
        thread.start();

    }



    @Override
    public void run() {

        try {

            //线程沉睡
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent();

        //TODO 如果没有显示欢迎页  那么起订欢迎页, 否则直接启动首页
        SharedPreferences sp =
                getSharedPreferences(Constants.SP_NAME,MODE_PRIVATE);

        //利用 SP保存字段  判断是否显示  欢迎页  保存数值一定是程序版本号
        //利用当前的  程序版本号  和 sp中的版本号进行比较  从而判断，更精确  兼容性更好
        int wsv = sp.getInt(Constants.SP_KEY_WELCOME_SHOW_VER,-1);

        if (BuildConfig.VERSION_CODE != wsv){
            //显示欢迎页
            intent.setClass(this,WelcomeActivity.class);
        }else{
            //TODO 显示主界面
            intent.setClass(this,MainActivity.class);
        }

        startActivity(intent);

        finish();
    }
}
