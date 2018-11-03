package net.suntrans.looney.custom_view.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import net.suntrans.looney.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Looney on 2018/5/7.
 * Des:
 */
public class ProgressView extends View {

    private List<DataSet> dataSets = new ArrayList<>();
    private Paint mAxisPaint;
    private Paint mOutSizePaint;
    private Path mPath = new Path();
    private Bitmap bitmap;
    private int height;
    private int width;

    private int barHeight = 10;
    private int barWidth = 40;
    private int count = 60;

    private int startArg = 0;
    private Matrix matrix = new Matrix();
    private ScanThread scanThread;
    private boolean isScan = true;

    float postion1 = 0f;
    float position2 = 1f;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


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

        scanThread = new ScanThread();
        mOutSizePaint = new Paint();
        mOutSizePaint.setAntiAlias(true);
        mOutSizePaint.setStrokeWidth(barHeight);
        mOutSizePaint.setStyle(Paint.Style.STROKE);
        mOutSizePaint.setColor(Color.parseColor("#e2e2e2"));
        mOutSizePaint.setStrokeCap(Paint.Cap.ROUND);

    }

    public void startScan() {
        scanThread.start();
    }

    public void stopScan() {
        isScan = false;
    }

    protected class ScanThread extends Thread {
        @Override
        public void run() {
            while (isScan) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        matrix = new Matrix();
                        matrix.preRotate(startArg, 0, 0);
                        startArg += 360 / count;
                        if (startArg >= 360) {
                            startArg = 0;
                        }
                        postion1 = startArg / 360;
                        position2 = postion1 + 0.25f;
//                      System.out.println(startArg);
                        invalidate();
                    }
                });
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {

//        mAxisPaint.setStyle(Paint.Style.FILL);
        mAxisPaint.setAntiAlias(true);
        mPath.moveTo(100, 0);
//        mPath.quadTo(325,550,750,0); 二阶贝塞尔曲线
//        mPath.cubicTo(225,550,525,450,750,0); 三阶贝塞尔曲线
//        mPath.close(); // 使用 close() 封闭子图形。等价于 path.lineTo(100, 100)
//        canvas.drawPath(mPath,mAxisPaint);
//        canvas.drawCircle(250,250,100,mAxisPaint);
//        canvas.drawCircle(300,250,100,mAxisPaint);
        //path画圆形
//        mPath.addCircle(250,250,100, Path.Direction.CCW);
//        mPath.addCircle(300,250,100, Path.Direction.CCW);
//        mPath.setFillType(Path.FillType.EVEN_ODD);
//        canvas.drawPath(mPath,mAxisPaint);

        //画bitMap
//        Matrix matrix = new Matrix();
//        matrix.postRotate(90);
        if (bitmap == null)
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        canvas.drawBitmap(bitmap, matrix,mAxisPaint);
//        canvas.drawBitmap(bitmap, 100,100,mAxisPaint);
//        canvas.save();
//        canvas.translate(300,0);
//        canvas.drawBitmap(bitmap, 100,100,mAxisPaint);
//        canvas.restore();
//        canvas.drawBitmap(bitmap, 100,300,mAxisPaint);
        int radius = Math.min((width / 2), (height / 2));

        canvas.translate(width / 2, height / 2);

//        canvas.save();
//        canvas.drawArc(rectF,0,90,false,mAxisPaint);
//        canvas.drawRect(width-barHeight,height/2-barWidth,width,height/2,mAxisPaint);
//        canvas.drawLine(width/2+radius-barWidth,height/2,width/2+radius,height/2,mOutSizePaint);
        Matrix matrix1;
//        for(int i=1;i<count;i++){
//            matrix1=new Matrix();
//            matrix1.postRotate(360/count, width/2, height/2);
//            canvas.concat(matrix1);
//            canvas.drawLine(width/2+radius-barWidth,height/2,width/2+radius,height/2,mOutSizePaint);
//        }
//        canvas.restore();
        float per = 360 / count;
        for (int i = 0; i < count; i++) {
            float degree = i * per;
            degree = (float) (Math.PI * degree / 180);
            PointF start = new PointF((float) ((radius - barWidth) * Math.cos(degree)), (float) ((radius - barWidth) * Math.sin(degree)));
            PointF end = new PointF((float) (radius * Math.cos(degree)), (float) (radius * Math.sin(degree)));
//            System.out.println("degree="+degree+"->"+"start:("+start.x+","+start.y+")->"+"end:("+end.x+","+end.y+")");
            canvas.drawLine(start.x, start.y, end.x, end.y, mOutSizePaint);
        }

//        float s=1*per;
//        s = (float) (Math.PI*s/180);
//
//        float e=1*per;
//        e = (float) (Math.PI*e/180);
//        LinearGradient gradient = new LinearGradient((float)( (radius-barWidth)*Math.cos(s)),(float)((radius-barWidth)*Math.sin(s)),
//                (float)(radius*Math.cos(e)),(float)(radius*Math.sin(e)),Color.TRANSPARENT,Color.BLUE, Shader.TileMode.REPEAT);

        int[] colors = new int[]{Color.TRANSPARENT, Color.parseColor("#566af4")};
        float[] positions = new float[]{postion1, position2};
        SweepGradient sweepGradient = new SweepGradient(0, 0, colors, positions);
        SweepGradient sweepGradient1 = new SweepGradient(width / 2, height / 2,
                new int[]{Color.TRANSPARENT, Color.parseColor("#ff0000")}, new float[]{0.8f, 1f});

        mAxisPaint.setShader(sweepGradient);
//        canvas.drawCircle(width/2,height/2,radius,mAxisPaint);

//        int left = width/2-radius;
//        int top =height/2-radius;
//        int right = width/2+radius;
//        int bottom = height/2+radius;
//        RectF rectF = new RectF(left,top,right,bottom);
        canvas.save();
        canvas.concat(matrix);
//        canvas.drawLine(radius-barWidth,0,radius,0,mAxisPaint);

        for (int i = 1; i < 20; i++) {

            float degree = i * per;

            degree = (float) (Math.PI * degree / 180);

            PointF start = new PointF((float) ((radius - barWidth) * Math.cos(degree)), (float) ((radius - barWidth) * Math.sin(degree)));

            PointF end = new PointF((float) (radius * Math.cos(degree)), (float) (radius * Math.sin(degree)));

            canvas.drawLine(start.x, start.y, end.x, end.y, mAxisPaint);

        }

        canvas.restore();

        super.onDraw(canvas);

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


    public static class DataSet {
        public String x = "";
        public float y = 0;
    }
}
