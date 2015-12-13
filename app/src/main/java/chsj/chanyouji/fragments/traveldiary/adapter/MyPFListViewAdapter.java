package chsj.chanyouji.fragments.traveldiary.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

import chsj.chanyouji.R;
import chsj.chanyouji.fragments.traveldiary.activitys.TripDetailsActivity;
import chsj.chanyouji.fragments.traveldiary.activitys.UserDetailsActivity;
import chsj.chanyouji.fragments.traveldiary.model.Trips;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/9
 */


/**
 * 显示首页内容的适配器
 */
public class MyPFListViewAdapter extends BaseAdapter implements View.OnClickListener {

    private Context context;
    private List<Trips> datas;
    private Trips trips;


    public MyPFListViewAdapter(Context context, List<Trips> datas) {
        this.context = context;
        this.datas = datas;
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


        Log.d("ada", position + "");

        View ret = null;

        if (convertView != null) {
            ret = convertView;
        } else {
            ret = LayoutInflater.from(context).inflate(R.layout.fragment_traveldiary_list_view_item, parent, false);
        }

        ViewHolder holder = (ViewHolder) ret.getTag();
        if (holder == null) {
            holder = new ViewHolder();
            holder.imageView = (ImageView) ret.findViewById(R.id.fragment_traveldiary_list_view_img);
            holder.txtName = (TextView) ret.findViewById(R.id.fragment_traveldiary_list_view_name);
            holder.txtContent = (TextView) ret.findViewById(R.id.fragment_traveldiary_list_view_content);
            holder.userIcon = (ImageView) ret.findViewById(R.id.fragment_traveldiary_list_view_user_icon);

            ret.setTag(holder);
        }

        //获得数据，开始设置
        trips = datas.get(position);

        //设置题目
        holder.txtName.setText(trips.getName());

        String start = trips.getStart_date();
        int days = trips.getDays();
        int photoConunts = trips.getPhotos_count();
        String content = start.replace('-', '.') + "/" + days + "天," + photoConunts + "图";
        //设置开始时间，图片张数等.
        holder.txtContent.setText(content);

        //设置背景大图
        String coverPhoto = trips.getFront_cover_photo_url();
        x.image().bind(holder.imageView, coverPhoto);

        //设置用户头像
        if (trips.getUser_image() != null) {
            x.image().bind(holder.userIcon, trips.getUser_image());
        }


        holder.imageView.setTag(position);
        holder.userIcon.setTag(position);
        holder.imageView.setOnClickListener(this);
        holder.userIcon.setOnClickListener(this);


//        //利用5.0特性来处理字体颜色和背景颜色
//
//        Drawable drawable = holder.imageView.getDrawable();
//        Bitmap bitmap = null;
//        if (drawable != null) {
//            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//            bitmap = bitmapDrawable.getBitmap();
//        }
//
//
//        if (bitmap != null) {
//
//            //异步获得bitmap图片颜色值
//            final ViewHolder finalHolder = holder;
//            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//                @Override
//                public void onGenerated(Palette palette) {
//                    Palette.Swatch vibrant = palette.getVibrantSwatch();//有活力
//                    Palette.Swatch c = palette.getDarkVibrantSwatch();//有活力 暗色
//                    Palette.Swatch d = palette.getLightVibrantSwatch();//有活力 亮色
//                    Palette.Swatch f = palette.getMutedSwatch();//柔和
//                    Palette.Swatch a = palette.getDarkMutedSwatch();//柔和 暗色
//                    Palette.Swatch b = palette.getLightMutedSwatch();//柔和 亮色
//
//                    if (vibrant != null) {
//
//                        int color1 = vibrant.getBodyTextColor();
//                        int color2 = vibrant.getTitleTextColor();
//                        int color3 = vibrant.getRgb();
//
//                        finalHolder.txtName.setBackgroundColor(vibrant.getRgb());
//                        finalHolder.txtContent.setTextColor(vibrant.getTitleTextColor());
//                    }
//                }
//            });
//        }

        return ret;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            //跳转到游记详情页
            case R.id.fragment_traveldiary_list_view_img:
                int pos1 = (int) v.getTag();
                Trips tri1 = datas.get(pos1);
                double tripId = tri1.getId();

                Intent intentTrip = new Intent(context, TripDetailsActivity.class);
                intentTrip.putExtra("tripId", tripId);
                intentTrip.putExtra("coverPhoto", tri1.getFront_cover_photo_url());
                intentTrip.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intentTrip);
                break;

            //跳转到用户详情页
            case R.id.fragment_traveldiary_list_view_user_icon:
                int pos2 = (int) v.getTag();
                Trips tri2 = datas.get(pos2);
                Intent intent = new Intent(context.getApplicationContext(), UserDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("trip", tri2);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                break;
        }
    }


    private static class ViewHolder {
        ImageView imageView;
        TextView txtName;
        TextView txtContent;
        ImageView userIcon;
    }
}
