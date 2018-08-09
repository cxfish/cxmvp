package com.hcx.framework.cxmvplibrary.baseact;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hcx.framework.cxmvplibrary.CxApplication;
import com.hcx.framework.cxmvplibrary.R;
import com.hcx.framework.cxmvplibrary.R2;
import com.hcx.framework.cxmvplibrary.utils.ActivitySwitcher;
import com.hcx.framework.cxmvplibrary.utils.ToastUtils;
import com.hcx.framework.cxmvplibrary.utils.TypeSafer;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * MvpActivity基类
 *
 * @param <V>
 * @param <T>
 */
public abstract class BaseMvpActivity<V extends BaseActView, T extends BaseActPresenter<V>> extends RxAppCompatActivity implements BaseActView {

    public T presenter;

    @BindView(R2.id.act_content)
    FrameLayout llWhole;

    //头部返回按钮
    @BindView(R2.id.tv_back)
    TextView tvBack;
    //头部时间
    @BindView(R2.id.tv_time)
    TextView tvTime;
    //头部布局
    @BindView(R2.id.rl_statusBar)
    RelativeLayout rlStatusBar;
    //wifi图标
    @BindView(R2.id.iv_wifi)
    ImageView ivWifi;
    //蓝牙图标
    @BindView(R2.id.iv_bluetooth)
    ImageView ivBluetooth;
    //U盘图标
    @BindView(R2.id.iv_usb)
    ImageView ivUsb;
    //内存提示
    @BindView(R2.id.tv_tipsMemory)
    TextView tvTipsMemory;
    //网络提示
    @BindView(R2.id.tv_tipsNet)
    TextView tvTipsNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inJect();
        presenter = initPresenter();
        presenter.attachView(this);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.act_common);

        ButterKnife.bind(this);
        initView();
        bindingEvent();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        View rootView = LayoutInflater.from(this).inflate(initRootView(), null);
        if (rootView == null) {
            return;
        }
        llWhole.addView(rootView);

    }


    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.attach((V) this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        release();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.dettach();
        }
        super.onDestroy();
    }


    @Override
    public void showMsg(String msg) {
        ToastUtils.showShortToast(msg);
    }

    //注册该界面，每个界面都要实现
    public abstract void inJect();

    //初始化协调者
    public abstract T initPresenter();

    //初始化布局文件id
    public abstract int initRootView();

    //初始化View
    public abstract void initView();

    //绑定事件
    public void bindingEvent() {
    }

    //释放资源
    protected void release() {

    }

    /**
     * 将网络请求绑定到生命周期
     *
     * @return
     */
    public LifecycleTransformer getLifecycleTransformer() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    @OnClick(R2.id.tv_back)
    @Override
    public void back() {
        ActivitySwitcher.addAnimR2L(this);
        ActivitySwitcher.finish(this);
    }

    @Override
    public void setBackText(String backText) {
        TypeSafer.text(tvBack, backText);
    }

    @Override
    public void setTime(String time) {
        TypeSafer.text(tvTime, time);
    }

    @Override
    public void hideInput() {
        // 隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
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

    @Override
    public void hideStatusBar() {
        rlStatusBar.setVisibility(View.GONE);
    }

    @Override
    public void showStatusBar() {
        rlStatusBar.setVisibility(View.VISIBLE);
    }
}
