package com.hcx.framework.cxmvp.dagger;

import com.hcx.framework.cxmvp.DemoFrg;

import dagger.Subcomponent;

/**
 * 注册。
 * Created by ChenxiHu on 2016/12/15.
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    //在这里提供注册方法
    void inJect(DemoFrg frg);


}
