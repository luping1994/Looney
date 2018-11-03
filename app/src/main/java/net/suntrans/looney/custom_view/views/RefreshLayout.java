package net.suntrans.looney.custom_view.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by Looney on 2018/9/11.
 * Des:
 */
public class RefreshLayout extends ScrollView {
    private static final int INVALID_POINTER = -1;
    private static final String LOG_TAG = "RefreshLayout";

    private int mActivePointerId = INVALID_POINTER;//当前手指点击的有效id
    private boolean mIsBeingDragged;//是否已经准备拖拽了
    private static final float DRAG_RATE = .5f;//拖拽系数

    private int mTouchSlop;

    private float mInitialMotionY;
    private float mInitialDownY;
    private float mCurrentOffset = 0;
    private Scroller mScroller;
    private float mLastPointerY;

    public RefreshLayout(Context context) {
        this(context, null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        setWillNotDraw(false);
        mScroller = new Scroller(context);
    }



    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getActionMasked();
        int pointerIndex = -1;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.w(LOG_TAG,"ACTION_DOWN");
                mActivePointerId = ev.getPointerId(0);
                mIsBeingDragged = false;
                break;

            case MotionEvent.ACTION_MOVE: {
//                Log.i(LOG_TAG,"ACTION_MOVE");
                pointerIndex = ev.findPointerIndex(mActivePointerId);
                if (pointerIndex < 0) {
                    Log.e(LOG_TAG, "Got ACTION_MOVE event but have an invalid active pointer id.");
                    return false;
                }

                final float y = ev.getY(pointerIndex);
                startDragging(y);

                if (mIsBeingDragged) {
                    final float overscrollTop = (y - mInitialMotionY) * DRAG_RATE;
                    if (overscrollTop > 0) {
                        //开始下拉
                        mCurrentOffset = overscrollTop;
                        moveSpinner(overscrollTop);
                    } else {
                        return false;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_POINTER_DOWN: {
                    Log.e(LOG_TAG,"ACTION_POINTER_DOWN");
                pointerIndex = ev.getActionIndex();
                mLastPointerY = ev.getY(pointerIndex);
                if (pointerIndex < 0) {
                    Log.e(LOG_TAG,
                            "Got ACTION_POINTER_DOWN event but have an invalid action index.");
                    return false;
                }
                mActivePointerId = ev.getPointerId(pointerIndex);
                break;
            }

            case MotionEvent.ACTION_POINTER_UP:
                System.out.println("ACTION_POINTER_UP");
                onSecondaryPointerUp(ev);
                break;

            case MotionEvent.ACTION_UP: {
                System.out.println("ACTION_UP");
                pointerIndex = ev.findPointerIndex(mActivePointerId);
                if (pointerIndex < 0) {
                    Log.e(LOG_TAG, "Got ACTION_UP event but don't have an active pointer id.");
                    return false;
                }

                if (mIsBeingDragged) {
                    final float y = ev.getY(pointerIndex);
                    final float overscrollTop = (y - mInitialMotionY) * DRAG_RATE;
                    mIsBeingDragged = false;
                    finishSpinner(overscrollTop);
                }
                mActivePointerId = INVALID_POINTER;
                return false;
            }
            case MotionEvent.ACTION_CANCEL:
                return false;
        }

        return true;
    }

    /**
     * 开始下拉
     *
     * @param overscrollTop
     */
    private void moveSpinner(float overscrollTop) {
        layout(getLeft(), (int) overscrollTop, getRight(), getBottom());
    }

    /**
     * 结束下拉
     *
     * @param overscrollTop
     */
    private void finishSpinner(float overscrollTop) {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, overscrollTop,
                0);
        ta.setDuration(300);
        this.startAnimation(ta);
        // 设置回到正常的布局位置
        layout(getLeft(), 0, getRight(), getBottom());
        mCurrentOffset = 0;

    }

    /**
     * 准备拖拽
     *
     * @param y
     */
    private void startDragging(float y) {
        final float yDiff = y - mInitialDownY;
        if (yDiff > mTouchSlop && !mIsBeingDragged) {
            mInitialMotionY = mInitialDownY + mTouchSlop;
            mIsBeingDragged = true;
        }
    }

    /**
     * 第二个手指按下
     *
     * @param ev
     */
    private void onSecondaryPointerUp(MotionEvent ev) {
        final int pointerIndex = ev.getActionIndex();
        final int pointerId = ev.getPointerId(pointerIndex);
        if (pointerId == mActivePointerId) {
            // This was our active pointer going up. Choose a new
            // active pointer and adjust accordingly.
            final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
            mActivePointerId = ev.getPointerId(newPointerIndex);
        }
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        final int action = ev.getActionMasked();
        int pointerIndex;


        if (!isEnabled()) {
            // Fail fast if we're not in a state where a swipe is possible
            return false;
        }

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = ev.getPointerId(0);
                mIsBeingDragged = false;

                pointerIndex = ev.findPointerIndex(mActivePointerId);
                if (pointerIndex < 0) {
                    return false;
                }
                mInitialDownY = ev.getY(pointerIndex);
                break;

            case MotionEvent.ACTION_MOVE:
                if (mActivePointerId == INVALID_POINTER) {
                    Log.e(LOG_TAG, "Got ACTION_MOVE event but don't have an active pointer id.");
                    return false;
                }

                pointerIndex = ev.findPointerIndex(mActivePointerId);
                if (pointerIndex < 0) {
                    return false;
                }
                final float y = ev.getY(pointerIndex);
                startDragging(y);
                break;

            case MotionEvent.ACTION_POINTER_UP:
                onSecondaryPointerUp(ev);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mIsBeingDragged = false;
                mActivePointerId = INVALID_POINTER;
                break;
        }

        return mIsBeingDragged;
    }

    GestureDetector.OnGestureListener listener = new GestureDetector.OnGestureListener(){

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
    };
}
