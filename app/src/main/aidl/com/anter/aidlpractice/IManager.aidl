// IManager.aidl
package com.anter.aidlpractice;

import com.anter.aidlpractice.User;
import com.anter.aidlpractice.ICallback;

// Declare any non-default types here with import statements

interface IManager {

        String basicTypesToString(int anInt, long aLong, boolean aBoolean, float aFloat,
                double aDouble, String aString);

        User updateUser(in User user);

        void managerCallback(ICallback callback);

}
