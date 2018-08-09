package com.hcx.framework.cxmvplibrary.baseact;


public interface BaseActView {


    void showMsg(String msg);

    //销毁界面
    void back();

    //设置返回文字
    void setBackText(String backText);

    //设置时间
    void setTime(String time);

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

    //隐藏状态栏
    void hideStatusBar();

    //显示状态栏
    void showStatusBar();
}
