package com.hcx.framework.cxmvp;

import com.hcx.framework.cxmvplibrary.basefrg.BaseFrgPresenter;
import com.hcx.framework.cxmvplibrary.basefrg.BaseFrgView;

import javax.inject.Inject;

/**
 * Created by Chenxi Hu on 2018/7/17 0017.
 * vx : xidi9666
 */

public class DemoPresenter extends BaseFrgPresenter {

    //View
    DemoView v;


    @Inject
    public DemoPresenter() {

    }

    @Override
    protected void attachView(BaseFrgView frgView) {
        this.v = (DemoView) frgView;
    }
}
