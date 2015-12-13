package chsj.chanyouji.fragments.tools.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.ViewUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.Calendar;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.tools.entity.Consume;

public class AddAccountActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    @ViewInject(R.id.addaccount_activity_nav_back)
    private RelativeLayout back; //返回
    @ViewInject(R.id.addaccount_activity_add)
    private ImageButton imgBtnadd;  //添加数据

    @ViewInject(R.id.add_account_consume_condition)
    private EditText consumeCondition; //消费明细

    @ViewInject(R.id.add_account_money)
    private TextView consumemoney;  //消费金额

    @ViewInject(R.id.add_account_more)
    private RadioButton more;
    @ViewInject(R.id.add_account_traffic)
    private RadioButton traffic;
    @ViewInject(R.id.add_account_food)
    private RadioButton food;
    @ViewInject(R.id.add_account_hotel)
    private RadioButton hotel;
    @ViewInject(R.id.add_account_tickets)
    private  RadioButton trickets;
    @ViewInject(R.id.add_account_shopping)
    private RadioButton shopping;
    @ViewInject(R.id.add_account_entertainment)
    private RadioButton entertainment;

    @ViewInject(R.id.add_account_spinner)
    private Spinner spinner;
    private String spinnerTxt;//当前备选中的  spinner 项的文本内容：

    private ArrayAdapter<CharSequence> adapter;

    //创建数据库
    DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            .setDbName("acc");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        spinner = (Spinner) findViewById(R.id.add_account_spinner);

        //使用 XUtils查找控件
        x.view().inject(this);

        back.setOnClickListener(this);
        imgBtnadd.setOnClickListener(this);

        spinner.setOnItemSelectedListener(this);

        initRadioBtnEvent();

        initSpinner();
    }

    private void initRadioBtnEvent() {
        //初始化 RadioButton 的单击事件：
        more.setOnCheckedChangeListener(this);
        traffic.setOnCheckedChangeListener(this);
        food.setOnCheckedChangeListener(this);
        hotel.setOnCheckedChangeListener(this);
        trickets.setOnCheckedChangeListener(this);
        shopping.setOnCheckedChangeListener(this);
        entertainment.setOnCheckedChangeListener(this);
    }

    private void initSpinner() {
        //初始化spinner:
        adapter = ArrayAdapter.createFromResource(
                this,
                R.array.datas,
                android.R.layout.simple_list_item_1
        );
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            //点击返回  上一页
            case R.id.addaccount_activity_nav_back:
                finish();
                break;

            //点击导航中 输入完成时
            case R.id.addaccount_activity_add:

//                输入完成获取输入内容：
                String conMoney = consumemoney.getText().toString().trim();
                String conCondition = consumeCondition.getText().toString().trim();

                if (conMoney!=null && !conMoney.equals("")){
                    //输入不为空:
                    float v1 = Float.parseFloat(conMoney);  //消费金额
                    //
                    RadioButton checkedBtn = getCheckedRadioButton();//获取被点击的  radiobutton
                    String checkedRBTxt = checkedBtn.getText().toString().trim();//获取被选中的 radioButton的文本内容：
//                    获取Spinn当前spinner 项的文本内容：

                    //TODO 将数据 插入数据库
                    DbManager db = x.getDb(daoConfig);

                    Consume consume = new Consume();
                    consume.setMoney(v1);
                    consume.setMoneyCondition(conCondition);
                    consume.setMoneyUnit(spinnerTxt);
                    consume.setPattern(checkedRBTxt);//消费方式
                    consume.setDate(getTime()); //设置保存时间

                    //保存数据
                    try {
                        db.save(consume);
                    } catch (DbException e) {
                        e.printStackTrace();
                    }
                    finish();
                }else{
                    Toast.makeText(this,"请检查你的输入是否正确？",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private String getTime() {
        //获取当前时间  并存入  数据
        StringBuilder date = new StringBuilder();

        Calendar calendar = Calendar.getInstance();
        date.append(calendar.get(Calendar.YEAR)).append("-")
                .append(calendar.get(Calendar.MONTH)).append("-")
                .append(calendar.get(Calendar.DATE)).append(" ")
                .append(calendar.get(Calendar.HOUR)).append(":")
                .append(calendar.get(Calendar.MINUTE)).append(":")
                .append(calendar.get(Calendar.SECOND));
        return date.toString();
    }


    private RadioButton getCheckedRadioButton() {

        RadioButton[] radioButtons = new RadioButton[]{
                more,traffic,food,hotel,trickets,shopping,entertainment};

        //获取被点击的数据
        RadioButton checkedBtn = null;

        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isChecked()){
                checkedBtn = radioButtons[i];
            }
        }

        return checkedBtn;//返回被选中的  radioButton
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //spinner 的监听事件
        String[] stringArray = getResources().getStringArray(R.array.datas);
        spinnerTxt = stringArray[position];//获取当前  被选中的 spinner的内容：
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //没有项 被选中：
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        //初始化单击事件
        switch (buttonView.getId()){

            case R.id.add_account_more:
                setCheckedFalse(more);
                break;

            case R.id.add_account_traffic:
                setCheckedFalse(traffic);

                break;
            case R.id.add_account_food:
                setCheckedFalse(food);

                break;
            case R.id.add_account_hotel:
                setCheckedFalse(hotel);

                break;
            case R.id.add_account_tickets:
                setCheckedFalse(trickets);

                break;
            case R.id.add_account_shopping:
                setCheckedFalse(shopping);
                break;
            case R.id.add_account_entertainment:
                setCheckedFalse(entertainment);
                break;
        }
    }

    public void setCheckedFalse(RadioButton radbtn){

        //将所有的数据  放入数组中：
        RadioButton[] radioButtons = new RadioButton[]{
                more,traffic,food,hotel,trickets,shopping,entertainment};

        if (radbtn.isChecked()){
            for (int i = 0; i < radioButtons.length; i++) {
                  if (!radbtn.equals(radioButtons[i])){
                            radioButtons[i].setChecked(false);
                  }
            }
        }
    }


}
