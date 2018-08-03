package com.anter.aidlpractice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Anter
 * @date 2018/3/30.
 */

public class User implements Parcelable {

    private String name;
    private String gender;
    private int age;
    private boolean isChinese;

    public User(String name, String gender, int age, boolean isChinese) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.isChinese = isChinese;
    }

    public User() {
    }

    protected User(Parcel in) {
        name = in.readString();
        gender = in.readString();
        age = in.readInt();
        isChinese = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public String toString() {
        return "name:" + name
                + "gender:" + gender
                + "age:" + age
                + "isChinese:" + isChinese;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(gender);
        parcel.writeInt(age);
        parcel.writeByte((byte) (isChinese ? 1 : 0));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isChinese() {
        return isChinese;
    }

    public void setChinese(boolean chinese) {
        isChinese = chinese;
    }

}
