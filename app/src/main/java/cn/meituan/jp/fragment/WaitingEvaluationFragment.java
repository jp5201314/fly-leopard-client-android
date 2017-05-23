package cn.meituan.jp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shizhefei.mvc.MVCUltraHelper;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.adapter.OrdersListAdapter;
import cn.meituan.jp.adapter.WaitingEvaluationListAdapter;
import cn.meituan.jp.datasource.OrderDataSource;
import cn.meituan.jp.manager.FullyLinearLayoutManager;
import cn.meituan.jp.view.DefinedLoadViewFactory;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by 11608 on 2017/4/17.
 */

public class WaitingEvaluationFragment extends BaseFragment {
    MVCUltraHelper mvc;
    @Bind(R.id.ll_no_comment_order)
    LinearLayout llNoCommentOrder;
    @Bind(R.id.rv)
    RecyclerView rv;
    @Bind(R.id.pfl_ptr_frame)
    PtrClassicFrameLayout pflPtrFrame;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wait_evaluation, container, false);
        ButterKnife.bind(this, view);
        if (UserSharedPreference.getInstance().getIsLogined() == 0) {
            llNoCommentOrder.setVisibility(View.GONE);
            pflPtrFrame.setVisibility(View.VISIBLE);
            rv.setVisibility(View.VISIBLE);
            MVCUltraHelper.setLoadViewFractory(new DefinedLoadViewFactory());
            this.setMaterialHeader(pflPtrFrame);
            mvc = new MVCUltraHelper<>(pflPtrFrame);
            setRecyclerView();
        } else {
            llNoCommentOrder.setVisibility(View.VISIBLE);
            pflPtrFrame.setVisibility(View.GONE);
            rv.setVisibility(View.GONE);
        }
        return view;
    }

    private void setRecyclerView() {
        rv.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
        rv.setNestedScrollingEnabled(false);
        mvc.setDataSource(new OrderDataSource(this, UserSharedPreference.getInstance().getId()));
        mvc.setAdapter(new WaitingEvaluationListAdapter(getActivity()));
        mvc.refresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
