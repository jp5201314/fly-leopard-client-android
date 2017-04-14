package cn.meituan.jp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 11608 on 2017/4/13.
 */

public class UserSharedPreference {

    private static UserSharedPreference instance;
    private SharedPreferences mSharedPreference;
    private String SHAREDPREFERENCES_NAME = "users";
    private SharedPreferences.Editor editor;


    private UserSharedPreference(Context context){
        mSharedPreference = context.getSharedPreferences(SHAREDPREFERENCES_NAME,Context.MODE_PRIVATE);
        editor = mSharedPreference.edit();

    }

    public synchronized static UserSharedPreference getInstance(){
        if(null==instance){
            instance = new UserSharedPreference(FlyLeopardApplication.getContext());
        }
        return instance;
    }

    public void setIsLogined(String msg){
        editor.putString("LoginMsg",msg);
        editor.commit();
    }

    public String getIsLogined(){
        return mSharedPreference.getString("LoginMsg","null");
    }

    public void setIsFristLogin(boolean flag){
        editor.putBoolean("isFirstLogin",flag);
        editor.commit();
    }
    public boolean getIsFristLogin(){
        return mSharedPreference.getBoolean("isFirstLogin",true);
    }

    public void removeLoginMsg(){
        if(mSharedPreference.contains("LoginMsg")){
            editor.remove("LoginMsg");
            editor.commit();
        }
    }

    public void setPhoneAndPassword(String mobile,String password){
        editor.putString("mobile",mobile);
        editor.putString("password",password);
        editor.commit();
    }
}
