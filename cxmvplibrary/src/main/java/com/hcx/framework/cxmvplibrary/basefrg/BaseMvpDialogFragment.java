package com.hcx.framework.cxmvplibrary.basefrg;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.hcx.framework.cxmvplibrary.R;
import com.hcx.framework.cxmvplibrary.R2;
import com.hcx.framework.cxmvplibrary.baseact.NaviActivity;
import com.hcx.framework.cxmvplibrary.interfaces.FragmentBackHandler;
import com.hcx.framework.cxmvplibrary.utils.ActivitySwitcher;
import com.hcx.framework.cxmvplibrary.utils.ScreenUtils;
import com.hcx.framework.cxmvplibrary.utils.ToastUtils;
import com.trello.rxlifecycle2.components.RxDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * MvpFragment基类
 *
 * @param <V>
 * @param <T>
 */
public abstract class BaseMvpDialogFragment<V extends BaseFrgView, T extends BaseFrgPresenter<V>> extends RxDialogFragment implements BaseFrgView,FragmentBackHandler, SwipeRefreshLayout.OnRefreshListener {

    public T presenter;
    protected NaviActivity act;
    protected View rootView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if ((null == act) && act instanceof NaviActivity) {
            act = (NaviActivity) activity;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inJect();
        presenter = initPresenter();
        presenter.attachView(this);

        if (rootView == null) {
            rootView = inflater.inflate(initRootView(), container, false);
            ButterKnife.bind(this, rootView);
            initView(rootView);
        } else {
            //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null) {
                parent.removeView(rootView);
            }
        }
        bindingEvent();
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.attach((V) this);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        release();
    }

    @Override
    public void onDetach() {
        act = null;
        super.onDetach();
        if (presenter != null) {
            presenter.dettach();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable( new ColorDrawable(Color.WHITE));

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );

        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.RIGHT;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width =  ScreenUtils.dip2px(335);
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        win.setAttributes(params);
    }

    public void setActivity(NaviActivity act) {
        this.act = act;
    }
    /**
     *
     * 注册该界面，每个界面都要实现
     * 如代码所示
     * DWApp.get(getActivity()).createActivityComponent(getActivity());
     * DWApp.get(getActivity()).getActivityComponent().inject(this);
     * mPresenter.attchView(this);
     * @return
     */
    public abstract void inJect();


    //初始化协调者
    public abstract T initPresenter();

    //初始化布局文件id
    public abstract int initRootView();

    //初始化View
    public abstract void initView(View view);

    //绑定事件
    protected void bindingEvent(){

    }

    //释放资源
    protected void release(){

    }

    @Optional
    @OnClick(R2.id.tv_back)
    public void back(){
        ActivitySwitcher.addAnimR2L(getActivity());
        ActivitySwitcher.finish(getActivity());
    }

    @Override
    public boolean onBackPressed() {
        back();
        return false;
    }

    @Override
    public void showMsg(String msg){
        ToastUtils.showShortToast(msg);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onRefresh() {

    }

    @Override
    public void hideInput() {
        // 隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
    }
}
