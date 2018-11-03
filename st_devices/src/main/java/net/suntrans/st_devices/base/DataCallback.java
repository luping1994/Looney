package net.suntrans.st_devices.base;

/**
 * Created by Looney on 2018/9/13.
 * Des:
 */
public interface DataCallback<T> {
    void onDataGet(T data);
    void onError(String msg);
}
