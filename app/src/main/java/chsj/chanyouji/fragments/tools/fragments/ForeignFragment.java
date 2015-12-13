package chsj.chanyouji.fragments.tools.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chsj.chanyouji.Constants;
import chsj.chanyouji.R;
import chsj.chanyouji.fragments.BaseFragment;
import chsj.chanyouji.fragments.adapter.MyExpandableAdapter;
import chsj.chanyouji.fragments.tools.ExpandableListViewCallback;
import chsj.chanyouji.fragments.tools.entity.City;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForeignFragment extends BaseFragment implements ExpandableListView.OnChildClickListener {

    private View view;
    private List<City> parent;
    private List<String> country;
    private Map<String,List<String>> city;

    @ViewInject(R.id.select_foreign_fragment_expandlist_view)
    private ExpandableListView exListView;

    private ExpandableListViewCallback callback;//回调接口 用于回传数据

    private MyExpandableAdapter adapter;

    public ForeignFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //产生关联时  回调这个接口：当前fragment从activity重写了回调接口  得到接口的实例化对象
        callback = (ExpandableListViewCallback) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        x.Ext.init(getActivity().getApplication());
        view = inflater.inflate(R.layout.fragment_foreign, container, false);

        x.view().inject(this, view);

        initData();

        //设置listView  点击事件：
        exListView.setOnChildClickListener(this);
        return view;
    }

    private void initData() {

        //使用 xUtils访问网络：  没有参数 无需设置参数
        final RequestParams params = new RequestParams(Constants.GET_SELECT_CITY_URL);

        //访问网络
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String s) {
                //访问成功：
                try {
                    JSONArray ret = new JSONArray(s);

                    parent = new ArrayList<City>();
                    country = new ArrayList<String>();
                    city = new HashMap<String, List<String>>();

                    JSONArray array = ret.getJSONObject(0).getJSONArray("destinations");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        TypeToken<City> token = new TypeToken<City>() {
                        };
                        Gson gson = new Gson();
                        City ci = gson.fromJson(jsonObject.toString(), token.getType());
                        parent.add(ci);
                    }
                    JSONArray array1 = ret.getJSONObject(1).getJSONArray("destinations");
                    for (int i = 0; i < array1.length(); i++) {
                        JSONObject jsonObject = array1.getJSONObject(i);
                        TypeToken<City> token = new TypeToken<City>() {
                        };
                        Gson gson = new Gson();
                        City ci = gson.fromJson(jsonObject.toString(), token.getType());
                        parent.add(ci);
                    }
                    JSONArray array2 = ret.getJSONObject(2).getJSONArray("destinations");
                    for (int i = 0; i < array2.length(); i++) {
                        JSONObject jsonObject = array2.getJSONObject(i);
                        TypeToken<City> token = new TypeToken<City>() {
                        };
                        Gson gson = new Gson();
                        City ci = gson.fromJson(jsonObject.toString(), token.getType());
                        parent.add(ci);
                    }

                    for (int i = 0; i < parent.size(); i++) {
                        country.add(parent.get(i).getName_zh_cn());
                        List<String> citys = new ArrayList<String>();
                        for (int j = 0; j < parent.get(i).getChildren().size(); j++) {
                            String cityname = parent.get(i).getChildren().get(j).getName_zh_cn();
                            citys.add(cityname);
                        }
                        city.put(parent.get(i).getName_zh_cn(), citys);
                    }

                    adapter = new MyExpandableAdapter(getActivity(), country, city);
                    exListView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable, boolean b) {
                Toast.makeText(x.app(), "访问网络数据失败，请检查网络连接", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException e) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    @Override
    public String getFragmentTitle() {
        return "国外";
    }


    @Override
    public boolean onChildClick(ExpandableListView parent,
        View v, int groupPosition, int childPosition, long id) {
        callback.sendMesssageCallback(this.parent.get(groupPosition).getChildren().get(childPosition));
        return false;
    }


}
