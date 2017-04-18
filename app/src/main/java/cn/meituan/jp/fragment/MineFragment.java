package cn.meituan.jp.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.UserSharedPreference;
import cn.meituan.jp.activity.BusinessInToActivity;
import cn.meituan.jp.activity.HelpAndFeedBackActivity;
import cn.meituan.jp.activity.MessageActivity;
import cn.meituan.jp.activity.MoreActivity;
import cn.meituan.jp.activity.MyAccountActivity;
import cn.meituan.jp.activity.MyAddressActivity;
import cn.meituan.jp.activity.MyCollectionActivity;
import cn.meituan.jp.activity.MyCouponActivity;
import cn.meituan.jp.activity.MyEvaluationActivity;
import cn.meituan.jp.activity.MyOnlineServiceActivity;
import cn.meituan.jp.activity.MyRedPacketActivity;
import cn.meituan.jp.activity.MyShareActivity;
import cn.meituan.jp.activity.MyWalletActivity;
import cn.meituan.jp.event.ExitLoginEvent;

/**
 * Created by 11608 on 2017/4/13.
 */

public class MineFragment extends BaseFragment {


    @Bind(R.id.iv_head_image)
    ImageView ivHeadImage;
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
    @Bind(R.id.tv_online_service)
    TextView tvOnlineService;
    @Bind(R.id.rl_online_service)
    RelativeLayout rlOnlineService;
    @Bind(R.id.tv_more)
    TextView tvMore;
    @Bind(R.id.rl_more)
    RelativeLayout rlMore;
    @Bind(R.id.tv_service_phone)
    TextView tvServicePhone;
    private int isLogined;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        isLogined = UserSharedPreference.getInstance().getIsLogined();
        if (isLogined == 0) {
            tvLoginRegister.setClickable(false);
            tvLoginRegister.setText(JSONObject.parseObject(UserSharedPreference.getInstance().getUserJsonString()).getString("nick_name"));
            Picasso.with(getActivity()).load("http://i2.sanwen8.cn/doc/1609/805-160922092H0-51.jpg").resize(100, 80).centerCrop().into(ivHeadImage);
            init();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.iv_head_image)
    public void toShowAccountInfo() {
        if (isLogined == -1) {
            jumpLogin();
            toast("请先登录~");
            return;
        } else {
            Intent intent = new Intent(getActivity(), MyAccountActivity.class);
            intent.putExtra("id",JSONObject.parseObject(UserSharedPreference.getInstance().getUserJsonString()).getIntValue("id"));
            Log.i("FSLog",JSONObject.parseObject(UserSharedPreference.getInstance().getUserJsonString()).getIntValue("id")+"");
            intent.putExtra("password",UserSharedPreference.getInstance().getPassword());
            startActivity(intent);
        }
    }

    @OnClick(R.id.iv_msg)
    public void toGetMsg() {
        if (isLogined == -1) {
            jumpLogin();
            toast("请先登录~");
            return;
        } else {

            startActivity(new Intent(getActivity(), MessageActivity.class));
        }
    }

    @OnClick(R.id.tv_login_register)
    public void toLoginRegister() {
        jumpLogin();
    }

    //初始化操作
    private void init() {
        tvMyEvaluation.setTextColor(getActivity().getResources().getColor(R.color.color_black_1a1919));
        tvMyCollection.setTextColor(getActivity().getResources().getColor(R.color.color_black_1a1919));
        tvMyAddress.setTextColor(getActivity().getResources().getColor(R.color.color_black_1a1919));
        tvMyShare.setTextColor(getActivity().getResources().getColor(R.color.color_black_1a1919));
    }

    @OnClick(R.id.rl_myWallet)
    public void toRlMyWallet() {
        startActivity(new Intent(getActivity(), MyWalletActivity.class));
    }

    @OnClick(R.id.rl_myRedPacket)
    public void toMyRedPacket() {
        startActivity(new Intent(getActivity(), MyRedPacketActivity.class));
    }

    @OnClick(R.id.rl_myCoupon)
    public void toMyCoupon() {
        startActivity(new Intent(getActivity(), MyCouponActivity.class));
    }

    @OnClick(R.id.rl_myEvaluation)
    public void toMyEvaluation() {
        startActivity(new Intent(getActivity(), MyEvaluationActivity.class));
    }

    @OnClick(R.id.rl_myCollection)
    public void toMyCollection() {
        startActivity(new Intent(getActivity(), MyCollectionActivity.class));
    }

    @OnClick(R.id.rl_myAddress)
    public void toMyAddress() {
        startActivity(new Intent(getActivity(), MyAddressActivity.class));
    }

    @OnClick(R.id.rl_myShare)
    public void toMyShare() {
        startActivity(new Intent(getActivity(), MyShareActivity.class));
    }

    @OnClick(R.id.rl_business)
    public void toBusiness() {
        startActivity(new Intent(getActivity(), BusinessInToActivity.class));
    }

    @OnClick(R.id.rl_help_feedback)
    public void toHelpAndFeedBack() {
        startActivity(new Intent(getActivity(), HelpAndFeedBackActivity.class));
    }

    @OnClick(R.id.rl_online_service)
    public void toOnlineService() {
        startActivity(new Intent(getActivity(), MyOnlineServiceActivity.class));
    }

    @OnClick(R.id.rl_more)
    public void toMore() {
        startActivity(new Intent(getActivity(), MoreActivity.class));
    }

    @OnClick(R.id.tv_service_phone)
    public void callServicePhone() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tvServicePhone.getText().toString().trim()));
        startActivity(intent);
    }

}
