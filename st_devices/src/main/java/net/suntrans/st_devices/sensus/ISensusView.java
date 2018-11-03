package net.suntrans.st_devices.sensus;

import net.suntrans.st_devices.base.IBaseView;

/**
 * Created by Looney on 2018/9/13.
 * Des:
 */
public interface ISensusView extends IBaseView {
    void setPresenter(ISensusPresenter presenter);

    void getSensusData();

    void onGetSensusData(SensusData data);
}
