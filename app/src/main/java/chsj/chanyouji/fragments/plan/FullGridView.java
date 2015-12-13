package chsj.chanyouji.fragments.plan;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * ProjectName : com.chsj.chanyouji
 * Created by : whl
 * 2015/11/10
 */
public class FullGridView extends GridView {
    public FullGridView(Context context) {
        super(context);
    }

    public FullGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    重写ListView的OnMeasure方法是gridview占满屏幕


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }
}
