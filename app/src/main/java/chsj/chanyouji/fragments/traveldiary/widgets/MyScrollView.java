package chsj.chanyouji.fragments.traveldiary.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * ProjectName: com.chsj.com
 * Created By:chsj
 * Date:2015/11/11
 */
public class MyScrollView extends ScrollView {


    private OnScrollListener onScrollListener;

    private int lastScrollY;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setOnScrollListener(OnScrollListener scrollListener) {
        this.onScrollListener = scrollListener;
    }


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int scrollY = MyScrollView.this.getScrollY();
            if (lastScrollY != scrollY) {
                lastScrollY = scrollY;
                handler.sendMessageDelayed(handler.obtainMessage(), 5);
            }
            if (onScrollListener != null) {
                onScrollListener.onScroll(scrollY);
            }
        }
    };


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (onScrollListener != null) {
            onScrollListener.onScroll(lastScrollY = this.getScrollY());
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                handler.sendMessageDelayed(handler.obtainMessage(), 20);
                break;
        }

        return super.onTouchEvent(ev);
    }

    public interface OnScrollListener {
        public void onScroll(int scrollY);
    }
}
