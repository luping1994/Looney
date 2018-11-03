package net.suntrans.st_devices.sensus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.suntrans.st_devices.R;
import net.suntrans.st_devices.base.BaseFragment;

/**
 * Created by Looney on 2018/9/13.
 * Des:
 */
public class SensusFragment extends BaseFragment implements ISensusView {
    private ISensusPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_env_detail,null,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView();
    }

    private void initView() {

    }


    @Override
    public void getSensusData() {
        presenter.getSensusData();
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }

    @Override
    public void onGetSensusData(SensusData data) {
        refreshUI(data);
    }

    @Override
    public void setPresenter(ISensusPresenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    private void refreshUI(SensusData data) {

    }
}
