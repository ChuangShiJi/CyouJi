package chsj.chanyouji.fragments.plan.plan_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.plan.entity.TripNotePlanDaysEntity;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/12
 */
public class TripNotePlanDaysAdapter extends BaseAdapter {
    private List<TripNotePlanDaysEntity> list;
    private Context context;
    public  TripNotePlanDaysAdapter(Context context,List<TripNotePlanDaysEntity>list){
        this.list=list;
        this.context=context;
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
        if (convertView==null){
           convertView= LayoutInflater.from(context).inflate(R.layout.travel_note_plan_days_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.travel_note_plan_days_imageview);
            viewHolder.station= (TextView) convertView.findViewById(R.id.travel_note_plan_days_station);
            viewHolder.tips= (TextView) convertView.findViewById(R.id.travel_note_plan_days_tips);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        TripNotePlanDaysEntity entity=list.get(position);
        viewHolder.tips.setText(entity.getTips());
        viewHolder.station.setText("第"+(position+1)+"站："+entity.getEntry_name());
        x.image().bind(viewHolder.imageView,entity.getImage_url());


        return convertView;
    }

    private static class ViewHolder{
        TextView tips,station;
        ImageView imageView;
    }
}
