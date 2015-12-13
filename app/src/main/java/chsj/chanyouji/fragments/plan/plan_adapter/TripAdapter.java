package chsj.chanyouji.fragments.plan.plan_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;
import org.xutils.x;

import java.util.List;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.plan.entity.TripEntity;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/10
 */
public class TripAdapter extends BaseAdapter {
    private List<TripEntity> list;
    private Context context;
    private View.OnClickListener clickListener;

    public void setClickListener(View.OnClickListener clickListener){
        this.clickListener=clickListener;

    }
    public TripAdapter(Context context, List<TripEntity> list) {
        this.context = context;
        this.list = list;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.trip_item_layout, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.description = (TextView) convertView.findViewById(R.id.trip_description);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.trip_imageview);
            viewHolder.imageView.setOnClickListener(clickListener);
            viewHolder.planDayCount = (TextView) convertView.findViewById(R.id.trip_plan_days_count);
            viewHolder.planNodesCount = (TextView) convertView.findViewById(R.id.trip_plan_nodes_count);
            viewHolder.name = (TextView) convertView.findViewById(R.id.trip_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TripEntity tripEntity = list.get(position);
        viewHolder.name.setText(tripEntity.getName());
        viewHolder.planNodesCount.setText(tripEntity.getPlan_nodes_count()+"个旅行地");
        viewHolder.planDayCount.setText(tripEntity.getPlan_days_count()+"天");
        viewHolder.description.setText(tripEntity.getDescription());
        viewHolder.imageView.setTag(tripEntity);
        x.image().bind(viewHolder.imageView, tripEntity.getImage_url());
        return convertView;
    }

    private static class ViewHolder {
        ImageView imageView;
        TextView planDayCount;
        TextView planNodesCount;
        TextView name;
        TextView description;
    }


}
