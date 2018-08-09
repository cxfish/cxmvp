package com.hcx.framework.cxmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.MyApplication;
import com.hcx.framework.cxmvplibrary.baseact.BaseActPresenter;
import com.hcx.framework.cxmvplibrary.baseact.BaseMvpActivity;
import com.hcx.framework.cxmvplibrary.utils.ActivitySwitcher;

import javax.inject.Inject;

public class MainAct extends BaseMvpActivity implements MainView{

    @Inject
    MainPresenter presenter;

    @Override
    public void inJect() {
        MyApplication.app.createActivityComponent(this);
        MyApplication.app.getActivityComponent().inJect(this);
    }

    @Override
    public BaseActPresenter initPresenter() {
        return presenter;
    }

    @Override
    public int initRootView() {
        return R.layout.act_main;
    }

    @Override
    public void initView() {
        ActivitySwitcher.startFragment(MainAct.this,DemoFrg.class);
    }
}
