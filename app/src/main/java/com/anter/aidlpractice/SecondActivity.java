package com.anter.aidlpractice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * @author Anter
 * @date 2018/3/31.
 */

public class SecondActivity extends AppCompatActivity {

    private final static String TAG = "SecondActivity";

    private IManager manager = null;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(TAG, "onServiceConnected: ");
            manager = IManager.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setClickListener();

        bindService(new Intent(this, ManagerService.class), conn, Context.BIND_AUTO_CREATE);
    }

    private void setClickListener() {
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (manager != null) {
                    try {
                        manager.managerCallback(new ICallback.Stub() {
                            @Override
                            public void onCallBack(User user) throws RemoteException {
                                Log.i(TAG, this.getClass().getSimpleName() + "onCallBack: " + user.toString());
                            }
                        });
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}
