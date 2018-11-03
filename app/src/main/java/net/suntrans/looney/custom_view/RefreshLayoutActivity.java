package net.suntrans.looney.custom_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import net.suntrans.looney.R;

/**
 * Created by Looney on 2018/9/6.
 * Des:
 */
public class RefreshLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refreshlayout);
    }

    @Override
    protected void onResume() {
        System.out.println("onResume");

        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("onPause");
        super.onPause();
    }

    @Override
    protected void onStart() {
        System.out.println("onStart");
        super.onStart();
    }

    public void start(View view) {
        DialogFragment fragment =new DialogFragment();
        fragment.show(getSupportFragmentManager(),"test");
    }
}
