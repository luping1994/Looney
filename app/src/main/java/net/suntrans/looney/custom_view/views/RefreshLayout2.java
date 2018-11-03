package net.suntrans.looney.custom_view.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by Looney on 2018/9/13.
 * Des:
 */
public class RefreshLayout2 extends LinearLayout {

    private Scroller mScroller;
    private GestureDetector mGestureDetector;

    public RefreshLayout2(Context context) {
        this(context,null);
    }

    public RefreshLayout2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshLayout2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
        mGestureDetector = new GestureDetector(context,new GestureListenerImpl());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }

    class GestureListenerImpl implements GestureDetector.OnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return false;
        }
    }
}
