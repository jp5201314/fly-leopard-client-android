package cn.meituan.jp;

import android.content.Context;
import android.content.SharedPreferences;

import cn.meituan.jp.entity.UserEntity;

/**
 * Created by 11608 on 2017/4/13.
 */

public class UserSharedPreference {

    private static UserSharedPreference instance;
    private SharedPreferences mSharedPreference;
    private String SHAREDPREFERENCES_NAME = "users";
    private SharedPreferences.Editor editor;


    private UserSharedPreference(Context context) {
        mSharedPreference = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE);
        editor = mSharedPreference.edit();

    }

    public synchronized static UserSharedPreference getInstance() {
        if (null == instance) {
            instance = new UserSharedPreference(FlyLeopardApplication.getContext());
        }
        return instance;
    }

    public void setIsLogined(int msg) {
        editor.putInt("status", msg);
        editor.commit();
    }

    public int getIsLogined() {
        return mSharedPreference.getInt("status", -1);
    }

    public void setIsFristLogin(boolean flag) {
        editor.putBoolean("isFirstLogin", flag);
        editor.commit();
    }

    public boolean getIsFristLogin() {
        return mSharedPreference.getBoolean("isFirstLogin", true);
    }

    public void removeLoginMsg() {
        if (mSharedPreference.contains("status")) {
            editor.remove("status");
            editor.remove("name");
            editor.remove("password");
            editor.remove("user_json_string");
            editor.commit();
        }
    }

    public void setPhoneAndPassword(String mobile, String password) {
        editor.putString("name", mobile);
        editor.putString("password", password);
        editor.commit();
    }

    public String getPassword(){
        return mSharedPreference.getString("password",null);
    }

    public void setUserJsonString(String userJsonString) {
        editor.putString("user_json_string", userJsonString);
        editor.commit();
    }

    public String getUserJsonString() {
        return mSharedPreference.getString("user_json_string", null);
    }
}
