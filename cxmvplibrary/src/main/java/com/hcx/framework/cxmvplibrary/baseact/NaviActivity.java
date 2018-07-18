package com.hcx.framework.cxmvplibrary.baseact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hcx.framework.cxmvplibrary.R;
import com.hcx.framework.cxmvplibrary.basefrg.BaseMvpFragment;
import com.hcx.framework.cxmvplibrary.utils.ActivitySwitcher;
import com.hcx.framework.cxmvplibrary.utils.BackHandlerHelper;
import com.hcx.framework.cxmvplibrary.utils.ReflectUtil;

/**
 * Fragment依附的Activity
 */
public class NaviActivity extends AppCompatActivity {

    public static final String EXT_FRAGMENT = "fragment_name";

    protected BaseMvpFragment fragment;

    protected boolean isDestroyed;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        fragment = createInstance(getFragmentName(getIntent()));
        if (null == fragment) {
            finish();
            return;
        }
        fragment.setArguments(getIntent().getExtras());
        fragment.setActivity(this);
        setContentView(R.layout.act_common);
        addFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean isDisable() {
        return isDestroyed || isFinishing();
    }

    public String getFragmentName(final Intent intent) {
        return (null != intent) ? intent.getStringExtra(EXT_FRAGMENT) : null;
    }

    public BaseMvpFragment createInstance(final String fragName) {
        final Object obj = ReflectUtil.createInstance(fragName);
        if (obj instanceof BaseMvpFragment) {
            return (BaseMvpFragment) obj;
        }
        return null;
    }

    public void addFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.act_content, fragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        fragment.onActivityResult(arg0, arg1, arg2);
    }

    public void finishSelf() {
        ActivitySwitcher.finish(this);
    }

    public void finishDown() {
        ActivitySwitcher.finishDown(this);
    }

    public String simpleName() {
        return fragment.getClass().getSimpleName();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
    }

    @Override
    public void onBackPressed() {
        if (!BackHandlerHelper.handleBackPress(this)) {
            super.onBackPressed();
        }
    }
}
