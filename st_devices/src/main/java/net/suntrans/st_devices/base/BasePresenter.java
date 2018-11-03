package net.suntrans.st_devices.base;


/**
 * Created by Looney on 2018/9/3.
 * Des:
 */
public class BasePresenter implements IBasePresenter {

    private IBaseView mView;

    @Override
    public IBaseView getView() {
        return mView;
    }

    @Override
    public void attachView(IBaseView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public void onError(String msg) {
        mView.showErrorDialog(msg);
    }
}
