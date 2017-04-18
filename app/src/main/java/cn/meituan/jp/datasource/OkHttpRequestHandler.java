package cn.meituan.jp.datasource;

import com.shizhefei.mvc.RequestHandle;

/**
 * Created by lenovo on 2017/2/7.
 */
public class OkHttpRequestHandler implements RequestHandle {
    @Override
    public void cancle() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
