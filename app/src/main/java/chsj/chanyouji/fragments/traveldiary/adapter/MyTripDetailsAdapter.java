package chsj.chanyouji.fragments.traveldiary.adapter;

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
import chsj.chanyouji.fragments.traveldiary.model.TripDetails.Notes;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/11
 */
public class MyTripDetailsAdapter extends BaseAdapter {


    private Context context;

    private List<Notes> datas;

    private int count = 0;


    private static boolean isShowDay = true;//是否显示第几天
    private int day = 2;//第几天
    private static int temp = 1;//控制日期的显示
    private static View ret = null;
    private static View textView = null;

    public MyTripDetailsAdapter(Context context, List<Notes> notes) {
        this.context = context;
        this.datas = notes;

    }

    @Override
    public int getCount() {
        int ret = 0;
        if (datas != null) {
            ret = datas.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View ret = null;

        if (convertView != null) {
            ret = convertView;
        } else {
            ret = LayoutInflater.from(context).inflate(R.layout.activity_tripdetails_listview_item, parent, false);
        }

        ViewHolder holder = (ViewHolder) ret.getTag();

        if (holder == null) {
            holder = new ViewHolder();
            holder.img = (ImageView) ret.findViewById(R.id.activity_tripdetails_item_img);
            holder.txt = (TextView) ret.findViewById(R.id.activity_tripdetails_item_text);
            ret.setTag(holder);
        }

        Notes notes = datas.get(position);
        String des = notes.getDescription();
        String photoUrl = notes.getPhoto_url();


        if (des != null && photoUrl != null && !des.equals("null")) {

            holder.img.setVisibility(View.VISIBLE);
            holder.txt.setVisibility(View.VISIBLE);

            holder.txt.setText(des);
            x.image().bind(holder.img, photoUrl);
        }

        if (des != null && photoUrl == null && !des.equals("null")) {
            holder.txt.setVisibility(View.VISIBLE);

            holder.txt.setText(des);
            holder.img.setVisibility(View.GONE);
        }

        if (des == null && photoUrl != null) {
            holder.txt.setVisibility(View.GONE);
            holder.img.setVisibility(View.VISIBLE);
            x.image().bind(holder.img, photoUrl);
        }

        return ret;

    }


    private static class ViewHolder {


        ImageView img;
        TextView txt;

    }

}
