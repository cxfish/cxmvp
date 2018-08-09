package com.hcx.framework.cxmvplibrary.baseact;

import android.os.Bundle;
import android.view.WindowManager;

import com.hcx.framework.cxmvplibrary.CxApplication;
import com.hcx.framework.cxmvplibrary.R;
import com.hcx.framework.cxmvplibrary.utils.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

/**
 * MvpActivity基类
 *
 * @param <V>
 * @param <T>
 */
public abstract class BaseMvpActivity<V extends BaseActView, T extends BaseActPresenter<V>> extends RxAppCompatActivity implements BaseActView {

    public T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inJect();
        presenter = initPresenter();
        presenter.attachView(this);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
        setContentView(R.layout.act_common);
        setContentView(initRootView());
        ButterKnife.bind(this);
        initView();
        bindingEvent();
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
    public void showMsg(String msg){
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
    public void bindingEvent(){}

    //释放资源
    protected void release(){

    }

    /**
     * 将网络请求绑定到生命周期
     *
     * @return
     */
    public LifecycleTransformer getLifecycleTransformer() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }
}
