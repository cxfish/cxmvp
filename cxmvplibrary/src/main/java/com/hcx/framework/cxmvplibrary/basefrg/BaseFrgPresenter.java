package com.hcx.framework.cxmvplibrary.basefrg;


public abstract class BaseFrgPresenter<T extends BaseFrgView> {
    //分页加载
    protected int pageSize;
    protected int pageIndex;
    protected String cityCode;
    public T mView;


    //frag -- onResume方法执行
    public void attach(T mView) {
        this.mView = mView;
    }

    //frag -- onDettach方法执行
    public void dettach() {
        mView = null;
    }

    //绑定View
    protected abstract void attachView(BaseFrgView frgView);



}
