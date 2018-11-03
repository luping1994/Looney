package net.suntrans.looney.cavans_demo.view;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Looney on 2018/5/7.
 * Des:
 */
public class CanvasView extends View{

    private static final String TAG = "CanvasView";
    private Paint mAxisPaint;
    private Matrix mMatrix;

    public CanvasView(Context context) {
        this(context,null);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mMatrix = new Matrix();
        mAxisPaint = new Paint();
        mAxisPaint.setAntiAlias(true);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        canvas.save();
        System.out.println("1."+canvas.getClipBounds());

        canvas.clipRect(new Rect(0,0,200,200));
        canvas.drawColor(Color.GREEN);
//        canvas.save();
        System.out.println("2."+canvas.getClipBounds());

        canvas.restore();
        System.out.println("3."+canvas.getClipBounds());
        canvas.drawColor(Color.YELLOW);

//        canvas.restore();
//        System.out.println("4."+canvas.getClipBounds());




    }

    private void drawXAlias(){

    }
}
