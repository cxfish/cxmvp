package com.hcx.framework.cxmvplibrary.basefrg;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcx.framework.cxmvplibrary.R;
import com.hcx.framework.cxmvplibrary.R2;
import com.hcx.framework.cxmvplibrary.baseact.NaviActivity;
import com.hcx.framework.cxmvplibrary.interfaces.FragmentBackHandler;
import com.hcx.framework.cxmvplibrary.utils.ActivitySwitcher;
import com.hcx.framework.cxmvplibrary.utils.ToastUtils;
import com.hcx.framework.cxmvplibrary.utils.TypeSafer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * MvpFragment基类
 *
 * @param <V>
 * @param <T>
 */
public abstract class BaseMvpFragment<V extends BaseFrgView, T extends BaseFrgPresenter<V>> extends RxFragment implements BaseFrgView, FragmentBackHandler, View.OnClickListener {

    public T presenter;
    protected NaviActivity act;
    protected View rootView;
    //头部返回按钮
    private TextView tvBack;
    //头部时间
    private TextView tvTime;
    //头部布局
    RelativeLayout rlTitle;
    //wifi图标
    private ImageView ivWifi;
    //蓝牙图标
    private ImageView ivBluetooth;
    //U盘图标
    private ImageView ivUsb;
    //内存提示
    private TextView tvTipsMemory;
    //网络提示
    private TextView tvTipsNet;

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

        initTitleLayout();
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
        //设置标题
        if (TextUtils.isEmpty(getTitle())) {
            rlTitle.setVisibility(View.GONE);
        } else {
            rlTitle.setVisibility(View.VISIBLE);
        }
        bindingEvent();
        return rootView;
    }

    private void initTitleLayout() {
        rlTitle = getActivity().findViewById(R.id.rl_title);
        tvBack = getActivity().findViewById(R.id.tv_back);
        tvTime = getActivity().findViewById(R.id.tv_time);
        ivWifi = getActivity().findViewById(R.id.iv_wifi);
        ivBluetooth = getActivity().findViewById(R.id.iv_bluetooth);
        ivUsb = getActivity().findViewById(R.id.iv_usb);
        tvTipsMemory = getActivity().findViewById(R.id.tv_tipsMemory);
        tvTipsNet = getActivity().findViewById(R.id.tv_tipsNet);

        tvBack.setOnClickListener(this);
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


    public void setActivity(NaviActivity act) {
        this.act = act;
    }

    /**
     * 若使用Dagger2注册该界面，每个界面都要实现，否则的话可以不实现
     * <p>
     * App.get(getActivity()).createActivityComponent(getActivity());
     * App.get(getActivity()).getActivityComponent().inject(this);
     * mPresenter.attchView(this);
     *
     * @return
     */
    public abstract void inJect();


    //初始化协调者
    public abstract T initPresenter();

    //初始化布局文件id
    public abstract int initRootView();

    //设置标题
    public String getTitle() {
        return null;
    }

    //初始化View
    public abstract void initView(View view);

    //绑定事件
    protected void bindingEvent() {

    }

    //释放资源
    protected void release() {

    }

    public void back() {
        ActivitySwitcher.addAnimR2L(getActivity());
        ActivitySwitcher.finish(getActivity());
    }


    @Override
    public boolean onBackPressed() {
        back();
        return false;
    }

    @Override
    public void showMsg(final String msg) {
        Observable.just(0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        ToastUtils.showShortToast(msg);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void hideInput() {
        // 隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 设置设备时间
     *
     * @param time
     */
    public void setTime(String time) {
        TypeSafer.text(tvTime, time);
    }

    @SuppressLint("InvalidR2Usage")
    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_back) {
            back();

        }
    }

    @Override
    public void setBackText(String backText) {
        TypeSafer.text(tvBack,backText);
    }

    @Override
    public void hideMemoryTips() {
        tvTipsMemory.setVisibility(View.GONE);
    }

    @Override
    public void showMemoryTips() {
        tvTipsMemory.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNetTips() {
        tvTipsNet.setVisibility(View.GONE);
    }

    @Override
    public void showNetTips() {
        tvTipsNet.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBack() {
        tvBack.setVisibility(View.GONE);
    }

    @Override
    public void showBack() {
        tvBack.setVisibility(View.VISIBLE);
    }
}
