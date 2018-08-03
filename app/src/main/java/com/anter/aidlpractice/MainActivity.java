package com.anter.aidlpractice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * @author Anter
 */
public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService(new Intent(this, ManagerService.class), conn, Context.BIND_AUTO_CREATE);

        setClickListener();
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
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
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

}
