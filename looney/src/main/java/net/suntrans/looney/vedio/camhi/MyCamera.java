package net.suntrans.looney.vedio.camhi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.hichip.control.HiCamera;
import com.hichip.push.HiPushSDK;
import com.hichip.tools.Packet;

import net.suntrans.looney.vedio.base.DatabaseManager;
import net.suntrans.looney.vedio.utils.SharePreUtils;

public class MyCamera extends HiCamera {
    private String nikeName = "";
    private int videoQuality = HiDataValue.DEFAULT_VIDEO_QUALITY;// 1: �Ǳ��� 0: �Ǹ���
    private int alarmState = HiDataValue.DEFAULT_ALARM_STATE;
    private int pushState = HiDataValue.DEFAULT_PUSH_STATE;
    private boolean hasSummerTimer;
    private boolean isFirstLogin = true;
    private byte[] bmpBuffer = null;
    public Bitmap snapshot = null;
    private long lastAlarmTime;
    private boolean isSetValueWithoutSave = false;
    private int style;
    private String serverData;
    public int isSystemState = 0;// 1������ 2�ָ����������� 3��������
    public boolean alarmLog = false;// ����С����Ƿ���ʾ
    public int mInstallMode = 0; // 0-Ϊ���۶�װ; 1-��װ
    public boolean isFirst = false;// �����Ƿ��һ�ν���(ָ������)
    public boolean isChecked; // �������ѡ��״̬
    public boolean isWallMounted = false;// �Ƿ������۱�װ�¾�ͷ
    public String uid;
    private Context mContext;
    public int u32Resolution = 0;
    public boolean mIsReceived_4179 = false;

    public MyCamera(Context context, String nikename, String uid, String username, String password) {
        super(context, uid, username, password);
        this.nikeName = nikename;
        this.uid = uid;
        this.mContext = context;
    }

    public boolean isAlarmLog() {
        return alarmLog;
    }

    public void setAlarmLog(boolean alarmLog) {
        this.alarmLog = alarmLog;
    }

    public void saveInDatabase(Context context) {
        DatabaseManager db = new DatabaseManager(context);
        db.addDevice(nikeName, getUid(), getUsername(), getPassword(), videoQuality, alarmState, pushState);
    }

    public void setSummerTimer(boolean hasSummerTimer) {
        this.hasSummerTimer = hasSummerTimer;
    }

    public boolean getSummerTimer() {
        return this.hasSummerTimer;
    }

    public void setServerData(String serverData) {
        this.serverData = serverData;
    }

    public String getServerData() {
        return this.serverData;
    }

    public void saveInCameraList() {
        if (!HiDataValue.CameraList.contains(this)) {
            HiDataValue.CameraList.add(this);
        }
    }

    public void deleteInCameraList() {
        HiDataValue.CameraList.remove(this);
        this.unregisterIOSessionListener();
        this.unregisterDownloadListener();
        this.unregisterPlayStateListener();
        this.unregisterYUVDataListener();
        snapshot = null;
    }

    public long getLastAlarmTime() {
        return lastAlarmTime;
    }

    public void setLastAlarmTime(long lastAlarmTime) {
        this.lastAlarmTime = lastAlarmTime;
    }

    public void updateInDatabase(Context context) {
        DatabaseManager db = new DatabaseManager(context);
        db.updateDeviceByDBID(nikeName, getUid(), getUsername(), getPassword(), videoQuality,
                HiDataValue.DEFAULT_ALARM_STATE, pushState, getServerData());

        isSetValueWithoutSave = false;
    }

    public void updateServerInDatabase(Context context) {
        DatabaseManager db = new DatabaseManager(context);
        db.updateServerByUID(getUid(), getServerData());

        isSetValueWithoutSave = false;
    }

    public void deleteInDatabase(Context context) {
        DatabaseManager db = new DatabaseManager(context);
        db.removeDeviceByUID(this.getUid());
        db.removeDeviceAlartEvent(this.getUid());
    }

    public int getAlarmState() {
        return alarmState;
    }

    public void setAlarmState(int alarmState) {
        this.alarmState = alarmState;
    }

    public int getPushState() {
        return pushState;
    }

    public void setPushState(int pushState) {
        this.pushState = pushState;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getStyle() {

        return style;
    }

    public int getVideoQuality() {
        return videoQuality;
    }

    public void setVideoQuality(int videoQuality) {
        this.videoQuality = videoQuality;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    private int curbmpPos = 0;

    public boolean reciveBmpBuffer(byte[] byt) {
        if (byt.length < 10) {
            return false;
        }
        if (bmpBuffer == null) {
            curbmpPos = 0;
            int buflen = Packet.byteArrayToInt_Little(byt, 0);
            if (buflen <= 0) {
                return false;
            }
            bmpBuffer = new byte[buflen];
        }
        int datalen = Packet.byteArrayToInt_Little(byt, 4);
        if (curbmpPos + datalen <= bmpBuffer.length)
            System.arraycopy(byt, 10, bmpBuffer, curbmpPos, datalen);
        curbmpPos += (datalen);
        short flag = Packet.byteArrayToShort_Little(byt, 8);
        if (flag == 1) {
            createSnapshot();
            return true;
        }
        return false;
    }

    private void createSnapshot() {
        Bitmap snapshot_temp = BitmapFactory.decodeByteArray(bmpBuffer, 0, bmpBuffer.length);
        if (snapshot_temp != null)
            snapshot = snapshot_temp;

        bmpBuffer = null;
        curbmpPos = 0;

    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    public boolean isSetValueWithoutSave() {
        return isSetValueWithoutSave;
    }

    @Override
    public void connect() {
        // int i = 0;
        // if (getUid() != null && getUid().length() > 4) {
        // for (String str : HiDataValue.limit) {
        // i++;
        // String temp = getUid().substring(0, str.length());
        // if (temp.equalsIgnoreCase(str)) {
        // break;
        // }
        // }
        // if (i == HiDataValue.limit.length) {
        // super.connect();
        // return;
        // }
        // return;
        // } else {
        // return;
        // }
        if (getUid() != null && getUid().length() > 4) {
            // String temp = getUid().substring(0, 5);
            String str = getUid().substring(0, 4);
            // if (temp.equalsIgnoreCase("FDTAA") || str.equalsIgnoreCase("DEAA") ||
            // str.equalsIgnoreCase("AAES")) {
            if (str.equalsIgnoreCase("AAES")) {
                return;
            } else {
                super.connect();
                return;
            }
        } else {
            return;
        }
    }

    public interface OnBindPushResult {
        public void onBindSuccess(MyCamera camera);

        public void onBindFail(MyCamera camera);

        public void onUnBindSuccess(MyCamera camera);

        public void onUnBindFail(MyCamera camera);
    }

    private OnBindPushResult onBindPushResult;

    public HiPushSDK push;
    private HiPushSDK.OnPushResult pushResult = new HiPushSDK.OnPushResult() {
        @Override
        public void pushBindResult(int subID, int type, int result) {
            isSetValueWithoutSave = true;
            Log.e("final_Bind_addrss", push.getPushServer());
            if (type == HiPushSDK.PUSH_TYPE_BIND) {
                if (HiPushSDK.PUSH_RESULT_SUCESS == result) {
                    pushState = subID;
                    if (onBindPushResult != null)
                        onBindPushResult.onBindSuccess(MyCamera.this);
                } else if (HiPushSDK.PUSH_RESULT_FAIL == result || HiPushSDK.PUSH_RESULT_NULL_TOKEN == result) {
                    if (onBindPushResult != null)
                        onBindPushResult.onBindFail(MyCamera.this);
                }
            } else if (type == HiPushSDK.PUSH_TYPE_UNBIND) {
                if (HiPushSDK.PUSH_RESULT_SUCESS == result) {
                    if (onBindPushResult != null)
                        onBindPushResult.onUnBindSuccess(MyCamera.this);
                } else if (HiPushSDK.PUSH_RESULT_FAIL == result) {
                    if (onBindPushResult != null)
                        onBindPushResult.onUnBindFail(MyCamera.this);
                }

            }

        }
    };

    public void bindPushState(boolean isBind, OnBindPushResult bindPushResult) {
        if (HiDataValue.XGToken == null) {
            return;
        }
//		Log.e("=====", "XGToken=" + HiDataValue.XGToken + "---" + "company=" + HiDataValue.company + "ser"
//				+ this.getServerData());

        /* ��ַ��� ���ʱ �þɵķ����� */
        if (!isBind && this.getServerData() != null && !this.getServerData().equals(HiDataValue.CAMERA_ALARM_ADDRESS)) {
            push = new HiPushSDK(HiDataValue.XGToken, getUid(), HiDataValue.company, pushResult, this.getServerData());
        } else if (this.getCommandFunction(CamHiDefines.HI_P2P_ALARM_ADDRESS_SET) && !handSubXYZ()) {
            if (handSubWTU()) {
                push = new HiPushSDK(HiDataValue.XGToken, getUid(), HiDataValue.company, pushResult,
                        HiDataValue.CAMERA_ALARM_ADDRESS_122);
            } else {
                push = new HiPushSDK(HiDataValue.XGToken, getUid(), HiDataValue.company, pushResult,
                        HiDataValue.CAMERA_ALARM_ADDRESS);
            }
        } else if (this.getCommandFunction(CamHiDefines.HI_P2P_ALARM_ADDRESS_SET) && handSubXYZ()) {
            push = new HiPushSDK(HiDataValue.XGToken, getUid(), HiDataValue.company, pushResult,
                    HiDataValue.CAMERA_ALARM_ADDRESS_THERE);
        } else {// old device
            if (handSubWTU()) {
                push = new HiPushSDK(HiDataValue.XGToken, getUid(), HiDataValue.company, pushResult,
                        HiDataValue.CAMERA_ALARM_ADDRESS_122);
            } else {
                push = new HiPushSDK(HiDataValue.XGToken, getUid(), HiDataValue.company, pushResult,
                        HiDataValue.CAMERA_ALARM_ADDRESS);
            }
        }
//		Log.e("=====", "XGToken2=" + HiDataValue.XGToken + "---" + "company=" + HiDataValue.company + "ser"
//				+ this.getServerData());
        onBindPushResult = bindPushResult;
        if (isBind) {
            push.bind();
        } else {
            push.unbind(getPushState());
        }
    }

    /**
     * ����UIDǰ׺ΪXXX YYYY ZZZ
     *
     * @return ������򷵻� true
     */
    public boolean handSubXYZ() {
        String subUid = this.getUid().substring(0, 4);
        for (String str : HiDataValue.SUBUID) {
            if (str.equalsIgnoreCase(subUid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * ����UIDǰ׺ΪWWW TTT UUU
     *
     * @return ������򷵻� true
     */
    public boolean handSubWTU() {
        String subUid = this.getUid().substring(0, 4);
        for (String str : HiDataValue.SUBUID_WTU) {
            if (str.equalsIgnoreCase(subUid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * �ж�Camera �Ƿ��������豸
     */
    public boolean isFishEye() {
        if (mContext == null)
            return false;
        int isFishEye = 0;
        if (this.getConnectState() == HiCamera.CAMERA_CONNECTION_STATE_LOGIN) {
            isFishEye = this.getmold();
            SharePreUtils.putInt("cache", mContext, this.getUid() + "isFishEye", this.getmold());
        } else {
            isFishEye = SharePreUtils.getInt("cache", mContext, this.getUid() + "isFishEye");
        }
        if (isFishEye == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     */
    public void putFishModType(int fishModetype) {
        if (mContext == null)
            return;
        SharePreUtils.putInt("cache", mContext, this.getUid() + "fishmodtype", fishModetype);
    }

    /**
     */
    public int getFishModType() {
        if (mContext == null)
            return 0;
        int type = SharePreUtils.getInt("cache", mContext, this.getUid() + "fishmodtype");
        if (type == -1) {
            return 0;
        } else {
            return type;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MyCamera other = (MyCamera) obj;
        if (uid == null) {
            if (other.uid != null)
                return false;
        } else if (!uid.equals(other.uid))
            return false;
        return true;
    }
}
