package net.suntrans.looney.custom_view.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Looney on 2018/8/4.
 * Des:
 */
public class MyLinerlayout extends LinearLayout {
    public MyLinerlayout(Context context) {
        super(context);
    }

    public MyLinerlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLinerlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
