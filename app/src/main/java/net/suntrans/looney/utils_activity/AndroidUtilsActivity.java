package net.suntrans.looney.utils_activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.util.AppUtils;

import java.io.File;

/**
 * Created by Looney on 2018/3/16.
 * Des:
 */

public class AndroidUtilsActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        AppUtils.installAppSilent(new File(""));
    }
}
