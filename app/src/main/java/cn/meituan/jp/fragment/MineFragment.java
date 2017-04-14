package cn.meituan.jp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.activity.LoginRegisterActivity;

/**
 * Created by 11608 on 2017/4/13.
 */

public class MineFragment extends BaseFragment {
    @Bind(R.id.iv_title)
    ImageView title;
    @Bind(R.id.iv_msg)
    ImageView ivMsg;
    @Bind(R.id.tv_login_register)
    TextView tvLoginRegister;
    @Bind(R.id.tv_myWallet_sum)
    TextView tvMyWalletSum;
    @Bind(R.id.iv_myWallet)
    ImageView ivMyWallet;
    @Bind(R.id.rl_myWallet)
    RelativeLayout rlMyWallet;
    @Bind(R.id.tv_myredpacket_num)
    TextView tvMyredpacketNum;
    @Bind(R.id.iv_redpacket)
    ImageView ivRedpacket;
    @Bind(R.id.rl_myRedPacket)
    RelativeLayout rlMyRedPacket;
    @Bind(R.id.tv_mycoupon_num)
    TextView tvMycouponNum;
    @Bind(R.id.iv_mycoupon)
    ImageView ivMycoupon;
    @Bind(R.id.rl_myCoupon)
    RelativeLayout rlMyCoupon;
    @Bind(R.id.tv_myEvaluation)
    TextView tvMyEvaluation;
    @Bind(R.id.rl_myEvaluation)
    RelativeLayout rlMyEvaluation;
    @Bind(R.id.tv_myCollection)
    TextView tvMyCollection;
    @Bind(R.id.rl_myCollection)
    RelativeLayout rlMyCollection;
    @Bind(R.id.tv_myAddress)
    TextView tvMyAddress;
    @Bind(R.id.rl_myAddress)
    RelativeLayout rlMyAddress;
    @Bind(R.id.tv_myShare)
    TextView tvMyShare;
    @Bind(R.id.rl_myShare)
    RelativeLayout rlMyShare;
    @Bind(R.id.tv_business)
    TextView tvBusiness;
    @Bind(R.id.rl_business)
    RelativeLayout rlBusiness;
    @Bind(R.id.tv_help_feedback)
    TextView tvHelpFeedback;
    @Bind(R.id.rl_help_feedback)
    RelativeLayout rlHelpFeedback;
    @Bind(R.id.rl_online_service)
    RelativeLayout rlOnlineService;
    @Bind(R.id.rl_more)
    RelativeLayout rlMore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.iv_title)
    public void toLoginRegister(){
        startActivity(new Intent(getActivity(), LoginRegisterActivity.class));
    }
}
