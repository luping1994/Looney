package net.suntrans.looney.custom_view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.suntrans.looney.R;
import net.suntrans.looney.custom_view.views.ProgressView;

/**
 * Created by Looney on 2018/3/16.
 * Des:
 */

public class Demo1Activity extends AppCompatActivity {


    private ProgressView jj;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_view_demo1);
        jj = findViewById(R.id.jj);
        jj.startScan();
        Activity activity = new Activity();

    }

    @Override
    protected void onDestroy() {
        jj.stopScan();
        super.onDestroy();
    }
}
