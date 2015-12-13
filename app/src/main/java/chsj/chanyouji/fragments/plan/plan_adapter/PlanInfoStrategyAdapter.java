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
import chsj.chanyouji.fragments.plan.entity.PlanInfoStrategyEntity;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/10
 */
public class PlanInfoStrategyAdapter extends BaseAdapter {
    private List<PlanInfoStrategyEntity> list;
    private Context context;
    private View.OnClickListener clickListener;

    public void setOnClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;

    }

    public PlanInfoStrategyAdapter(Context context, List<PlanInfoStrategyEntity> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.plan_strategy_activity_info_list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.plan_strategy_activity_info_item_imageview);
            viewHolder.countryEn = (TextView) convertView.findViewById(R.id.plan_strategy_activity_info_item_countryname_en);
            viewHolder.countryZh = (TextView) convertView.findViewById(R.id.plan_strategy_activity_info_item_countryname_zh);
            viewHolder.tipsTV = (TextView) convertView.findViewById(R.id.plan_strategy_tips);
            viewHolder.tripTV = (TextView) convertView.findViewById(R.id.plan_strategy_trip);
            viewHolder.specialTV = (TextView) convertView.findViewById(R.id.plan_strategy_special);
            viewHolder.travelTV = (TextView) convertView.findViewById(R.id.plan_strategy_travel);
            viewHolder.downloadImageView= (ImageView) convertView.findViewById(R.id.plan_strategy_download);
            viewHolder.downloadImageView.setOnClickListener(clickListener);
            viewHolder.travelTV.setOnClickListener(clickListener);
            viewHolder.specialTV.setOnClickListener(clickListener);
            viewHolder.tipsTV.setOnClickListener(clickListener);
            viewHolder.tripTV.setOnClickListener(clickListener);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PlanInfoStrategyEntity entity = list.get(position);
        if (position == 0) {

            viewHolder.countryZh.setText(entity.getName_zh_cn() + "概览");
        } else {
            viewHolder.countryZh.setText(entity.getName_zh_cn());
        }
        viewHolder.countryEn.setText(entity.getName_en());
        viewHolder.travelTV.setTag(position);
        viewHolder.specialTV.setTag(position);
        viewHolder.tipsTV.setTag(position);
        viewHolder.tripTV.setTag(position);
        viewHolder.downloadImageView.setTag(position);
        x.image().bind(viewHolder.imageView, entity.getImage_url());

        return convertView;

    }

    private static class ViewHolder {
        ImageView imageView,downloadImageView;
        TextView countryZh, countryEn;
        TextView tipsTV, tripTV, travelTV, specialTV;

    }
}
