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
import chsj.chanyouji.fragments.plan.entity.Destinatons;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/10
 */
public class PlanGridViewAdapter extends BaseAdapter {
    private List<Destinatons> list;
    private Context context;

    public PlanGridViewAdapter(Context context, List<Destinatons> list) {
        this.list = list;
        this.context = context;

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
            convertView = LayoutInflater.from(context).inflate(R.layout.plan_list_grid_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.nameZh = (TextView) convertView.findViewById(R.id.plan_grid_item_cName_zh);
            viewHolder.name_En = (TextView) convertView.findViewById(R.id.plan_grid_item_cName_en);
            viewHolder.potCount = (TextView) convertView.findViewById(R.id.plan_grid__poi_count_item);
            viewHolder.imageViewBg = (ImageView) convertView.findViewById(R.id.plan_grid_item_icon);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Destinatons destinatons = list.get(position);
        viewHolder.nameZh.setText(destinatons.getName_zh_cn());
        viewHolder.name_En.setText(destinatons.getName_en());
        viewHolder.potCount.setText(destinatons.getPoi_count() + "旅行地");
//        使用xUtils加载图片
        x.image().bind(viewHolder.imageViewBg, destinatons.getImage_url());

        return convertView;
    }

    private static class ViewHolder {
        public TextView nameZh, name_En, potCount;
        public ImageView imageViewBg;

    }
}
