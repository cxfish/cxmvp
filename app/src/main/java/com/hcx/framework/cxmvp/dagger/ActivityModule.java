package com.hcx.framework.cxmvp.dagger;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Activity提供的变量
 * Created by ChenxiHu on 2016/12/15.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @PerActivity
    @Provides
    public Activity providesActivity() {
        return mActivity;
    }

}
