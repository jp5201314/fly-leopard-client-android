package cn.meituan.jp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.meituan.jp.R;

public class HomePageSearchActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.main_serch_relayout)
    LinearLayout mainSerchRelayout;
    @Bind(R.id.rv_search)
    RecyclerView rvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_search);
        ButterKnife.bind(this);
        this.setStatusBarColor(R.color.color_black_0e1214);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              /*  shopList.removeAll(shopList);
                for (Object shop: HomeFragment.shopList) {
                    if(((Shop)shop).getName() .contains(editText.getText())){
                        shopList.add((Shop) shop);
                    }
                }
                listView.setAdapter(new MySerchListAdapter()); //编辑框输入变化重新设置适配器*/

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

/*        shopList = new ArrayList<>();

        MySerchListAdapter adapter = new MySerchListAdapter();
        listView.setAdapter(adapter);
        *//**
         *  遍历从服务器获取的shoplist并比较商家名字，如果和输入相同则放入本类shoplist
         *//*

        for (Object shop: HomeFragment.shopList) {
            if(((Shop)shop).getName() .contains(editText.getText())){
                this.shopList.add((Shop) shop);
            }
        }*/
    }

    @OnClick(R.id.iv_back)
    public void back(){
        finish();
    }

    @OnClick(R.id.iv_search)
    public void search(){

    }

}
