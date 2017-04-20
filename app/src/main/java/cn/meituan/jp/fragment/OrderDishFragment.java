package cn.meituan.jp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.okhttpfinal.HttpRequest;
import cn.finalteam.okhttpfinal.RequestParams;
import cn.meituan.jp.Api;
import cn.meituan.jp.Constant;
import cn.meituan.jp.R;
import cn.meituan.jp.adapter.FoodsListAdapter;
import cn.meituan.jp.entity.FoodsEntity;
import cn.meituan.jp.manager.FullyLinearLayoutManager;
import cn.meituan.jp.net.FlyHttpRequestCallBack;

/**
 * Created by 11608 on 2017/4/19.
 */

public class OrderDishFragment extends BaseFragment {

    @Bind(R.id.rv_foods_list)
    RecyclerView rvFoodsList;

    private List<FoodsEntity> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_dish, container, false);
        ButterKnife.bind(this, view);
        getFoodLists();
        return view;
    }


    private void getFoodLists() {
        int id = getActivity().getIntent().getIntExtra("shop_id", 0);
        RequestParams params = new RequestParams(this);
        params.addFormDataPart("shop", id);
        HttpRequest.get(Constant.getHost() + Api.GAINGOODSLIST, params, new FlyHttpRequestCallBack() {
            @Override
            protected void onDataError(int status, JSONObject statusInfo) {
                super.onDataError(status, statusInfo);
            }

            @Override
            protected void onDataSuccess(JSONObject data) {
                super.onDataSuccess(data);
                list = JSON.parseArray(data.getString("foods"), FoodsEntity.class);
                if(list==null){
                   return;
                }
                rvFoodsList.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvFoodsList.setAdapter(new FoodsListAdapter(getActivity(), list));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
