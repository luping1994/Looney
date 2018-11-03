package net.suntrans.looney.vedio;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Looney on 2018/9/11.
 * Des:
 */
public class Utils {

    public static void showToast(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

}
