package net.suntrans.looney.vedio;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Looney on 2018/8/21.
 * Des:
 */
public class GLRender implements GLSurfaceView.Renderer {
    //控制旋转的角度
    private float rotate;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//关闭抗抖动
        gl.glDisable(GL10.GL_DITHER);
        //设置系统对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,
                GL10.GL_FASTEST);
        gl.glClearColor(0, 0, 0, 0);
        //设置阴影平滑模式
        gl.glShadeModel(GL10.GL_SMOOTH);
        //启动深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //设置深度测试的类型
        gl.glDepthFunc(GL10.GL_LEQUAL);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //设置3D视窗的大小及位置
        gl.glViewport(0, 0,
                width, height);
        //将当前矩阵模式设为投影矩形
        gl.glMatrixMode(GL10.GL_PROJECTION);
        //初始化单位矩阵
        gl.glLoadIdentity();
        //计算透视窗口的宽度高度比
        float ratio = (float) width / height;
        //调用此方法设置透视窗口的空间大小
        gl.glFrustumf(-ratio,
                ratio, -1, 1, 1, 10);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        //清除屏幕缓存和深度缓存
//        gl.glClear(GL10.GL_COLOR_BUFFER_BIT
//                | GL10.GL_DEPTH_BUFFER_BIT);
//        //启用顶点坐标数据
//        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//        //启用顶点颜色数据
//        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
//        //设置当前矩阵堆栈为模型堆栈
//        gl.glMatrixMode(GL10.GL_MODELVIEW);
//        //------绘制第一个图形-----
//        //重置当前的模型视图矩阵
//        gl.glLoadIdentity();
//        gl.glTranslatef(0.95f,
//                -0.8f, -1.5f);
//        //1⃣
//        //设置顶点位置数据
//        gl.glVertexPointer(3,
//                GL10.GL_FLOAT, 0,
//                PointData.floatBufferUtil(PointData.triangleData));
//        //设置顶点颜色数据
//        gl.glColorPointer(4,
//                GL10.GL_FIXED, 0,
//                PointData.intBufferUtil(PointData.triangleColor));
//        //根据顶点数据绘制平面图形
//        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
//
//        //-----绘制第二个图形-----
//        //重置当前的模型视图矩阵
//        gl.glLoadIdentity();
//        gl.glTranslatef(0.95f, 0.8f,
//                -1.5f);
//        //设置顶点位置数据
//        gl.glVertexPointer(3,
//                GL10.GL_FLOAT, 0,
//                PointData.floatBufferUtil(PointData.rectData));
//        //设置顶点颜色数据
//        gl.glColorPointer(4,
//                GL10.GL_FIXED, 0,
//                PointData.intBufferUtil(PointData.rectColor));
//        //更具顶点数据绘制平面图形
//        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//
//        //-----绘制第三个图形----
//        //重置当前的模型视图矩阵
//        gl.glLoadIdentity();
//        gl.glTranslatef(-0.95f, 0.8f,
//                -1.5f);
//        //设置顶点位置数据
//        gl.glVertexPointer(3,
//                GL10.GL_FLOAT, 0,
//                PointData.floatBufferUtil(PointData.rectData2));
//        //根据顶点数据绘制平面图形
//        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
//
//        //-----绘制第四个图形-----
//        //重置当前的模型视图矩阵
//        gl.glLoadIdentity();
//        gl.glTranslatef(-0.95f,
//                -0.8f, -1.5f);
//        //设置使用纯色填充 **需要注意: 使用纯色填充需要禁用顶点颜色数组
//        gl.glColor4f(1.0f, 0.2f, 0.2f, 0.0f);
//        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
//        //设置顶点位置数据
//        gl.glVertexPointer(3,
//                GL10.GL_FLOAT, 0,
//                PointData.floatBufferUtil(PointData.pentacle));
//        //根据顶点数据绘制图形
//        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 5);
//
//        //----绘制第五个图形---
//        //重置当前的模型视图矩阵
//        gl.glLoadIdentity();
//        gl.glTranslatef(0f, 0f,
//                -1.5f);
//        gl.glRotatef(rotate, 0f, 0.2f, 0f);
//        //设置顶点位置数据
//        gl.glVertexPointer(3,
//                GL10.GL_FLOAT, 0,
//                PointData.floatBufferUtil(TDPointData.taperVertices));
//        //启用顶点颜色组
//        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
//        //设置顶点颜色数据
//        gl.glColorPointer(4,
//                GL10.GL_FIXED, 0,
//                PointData.intBufferUtil(TDPointData.taperColors));
//        //按taperFacetsBuffer指定的面绘制三角形
//        ByteBuffer byteBuffer = PointData.byteBufferUtil(TDPointData.taperFacets);
//        gl.glDrawElements(GL10.GL_TRIANGLE_STRIP,
//                byteBuffer.remaining(), GL10.GL_UNSIGNED_BYTE,
//                byteBuffer);
//
//
//        //绘制结束
//        gl.glFinish();
//        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//        //旋转角度+1
//        rotate += 1;
    }
}
