package net.suntrans.st_devices;

import net.suntrans.st_devices.base.BasePresenter;
import net.suntrans.st_devices.sensus.ISensusPresenter;
import net.suntrans.st_devices.sensus.ISensusView;
import net.suntrans.st_devices.sensus.SensusData;

/**
 * Created by Looney on 2018/9/13.
 * Des:
 */
public class TestPresenter implements ISensusPresenter {
    private ISensusView mView;

    @Override
    public void getSensusData() {

    }

    @Override
    public ISensusView getView() {
        return mView;
    }

    @Override
    public void attachView(ISensusView view) {
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
