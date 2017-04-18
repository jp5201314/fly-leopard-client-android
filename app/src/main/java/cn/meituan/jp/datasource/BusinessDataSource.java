package cn.meituan.jp.datasource;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.IDataSource;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.meituan.jp.Api;
import cn.meituan.jp.Constant;
import cn.meituan.jp.entity.BusinessEntity;
import cn.meituan.jp.event.ErrorMessageEvent;
import cn.meituan.jp.net.FlyHttpRequestCallBack;

/**
 * Created by 11608 on 2017/4/14.
 */

public class BusinessDataSource implements IAsyncDataSource<List<BusinessEntity>> {
    private HttpCycleContext httpCycleContext;

    public BusinessDataSource(HttpCycleContext httpCycleContext) {
        this.httpCycleContext = httpCycleContext;
    }

    @Override
    public RequestHandle refresh(ResponseSender<List<BusinessEntity>> sender) throws Exception {
        return loadBusinessList(sender);
    }

    @Override
    public RequestHandle loadMore(ResponseSender<List<BusinessEntity>> sender) throws Exception {
        return loadBusinessList(sender);
    }

    @Override
    public boolean hasMore() {
        return true;
    }

    private RequestHandle loadBusinessList(final ResponseSender<List<BusinessEntity>> sender) {

        HttpRequest.get(Constant.getHost() + Api.GAINSHOPSLIST, new FlyHttpRequestCallBack() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                super.onDataSuccess(data);
                List<BusinessEntity> businessEntities = JSON.parseArray(data.getString("shops"), BusinessEntity.class);
                sender.sendData(businessEntities);
            }

            @Override
            protected void onDataError(int status, JSONObject statusInfo) {
                super.onDataError(status, statusInfo);
                EventBus.getDefault().post(new ErrorMessageEvent("获取商店失败"));
            }
        });
        return new OkHttpRequestHandler();
    }
}
