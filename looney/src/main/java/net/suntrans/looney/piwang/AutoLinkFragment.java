package net.suntrans.looney.piwang;

import android.content.BroadcastReceiver;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Context;

import net.suntrans.looney.R;

import io.fogcloud.sdk.easylink.api.EasyLink;
import io.fogcloud.sdk.easylink.helper.EasyLinkCallBack;
import io.fogcloud.sdk.easylink.helper.EasyLinkParams;

/**
 * Created by Looney on 2018/9/5.
 * Des:
 */
public class AutoLinkFragment extends Fragment {

    public static AutoLinkFragment newInstance() {
        Bundle args = new Bundle();
        AutoLinkFragment fragment = new AutoLinkFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private EasyLink easyLink;
    private AlertDialog dialog;

    private View start;
    private EditText ssid;
    private EditText passwordTx;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_autolink, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        easyLink = new EasyLink(getContext());
        start = view.findViewById(R.id.start);
        ssid = (EditText) view.findViewById(R.id.ssid);
        passwordTx = (EditText) view.findViewById(R.id.password);
        listenwifichange();

        dialog = new AlertDialog.Builder(getContext())
                .setCancelable(false)
                .create();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ssid.getText().toString()) || TextUtils.isEmpty(passwordTx.getText().toString())) {
                    Toast.makeText(getContext().getApplicationContext(), getString(R.string.tips_contain_ssid_password), Toast.LENGTH_SHORT).show();
                    return;
                }
                start.setEnabled(false);
                startEasyLink(ssid.getText().toString(), passwordTx.getText().toString());
                View view = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_loading, null, false);
                view.findViewById(R.id.close)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                stopEasyLink();
                                start.setEnabled(true);
                                dialog.dismiss();
                            }
                        });
                dialog.setView(view);
                dialog.show();
            }
        });
    }

    private void startEasyLink(String ssid, String password) {

        EasyLinkParams params = new EasyLinkParams();


        params.ssid = ssid;
        params.password = password;
        params.runSecond = 60000;
        params.sleeptime = 50;


        getActivity().getApplicationContext().getSharedPreferences("peiwang", Context.MODE_PRIVATE)
                .edit()
                .putString(ssid, password)
                .apply();

//        easylinkP2P.startEasyLink(params, new EasyLinkCallBack() {
//            @Override
//            public void onSuccess(int code, String message) {
//                UiUtils.showToast(message);
//
//            }
//
//            @Override
//            public void onFailure(int code, String message) {
//
//            }
//        });
        easyLink.startEasyLink(params, new EasyLinkCallBack() {
            @Override
            public void onSuccess(int code, String message) {

            }

            @Override
            public void onFailure(int code, String message) {

            }
        });
    }

    private void stopEasyLink() {
//
        easyLink.stopEasyLink(new EasyLinkCallBack() {
            @Override
            public void onSuccess(int code, String message) {

            }

            @Override
            public void onFailure(int code, String message) {

            }
        });

//        easylinkP2P.stopEasyLink(new EasyLinkCallBack() {
//            @Override
//            public void onSuccess(int code, String message) {
//
//            }
//
//            @Override
//            public void onFailure(int code, String message) {
//
//            }
//        });
    }

    private void listenwifichange() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        getContext().registerReceiver(broadcastReceiver, intentFilter);
    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (info.getDetailedState() == NetworkInfo.DetailedState.CONNECTED) {
                    ssid.setText(easyLink.getSSID());
                    String password = getActivity().getApplicationContext().getSharedPreferences("peiwang", Context.MODE_PRIVATE).getString(easyLink.getSSID(), "");
                    passwordTx.setText(password);
                }
            }
        }
    };
}
