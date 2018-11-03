package net.suntrans.st_devices.base;


import android.support.v4.app.Fragment;

/**
 * Created by Looney on 2018/9/13.
 * Des:
 */
public abstract class BaseFragment extends Fragment implements IBaseView {

    @Override
    public void showLoading() {

    }

    @Override
    public void showErrorDialog(String msg) {

    }

    @Override
    public void showToast(String msg) {

    }
}
