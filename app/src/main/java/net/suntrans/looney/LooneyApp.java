package net.suntrans.looney;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by Looney on 2018/3/16.
 * Des:
 */

public class LooneyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);

    }

    public static void main(String[] args){
        int[] a=new int[2];
        add(a);
        System.out.println(a[1]);
    }

    public static void add(int[] a){
        a[0]=1;
        a[1]=2;
    }
}
