package com.hcx.framework.cxmvplibrary.baseact;


public abstract class BaseActPresenter<T extends BaseActView> {
    public T mView;

    public void attach(T mView) {
        this.mView = mView;
    }

    public void dettach() {
        mView = null;
    }

    //绑定View
    public abstract void attachView(BaseActView actView);


}
