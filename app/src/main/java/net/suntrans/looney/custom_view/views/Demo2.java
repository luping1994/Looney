package net.suntrans.looney.custom_view.views;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.LinearLayout;
import net.suntrans.looney.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Looney on 2018/5/7.
 * Des:
 */
public class Demo2 extends LinearLayout {

    private List<DataSet> dataSets = new ArrayList<>();
    private Paint mAxisPaint;
    private Paint mOutSizePaint;
    private Path mPath = new Path();
    private int height;
    private int width;

    private int barHeight = 10;
    private int barWidth = 40;
    private int count = 60;

    private int startArg = 0;
    private Matrix matrix = new Matrix();
    private boolean isScan = true;

    float postion1 = 0f;
    float position2 = 1f;
    private Bitmap bitmap1;

    public Demo2(Context context) {
        this(context, null);
    }

    public Demo2(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Demo2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Camera camera ;
    private void init() {
        for (int i = 0; i < 6; i++) {
            Random random = new Random(i);
            DataSet dataSet = new DataSet();
            dataSet.x = i + "";
            dataSet.y = random.nextFloat();
            dataSets.add(dataSet);
        }
        mAxisPaint = new Paint();
        mAxisPaint.setAntiAlias(true);
        mAxisPaint.setAntiAlias(true);
        mAxisPaint.setStrokeWidth(barHeight);
        mAxisPaint.setStyle(Paint.Style.STROKE);
        mAxisPaint.setStrokeCap(Paint.Cap.ROUND);


        mOutSizePaint = new Paint();
        mOutSizePaint.setAntiAlias(true);
        mOutSizePaint.setStrokeWidth(barHeight);
        mOutSizePaint.setStyle(Paint.Style.STROKE);
        mOutSizePaint.setColor(Color.parseColor("#e2e2e2"));
        mOutSizePaint.setStrokeCap(Paint.Cap.ROUND);

        camera =  new Camera();
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        animate().translationX(50)
                .setDuration(500)
                .setInterpolator(new AccelerateDecelerateInterpolator());
        setTranslationX(10);

    }


    private class HSL implements TypeEvaluator<Integer> {
        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
//            Color.colorToHSV();
            return null;
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        camera.save(); // 保存 Camera 的状态
        camera.rotateX(30); // 旋转 Camera 的三维空间
        camera.applyToCanvas(canvas); // 把旋转投影到 Canvas
        camera.restore(); // 恢复 Camera 的状态

        canvas.drawBitmap(bitmap1,0,0,mAxisPaint);

        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        height = getMeasuredHeight();
        width = getMeasuredWidth();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);//宽的测量大小，模式
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);//高的测量大小，模式
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

//        String s=heightSpecMode==MeasureSpec.AT_MOST?"MeasureSpec.AT_MOST":heightSpecMode==MeasureSpec.EXACTLY?"MeasureSpec.EXACTLY":"MeasureSpec.UNSPECIFIED";
//        String s2=widthSpecMode==MeasureSpec.AT_MOST?"MeasureSpec.AT_MOST":widthSpecMode==MeasureSpec.EXACTLY?"MeasureSpec.EXACTLY":"MeasureSpec.UNSPECIFIED";
//        System.out.println("heightSpecMode:"+s);
//        System.out.println("widthSpecMode:"+s2);

        int w = widthSpecSize;   //定义测量宽，高(不包含测量模式),并设置默认值，查看View#getDefaultSize可知
        int h = heightSpecSize;

        //处理wrap_content的几种特殊情况
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            w = 200;  //单位是px
            h = 200;
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            //只要宽度布局参数为wrap_content， 宽度给固定值200dp(处理方式不一，按照需求来)
            w = 200;
            //按照View处理的方法，查看View#getDefaultSize可知
            h = heightSpecSize;
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            w = widthSpecSize;
            h = 200;
        }
        //给两个字段设置值，完成最终测量
        setMeasuredDimension(w, h);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public static class DataSet {
        public String x = "";
        public float y = 0;
    }
}
