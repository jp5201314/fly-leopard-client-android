package cn.meituan.jp.datasource;

import com.shizhefei.mvc.IDataSource;

import java.util.List;

import cn.meituan.jp.entity.BusinessEntity;

/**
 * Created by 11608 on 2017/4/14.
 */

public class BusinessDataSource implements IDataSource<List<BusinessEntity>> {
    @Override
    public List<BusinessEntity> refresh() throws Exception {
        return null;
    }

    @Override
    public List<BusinessEntity> loadMore() throws Exception {
        return null;
    }

    @Override
    public boolean hasMore() {
        return false;
    }
}
