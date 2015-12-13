package chsj.chanyouji.fragments.traveldiary.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Author:甄强
 * Date:2015/10/23
 * Email:978823884@qq.com
 */

/**
 * 自动扩充的ListView，适用于ScrollView+ListView
 */
public class FullListView extends ListView {
    public FullListView(Context context) {
        super(context);
    }

    public FullListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {


        heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 4,
                MeasureSpec.AT_MOST);


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
