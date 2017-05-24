package cn.meituan.jp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

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
        editor.clear();
        editor.commit();
    }

    public void setPhoneAndPassword(String name, String password) {
        editor.putString("name", name);
        editor.putString("password", password);
        editor.commit();
    }

    public String getUserName(){
        return mSharedPreference.getString("name",null);
    }


    public String getPassword() {
        return mSharedPreference.getString("password", null);
    }


    public void setShoppingCartSum(int price) {
        editor.putInt("sum", price);
        editor.commit();
    }

    public int getShoppingCartAmount() {
        return mSharedPreference.getInt("sum", 0);
    }

    public void setBalance(int balance) {
        editor.putInt("balance", balance);
        editor.commit();
    }

    public int getBalance() {
        return mSharedPreference.getInt("balance", 0);
    }

    public void setId(int id){
        editor.putInt("id",id);
        editor.commit();
    }

    public int getId(){
        return mSharedPreference.getInt("id",0);
    }

    public void setPhone(String phone){
        editor.putString("phone",phone);
        editor.commit();
    }

    public String getPhone(){
        return mSharedPreference.getString("phone",null);
        }


    public void setAddress(String address){
        editor.putString("address",address);
        editor.commit();
    }

    public String getAddress(){
        return mSharedPreference.getString("address",null);
    }

    public void setNickName(String nickName){
        editor.putString("nick_name",nickName);
        editor.commit();
    }

    public String getNickName(){
        return mSharedPreference.getString("nick_name",null);
    }

    public void setCollectionClickNum(int num){
        editor.putInt("collectionClickNum",num);
        editor.commit();
    }

    public int getCollectionClickNum(){
        return mSharedPreference.getInt("collectionClickNum",0);
    }
}
