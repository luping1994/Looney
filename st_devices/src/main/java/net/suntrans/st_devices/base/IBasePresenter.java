package net.suntrans.st_devices.base;

/**
 * Created by Looney on 2018/9/4.
 * Des:
 */
public interface IBasePresenter<View extends IBaseView> {
    View getView();

    void attachView(View view);

    void detachView();

    void onError(String msg);
}

