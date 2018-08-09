package com.hcx.framework.cxmvp;

import com.hcx.framework.cxmvplibrary.baseact.BaseActPresenter;
import com.hcx.framework.cxmvplibrary.baseact.BaseActView;

import javax.inject.Inject;

public class MainPresenter extends BaseActPresenter{

    //View
    MainView v;

    @Inject
    public MainPresenter() {

    }

    @Override
    public void attachView(BaseActView actView) {
        this.v = (MainView) actView;
    }
}
