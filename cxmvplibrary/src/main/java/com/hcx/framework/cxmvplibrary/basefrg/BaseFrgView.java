package com.hcx.framework.cxmvplibrary.basefrg;


public interface BaseFrgView {

    void showMsg(String msg);

    //设置返回文字
    void setBackText(String backText);

    //隐藏键盘
    void hideInput();

    //隐藏网络提示
    void hideNetTips();

    //显示网络提示
    void showNetTips();

    //显示内存提示
    void hideMemoryTips();

    //隐藏内存提示
    void showMemoryTips();

    //隐藏返回按钮
    void hideBack();

    //显示返回按钮
    void showBack();
}
