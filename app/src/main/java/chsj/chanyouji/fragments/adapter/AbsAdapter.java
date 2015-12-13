package chsj.chanyouji.fragments.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chsj.chanyouji.R;

/**
 * ProjectName : chsj.chanyouji.fragments.adapter
 * Created by : zhaoQiang
 * Email : zhaoq_hero163.com
 * On 2015/11/12 // 10:26
 */

/**
 * 适配器的  万能适配 可回调
 * @param <T>
 */
public abstract class AbsAdapter<T> extends BaseAdapter{

    private Context context;
    private List<T> datas;
    private int layoutResId;

    public AbsAdapter(Context context, List<T> datas, int layoutResId) {
        this.context = context;
        this.datas = datas;
        this.layoutResId = layoutResId;
    }

    @Override
    public int getCount() {
        int ret =0;
        if (datas!=null){
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
        ViewHolder vHolder = null;
        if (convertView==null){
            convertView = LayoutInflater.from(context).inflate(layoutResId,parent,false);
            vHolder = new ViewHolder(convertView);

            convertView.setTag(vHolder);
        }else{
            vHolder = (ViewHolder) convertView.getTag();
        }

        bindView(vHolder,datas.get(position));

        return convertView;
    }

    public abstract void bindView(ViewHolder vHolder,T data);

    public static class ViewHolder{
        private Map<Integer,View> cacheViews;
        private View itemView;

        public ViewHolder(View itemView) {
            this.itemView = itemView;
            cacheViews=new HashMap<Integer,View>();
        }
        //查找指定Id的item中的子控件：
        public View getView(int id) {
            View v = cacheViews.get(id);
            if(v==null){
                v=itemView.findViewById(id);
                if(v!=null){
                    cacheViews.put(id, v);
                }
            }
            return v;
        }
    }
}
