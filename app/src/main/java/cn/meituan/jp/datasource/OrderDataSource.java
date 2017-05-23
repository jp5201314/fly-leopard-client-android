package cn.meituan.jp.datasource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shizhefei.mvc.IAsyncDataSource;
import com.shizhefei.mvc.RequestHandle;
import com.shizhefei.mvc.ResponseSender;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.finalteam.okhttpfinal.HttpCycleContext;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.meituan.jp.Api;
import cn.meituan.jp.Constant;
import cn.meituan.jp.entity.OrderEntity;
import cn.meituan.jp.event.ErrorMessageEvent;
import cn.meituan.jp.net.FlyHttpRequestCallBack;

/**
 * Created by 11608 on 2017/4/24.
 */

public class OrderDataSource implements IAsyncDataSource<List<OrderEntity>> {

    private int id;
    private HttpCycleContext httpCycleContext;
    public OrderDataSource(HttpCycleContext httpCycleContext,int id){
        this.id = id;
        this.httpCycleContext = httpCycleContext;
    }

    @Override
    public RequestHandle refresh(ResponseSender<List<OrderEntity>> sender) throws Exception {
        return loadOrderList(sender);
    }

    @Override
    public RequestHandle loadMore(ResponseSender<List<OrderEntity>> sender) throws Exception {
        return loadOrderList(sender);
    }

    @Override
    public boolean hasMore() {
        return false;
    }

    private RequestHandle loadOrderList(final ResponseSender<List<OrderEntity>> sender){
        HttpRequest.get(Constant.getHost() + String.format(Api.GAINMYGOODSORDERSLIST,id), new FlyHttpRequestCallBack() {
            @Override
            protected void onDataSuccess(JSONObject data) {
                super.onDataSuccess(data);
                List<OrderEntity> orderEntities = JSON.parseArray(data.getString("orders"), OrderEntity.class);
                sender.sendData(orderEntities);
            }

            @Override
            protected void onDataError(int status, JSONObject statusInfo) {
                super.onDataError(status, statusInfo);
                EventBus.getDefault().post(new ErrorMessageEvent("获取订单失败"));
            }
        });
        return new OkHttpRequestHandler();
    }
}
