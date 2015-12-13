package chsj.chanyouji.fragments.adapter;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import chsj.chanyouji.MainActivity;

/**
 * ProjectName : chsj.chanyouji.fragments.adapter
 * Created by : zhaoQiang
 * Email : zhaoq_hero163.com
 * On 2015/11/10 // 16:37
 */

/**
 * 适配  selector Activity中expandablelistView 中的内容适配器
 */
public class MyExpandableAdapter extends BaseExpandableListAdapter{

    private Context context;
    private List<String> parent;
    private Map<String,List<String>> map;

    public MyExpandableAdapter(Context context, List<String> parent, Map<String, List<String>> map) {
        this.context = context;
        this.parent = parent;
        this.map = map;
    }

    /**
     * 得到  item 需要关联的数据：
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = parent.get(groupPosition); //获取所在分组
        return (map.get(key)).get(childPosition);  //获取  在组内的  对象
    }

    /**
     * 得到  item的  id
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //获取子  视图的 布局
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        String key = this.parent.get(groupPosition);
        String info = map.get(key).get(childPosition);
        if (convertView == null) {
            convertView = new TextView(context);
        }

        ViewGroup.LayoutParams lp =
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

        TextView tv = (TextView) convertView;
        tv.setLayoutParams(lp);
        tv.setTextSize(25);
        tv.setPadding(50,5,0,5);
        tv.setText(info);

        return tv;
    }

    /**
     * 获取当前   父 item 的子item  的个数
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        String key = parent.get(groupPosition);//获取当前 分组的key
        int size = map.get(key).size();
        return size;
    }

    /**
     * 获取当前 父 item的个数：
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return parent.get(groupPosition);
    }

    /**
     * 获取 组的  个数
     * @return
     */
    @Override
    public int getGroupCount() {
        return parent.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 设置 父　item组件
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(context);
        }


        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        TextView tv = (TextView) convertView;
        tv.setLayoutParams(lp);
        tv.setTextSize(30);
        tv.setPadding(20,5, 0, 5);
        tv.setText(this.parent.get(groupPosition));

        return tv;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
