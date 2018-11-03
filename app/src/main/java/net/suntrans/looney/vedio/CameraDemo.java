package net.suntrans.looney.vedio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import net.suntrans.looney.R;
/**
 * Created by Looney on 2018/8/21.
 * Des:
 */
public class CameraDemo extends AppCompatActivity {

    private SurfaceView surfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        surfaceView = findViewById(R.id.surfaceView);
    }
}
