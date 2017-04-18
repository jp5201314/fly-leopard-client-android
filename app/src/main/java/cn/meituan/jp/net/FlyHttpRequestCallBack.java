package cn.meituan.jp.net;

import com.alibaba.fastjson.JSONObject;

import org.greenrobot.eventbus.EventBus;

import cn.finalteam.okhttpfinal.JsonHttpRequestCallback;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.event.ErrorMessageEvent;
import cn.meituan.jp.event.UnLoginEvent;
import okhttp3.Headers;

/**
 * Created by 11608 on 2017/4/14.
 */

public class FlyHttpRequestCallBack extends JsonHttpRequestCallback {
    protected boolean autoToastErrorMessage = true;

    public FlyHttpRequestCallBack() {
        this.autoToastErrorMessage = true;
    }

    public FlyHttpRequestCallBack(boolean autoToastErrorMessage) {
        this.autoToastErrorMessage = autoToastErrorMessage;
    }

    @Override
    protected void onSuccess(Headers headers, JSONObject rstJson) {
        int status = rstJson.getInteger("status");

        JSONObject data = null;
        if (rstJson.containsKey("data")) {
            data = rstJson.getJSONObject("data");
        }

        JSONObject statusInfo = null;
        if (rstJson.containsKey("statusInfo")) {
            statusInfo = rstJson.getJSONObject("statusInfo");
        }

        switch (status) {
            case 0:
                onDataSuccess(data);
                break;
            case 10000:
                onDataError(status, statusInfo);
                if (autoToastErrorMessage) {
                    EventBus.getDefault().post(new ErrorMessageEvent(statusInfo.getString("message")));
                    unAvailableLogin(status);
                }
                break;
            case 40000://JWT_TOKEN不存在
            case 40001://JWT_TOKEN不可用
            case 40004://JWT_USER未找到
            case 40005://JWT_TOKEN过期失效
            case 40006://USER_KICKED
                unAvailableLogin(status);
            default:
                onDataError(status, statusInfo);
                break;
        }
    }

    protected void onDataSuccess(JSONObject data) {

    }

    protected void onDataError(int status, JSONObject statusInfo) {

    }

    private void unAvailableLogin(int code) {
        UserSharedPreference.getInstance().removeLoginMsg();
        EventBus.getDefault().post(new UnLoginEvent(code));
    }

}
