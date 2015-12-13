package chsj.chanyouji.fragments.tools.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;
import java.util.List;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.adapter.AbsAdapter;

public class ExchangeActivity extends AppCompatActivity {

    private Drawable[] imgs = {
            getResources().getDrawable(R.mipmap.vietnam),
            getResources().getDrawable(R.mipmap.china),
            getResources().getDrawable(R.mipmap.unitedstates),
            getResources().getDrawable(R.mipmap.europe)
    };
    private String[] str = {"VND","CNY","USD","EUR"};
    private String[] moneyUnit = {"盾","人民币","美元","欧元"};

    private AbsAdapter adapter;

    private HashMap<Drawable,String> map;

    private List<HashMap> datas;

    @ViewInject(R.id.exchange_list_view)
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        //初始化数据：
        x.view().inject(this);
        initDatas();
        initView();
    }

    private void initDatas() {
        //初始化数据：
        for (int i=0;i<4;i++){
            map.put(imgs[i],str[i]+","+moneyUnit[i]);
            datas.add(i,map);
        }
    }

    private void initView() {
        //初始化数据    和adapter:
        adapter = new AbsAdapter(this,datas,R.layout.exchange_list_view_item) {
            @Override
            public void bindView(ViewHolder vHolder, Object data) {
                ImageView imageView = (ImageView) vHolder.getView(R.id.exchange_list_view_imageView);//国旗

                EditText moneyTxt = (EditText) findViewById(R.id.exchange_money); //国家简写
                TextView moneyUnit = (TextView) findViewById(R.id.exchange_money_unit); //单位

                imageView.setImageResource(R.mipmap.ic_launcher);

                moneyUnit.setText("meiyaun");
                moneyTxt.setText("nihao");
            }
        };

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
