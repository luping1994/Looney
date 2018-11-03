package net.suntrans.st_devices.base;

/**
 * Created by Looney on 2018/9/13.
 * Des:
 */
public interface IBaseView {
    void showLoading();
    void showErrorDialog(String msg);
    void showToast(String msg);
}
