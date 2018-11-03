package net.suntrans.looney.window_manager;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import net.suntrans.looney.MainActivity;
import net.suntrans.looney.R;
import net.suntrans.looney.custom_view.views.DragFloatActionButton;

import java.io.Console;

/**
 * Created by Looney on 2018/3/16.
 * Des:
 */

public class WindowManagerDemo1 extends AppCompatActivity {

    private WindowManager windowManager;
    private final String TAG = this.getClass().getSimpleName();
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TAG);
        Log.d(TAG,"WindowManagerDemo1 onCreate");


        requestDrawOverLays();
    }

    private void addWindow() {
        startService(new Intent(this,SuspensionWindowService.class));
//        DragFloatActionButton floatingButton = new DragFloatActionButton(this);
//        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.WRAP_CONTENT,
//                0, 0,
//                PixelFormat.TRANSPARENT
//        );
//        // flag 设置 Window 属性
//        layoutParams.flags= WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
//        // type 设置 Window 类别（层级）
//        layoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;
//        layoutParams.gravity = Gravity.CENTER;
//        windowManager = getWindowManager();
//        windowManager.addView(floatingButton, layoutParams);
    }


    @TargetApi(Build.VERSION_CODES.M)
    public void requestDrawOverLays() {
        if (!Settings.canDrawOverlays(WindowManagerDemo1.this)) {
            Toast.makeText(this, "can not DrawOverlays", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + WindowManagerDemo1.this.getPackageName()));
            startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
        } else {
            // Already hold the SYSTEM_ALERT_WINDOW permission, do addview or something.
            addWindow();
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (!Settings.canDrawOverlays(this)) {
                Log.i(TAG,"Permission Denieddd by user.Please Check it in Settings");

                // SYSTEM_ALERT_WINDOW permission not granted...
                Toast.makeText(this, "Permission Denieddd by user.Please Check it in Settings", Toast.LENGTH_SHORT).show();
            } else {
               Log.i(TAG,"Permission Allowed");
                Toast.makeText(this, "Permission Allowed", Toast.LENGTH_SHORT).show();
                // Already hold the SYSTEM_ALERT_WINDOW permission, do addview or something.
                addWindow();
            }
        }
    }

}
