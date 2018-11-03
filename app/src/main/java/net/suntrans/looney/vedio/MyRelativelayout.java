package net.suntrans.looney.vedio;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Looney on 2018/8/22.
 * Des:
 */
public class MyRelativelayout extends RelativeLayout {
    public MyRelativelayout(Context context) {
        super(context);
    }

    public MyRelativelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRelativelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }
}
