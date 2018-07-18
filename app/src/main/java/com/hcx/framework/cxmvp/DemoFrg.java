package com.hcx.framework.cxmvp;

import android.view.View;
import android.widget.TextView;

import com.MyApplication;
import com.hcx.framework.cxmvplibrary.basefrg.BaseFrgPresenter;
import com.hcx.framework.cxmvplibrary.basefrg.BaseMvpFragment;
import com.hcx.framework.cxmvplibrary.utils.TypeSafer;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Chenxi Hu on 2018/7/17 0017.
 * vx : xidi9666
 */

public class DemoFrg extends BaseMvpFragment implements DemoView {

    @BindView(R.id.tv)
    TextView tv;

    @Inject
    DemoPresenter presenter;

    @Override
    public void inJect() {
        MyApplication.app.createActivityComponent(getActivity());
        MyApplication.app.getActivityComponent().inJect(this);
    }

    @Override
    public String getTitle() {
        return "设置";
    }

    @Override
    public BaseFrgPresenter initPresenter() {
        return presenter;
    }

    @Override
    public int initRootView() {
        return R.layout.frg_demo;
    }

    @Override
    public void initView(View view) {
        TypeSafer.text(tv,"你好");
        TypeSafer.text(tvRight,"右边");
    }

    @Override
    public void titleClick() {

        showMsg("点击标题");
    }

    @Override
    public void rightClick() {
        showMsg("点击右边");
    }
}
