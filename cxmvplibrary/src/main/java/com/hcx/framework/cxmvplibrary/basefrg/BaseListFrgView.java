package com.hcx.framework.cxmvplibrary.basefrg;

import java.util.List;

/**
 * Created by Chenxi Hu on 2018/5/13 0013.
 * vx : xidi9666
 */

public interface BaseListFrgView extends BaseFrgView{

    //显示数据
    void showDatas(List datas);

    //显示没有更多数据
    void showNoMoreDatas();

    //显示加载失败
    void showFailed();

    //清除数据列表
    void clearDatas();
}
