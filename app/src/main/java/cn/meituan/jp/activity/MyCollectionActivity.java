package cn.meituan.jp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.meituan.jp.R;
import cn.meituan.jp.event.CancleCollectionShopEvent;
import cn.meituan.jp.event.CollectionShopEvent;

public class MyCollectionActivity extends BaseActivity {

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ib_back)
    ImageButton ibBack;
    @Bind(R.id.lv_collection_list)
    ListView lvCollectionList;
    @Bind(R.id.tv_add_info)
    TextView tvAddInfo;
    @Bind(R.id.ll_no_collection)
    LinearLayout llNoCollection;
    private List<String> shopNameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);

        tvTitle.setText("我的收藏");
        shopNameList = new LinkedList<>();
        setCollectionShopList(shopNameList);
    }

    private void setCollectionShopList(List list) {
        if (null != list && list.size() != 0) {
            llNoCollection.setVisibility(View.VISIBLE);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            lvCollectionList.setAdapter(arrayAdapter);
        }else {
            llNoCollection.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.ib_back)
    public void back() {
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void obtainCollectionShop(CollectionShopEvent event) {
        shopNameList.add(event.getName());
        setCollectionShopList(shopNameList);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void cancelCollectionShop(CancleCollectionShopEvent event) {
        if (null == shopNameList && shopNameList.size() != 0) {
            shopNameList.remove(event.getName());
            setCollectionShopList(shopNameList);
        }
    }
}
