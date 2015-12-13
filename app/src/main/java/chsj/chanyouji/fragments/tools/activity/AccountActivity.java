package chsj.chanyouji.fragments.tools.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import chsj.chanyouji.Constants;
import chsj.chanyouji.R;
import chsj.chanyouji.fragments.adapter.AbsAdapter;
import chsj.chanyouji.fragments.tools.entity.Consume;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewInject(R.id.account_nav_back)
    private RelativeLayout back;
    @ViewInject(R.id.account_nav_add)
    private ImageButton navAdd;

    @ViewInject(R.id.account_to_allaccount)
    private RelativeLayout allAccount;

    @ViewInject(R.id.account_details_listView)
    private ListView listView;

    @ViewInject(R.id.account_clear_account)
    private Button btnClear;  //清空数据
//
//    @ViewInject(R.id.account_all_sum)
//    private TextView allSum;

    private List<Consume> consumes;//保存所有查到的数据
    private AbsAdapter adapter;//适配器


    private DbManager.DaoConfig dao;
    private DbManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        x.view().inject(this);

        dao = new DbManager.DaoConfig().setDbName("acc");

        back.setOnClickListener(this);
        navAdd.setOnClickListener(this);
        allAccount.setOnClickListener(this);

        btnClear.setOnClickListener(this);


        //TODO 查询数据总额：
//        //遍历数据：
//        getMoneySum();
//        allSum.setText(sum+":无单位");
    }

    private void getMoneySum() {
        DbManager dab = x.getDb(dao);
        try {
            List<Consume> all = dab.selector(Consume.class).findAll();
            if (all != null) {
                for (int i = 0; i < all.size(); i++) {
                    sum += all.get(i).getMoney();
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){

            case R.id.account_nav_back:
                finish();
                break;

            case R.id.account_nav_add:
                //跳转到添加  账户信息的界面
                Intent intent = new Intent(this,AddAccountActivity.class);
                startActivityForResult(intent, Constants.ACTIVITY_REQUESTCODE);
                break;

            case R.id.account_to_allaccount:
                //跳转到  消费统计的  界面
                Intent intent2 = new Intent(this,AllaccountActivity.class);
                startActivity(intent2);
                break;

            case R.id.account_clear_account:
                //删除所有
                try {
                    if (db.selector(Consume.class).findAll() != null) {
                        db.delete(Consume.class);

                        consumes.clear();
                        adapter.notifyDataSetChanged();
                    }else{
                        Toast.makeText(this,"当前无消费记录",Toast.LENGTH_SHORT).show();
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onResume() {

        //查询数据  查看  当前数据并显示
        findAllConsume();

        if (consumes != null) {
            //        //初始化适配器
            initListAdapter();
        }

        super.onResume();
    }

    private float sum;
    private void initListAdapter() {


        adapter= new AbsAdapter<Consume>(this,consumes,
                R.layout.consume_detail_list_view_item){
            //绑定数据
            @Override
            public void bindView(ViewHolder vHolder, Consume data) {
                //绑定视图：
                TextView pattern = (TextView) vHolder.getView(R.id.consume_pattern);
                TextView money = (TextView) vHolder.getView(R.id.consume_money);

                TextView condition = (TextView) vHolder.getView(R.id.consume_condition);
                TextView date = (TextView) vHolder.getView(R.id.detail_date_item);

                ImageButton imgBtn = (ImageButton) vHolder.getView(R.id.detail_imageBtn_delete);

                pattern.setText(data.getPattern());
                money.setText(" : "+data.getMoney()+data.getMoneyUnit());

                if (data.getMoneyCondition() != null) {
                    condition.setText("明细 : "+data.getMoneyCondition());
                }else{
                    condition.setText("明细 : 无");
                }

                sum = sum + data.getMoney();

                date.setText(data.getDate());
            }
        };

//        allSum.setText(sum+":无单位");

//        btnClear.setText("清空当前数据");

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //获取数据库中数据
    private void findAllConsume() {

        db = x.getDb(dao);
        //查询所有的 数据：
        try {
            consumes = db.selector(Consume.class).findAll();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
