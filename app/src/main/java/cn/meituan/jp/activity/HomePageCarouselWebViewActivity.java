package cn.meituan.jp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;

public class HomePageCarouselWebViewActivity extends BaseActivity {

    @Bind(R.id.wv)
    WebView wv;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_share)
    ImageView ivShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_home_page_carousel_web_view);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);
        //支持javascript
        wv.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        wv.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        wv.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        wv.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv.getSettings().setLoadWithOverviewMode(true);

        wv.loadUrl(getIntent().getStringExtra("url"));
    }

    @OnClick(R.id.iv_back)
    public void back() {
        finish();
    }

    @OnClick(R.id.iv_share)
    public void share() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getIntent().getStringExtra("url"));
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "分享给好基友吧"));
    }
}
