package chsj.chanyouji.fragments.plan.plan_adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.plan.entity.TripNoteEntity;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/11
 */
public class TravelNoteAdapter extends BaseAdapter {
    private List<TripNoteEntity> list;
    private Context context;

    public TravelNoteAdapter(Context context, List<TripNoteEntity> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.travel_note_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.travel_note_day);
            viewHolder.listView = (ListView) convertView.findViewById(R.id.travel_note_listview);
            viewHolder.memo = (TextView) convertView.findViewById(R.id.travel_note_memo);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TripNoteEntity entity = list.get(position);

        viewHolder.textView.setText("DAY" + (position + 1));
        TripNotePlanDaysAdapter daysAdapter = new TripNotePlanDaysAdapter(context, entity.getTripNotePlanDaysEntityList());
        viewHolder.listView.setAdapter(daysAdapter);

        viewHolder.memo.setText(Html.fromHtml(entity.getMemo()));
        return convertView;
    }

    private static class ViewHolder {
        TextView textView;
        ListView listView;
        TextView memo;
    }
}
