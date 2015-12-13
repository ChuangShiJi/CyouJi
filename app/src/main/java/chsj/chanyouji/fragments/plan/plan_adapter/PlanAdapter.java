package chsj.chanyouji.fragments.plan.plan_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.plan.entity.PlanEntity;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/10
 */
public class PlanAdapter extends BaseAdapter {
    private List<PlanEntity> list;
    private Context context;
    private HashMap<String,String> hashMap;
    private AdapterView.OnItemClickListener itemClickListener;
    public void setOnClickListener(AdapterView.OnItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    public PlanAdapter(Context context, List<PlanEntity> list) {
        this.list = list;
        this.context = context;
        hashMap=new HashMap<String,String>();
        hashMap.put("1","国外.亚洲");
        hashMap.put("2","国外.欧洲");
        hashMap.put("3","美洲、大洋洲、非洲与南极洲");
        hashMap.put("99","国内.港澳台");
        hashMap.put("999","国内.大陆");


    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.plan_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.plan_country_item);
            viewHolder.gridView = (GridView) convertView.findViewById(R.id.plan_country_gridview);
            viewHolder.gridView.setOnItemClickListener(itemClickListener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlanEntity planEntity = list.get(position);
        viewHolder.textView.setText(hashMap.get(planEntity.getCategory()));
//        设置gridView的适配器
        PlanGridViewAdapter planGridViewAdapter = new PlanGridViewAdapter(context, planEntity.getDestinatonses());
        viewHolder.gridView.setAdapter(planGridViewAdapter);

        viewHolder.gridView.setTag(position);
        return convertView;
    }

    private static class ViewHolder {
        GridView gridView;
        TextView textView;

    }
}
