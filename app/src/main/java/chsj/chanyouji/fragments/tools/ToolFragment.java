package chsj.chanyouji.fragments.tools;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import chsj.chanyouji.Constants;
import chsj.chanyouji.R;
import chsj.chanyouji.fragments.BaseFragment;
import chsj.chanyouji.fragments.tools.activity.AccountActivity;
import chsj.chanyouji.fragments.tools.activity.ExchangeActivity;
import chsj.chanyouji.fragments.tools.activity.MapActivity;
import chsj.chanyouji.fragments.tools.activity.SelectCityActivity;
import chsj.chanyouji.fragments.tools.activity.TranslateActivity;
import chsj.chanyouji.fragments.tools.entity.City;

public class ToolFragment extends BaseFragment implements
        CompoundButton.OnCheckedChangeListener, View.OnClickListener{

    @ViewInject(R.id.tool_fragment_translation)
    private RadioButton radioButton1;
    @ViewInject(R.id.tool_fragment_account)
    private RadioButton radioButton2;
    @ViewInject(R.id.tool_fragment_map)
    private RadioButton radioButton3;
    @ViewInject(R.id.tool_fragment_exchange)
    private RadioButton radioButton4;

    private View view;

    @ViewInject(R.id.tool_fragment_city)
    private TextView city;//当前所选择城市
    @ViewInject(R.id.tool_min_temperature)
    private TextView minTem;//当前所选择城市最低气温
    @ViewInject(R.id.tool_max_temperature)
    private TextView maxTem;//当前所选择城市最高气温
    @ViewInject(R.id.current_time)
    private TextView cuttentTime;//当地当前时间

    private LocalBroadcastManager manager;//创建广播接受管理器
    private MyReceiverSelectCity receiverSelectCity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        manager = LocalBroadcastManager.getInstance(getActivity());
        receiverSelectCity = new MyReceiverSelectCity();

        //注册本地广播  接受者：
        manager.registerReceiver(receiverSelectCity,
                new IntentFilter(Constants.SELECT_CITY_BROADCAST_ACTION));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tool, container, false);
        //fragment中使用 xutils
        x.view().inject(this, view);

        radioButton1.setOnCheckedChangeListener(this);
        radioButton2.setOnCheckedChangeListener(this);
        radioButton3.setOnCheckedChangeListener(this);
        radioButton4.setOnCheckedChangeListener(this);
        city.setOnClickListener(this);

        //初始化一次当地时间
        city.setText("北京");
        initWeather(23+"");

        return view;
    }

    @Override
    public String getFragmentTitle() {
        return "工具箱";
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getSupportFragmentManager().beginTransaction().remove(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        Intent intent = new Intent();
        //当被选中  RadioBtn后：
        switch (buttonView.getId()){
            case R.id.tool_fragment_translation:
                intent.setClass(getActivity(), TranslateActivity.class);
                radioButton1.setChecked(false);
                break;
            case R.id.tool_fragment_account:
                intent.setClass(getActivity(), AccountActivity.class);
                radioButton2.setChecked(false);
                break;
            case R.id.tool_fragment_map:
                intent.setClass(getActivity(), MapActivity.class);
                radioButton3.setChecked(false);
                break;
            case  R.id.tool_fragment_exchange:
                intent.setClass(getActivity(), ExchangeActivity.class);
                radioButton4.setChecked(false);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        //设置   城市的单击事件：
        switch (v.getId()){

            case R.id.tool_fragment_city:
                //TODO  需要回传数据
                Intent intent = new Intent(getActivity(), SelectCityActivity.class);
                startActivity(intent);

                break;
        }
    }

    @Override
    public void onResume() {

        //获取到焦点时 刷新视图：
        if (receiverSelectCity.cdata != null) {
            //刷新数据：
            city.setText(receiverSelectCity.cdata.getName_zh_cn());
            initWeather(receiverSelectCity.cdata.getId()+"");
        }
        super.onResume();
    }

    private void initWeather(String cityId) {

        //刷新天气数据 和  当地时间：
        //使用  xutils访问网络：
        final RequestParams params = new RequestParams(
                String.format(Constants.GET_CITY_INFO_TOOLFRAGMENT_URL,cityId));

        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {

                //获取返回成功的数据：
                if(s!=null){
                    try {
                        JSONObject jsonObject = new JSONObject(s);
                        minTem.setText(jsonObject.getString("temp_min")+"'c");
                        maxTem.setText(jsonObject.getString("temp_max")+"'c");
                        cuttentTime.setText("当地时间 : "+jsonObject.getString("current_time"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {

            }

            @Override
            public void onCancelled(CancelledException e) {

            }

            @Override
            public void onFinished() {

            }
        });



    }

    //创建 广播接受者：  接受 所选择的  广播数据
    public final class MyReceiverSelectCity extends BroadcastReceiver{

        private Object object;

        private City.ChildrenEntity cdata;
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                object =  bundle.get(Constants.DATA_EXTRA_INTENT);
                if (object instanceof City.ChildrenEntity){
                    cdata = (City.ChildrenEntity) object;
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        manager.unregisterReceiver(receiverSelectCity);
        super.onDestroyView();
    }

}
