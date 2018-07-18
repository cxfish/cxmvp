package com.hcx.framework.cxmvplibrary;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.hcx.framework.cxmvplibrary.utils.ToastUtils;
import com.hcx.framework.cxmvplibrary.utils.Utils;

/**
 * Created by Chenxi Hu on 2018/7/17 0017.
 * vx : xidi9666
 */

public class CxApplication extends Application {

    public static CxApplication self;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (self == null) {
            self = this;
        }
        Utils.init(this);
        ToastUtils.init(false);
    }
}
