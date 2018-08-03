package com.anter.aidlpractice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * @author Anter
 * @date 2018/3/30.
 */

public class ManagerService extends Service {

    private RemoteCallbackList<ICallback> callbackList = new RemoteCallbackList<>();

    private Binder binder = new IManager.Stub() {
        @Override
        public String basicTypesToString(int anInt,
                                         long aLong,
                                         boolean aBoolean,
                                         float aFloat,
                                         double aDouble,
                                         String aString) throws RemoteException {
            return "anInt:" + anInt
                    + "aLong:" + aLong
                    + "aBoolean:" + aBoolean
                    + "aFloat:" + aFloat
                    + "aDouble:" + aDouble
                    + "aString:" + aString;
        }

        @Override
        public User updateUser(User user) throws RemoteException {
            user.setName("Back " + user.getName());
            user.setGender("Back " + user.getGender());
            user.setAge(100 + user.getAge());
            user.setChinese(!user.isChinese());
            return user;
        }

        @Override
        public void managerCallback(ICallback callback) throws RemoteException {
            callbackList.register(callback);
            final int n = callbackList.beginBroadcast();
            for (int i = 0; i < n; i ++) {
                callbackList.getBroadcastItem(i).onCallBack(new User("小武", "男", 12, true));
            }
            callbackList.finishBroadcast();
            callbackList.unregister(callback);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}
