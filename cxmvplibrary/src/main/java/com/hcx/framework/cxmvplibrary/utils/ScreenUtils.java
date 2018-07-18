package com.hcx.framework.cxmvplibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;


import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/8/23.
 */
public class ScreenUtils {
    private static final String TAG = "ScreenUtils";

    private static double RATIO = 0.85;

    public static int screenWidth;
    public static int screenHeight;
    public static int screenMin;// 宽高中，较小的值
    public static int screenMax;// 宽高中，较大的值

    public static float density;
    public static float scaleDensity;
    public static float xdpi;
    public static float ydpi;
    public static int densityDpi;

    public static int dialogWidth;
    public static int statusbarheight;
    public static int navbarheight;
    public static float actionbarheight;

    public static void getInfo(Context context) {
        if (null == context) {
            return;
        }
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;//屏幕的宽，单位是像素
        screenHeight = dm.heightPixels;//屏幕的高，单位是像素
        screenMin = (screenWidth > screenHeight) ? screenHeight : screenWidth;
        screenMax = (screenWidth < screenHeight) ? screenHeight : screenWidth;
        density = dm.density;
        scaleDensity = dm.scaledDensity;
        xdpi = dm.xdpi;
        ydpi = dm.ydpi;
        densityDpi = dm.densityDpi;
        statusbarheight = getStatusBarHeight(context);
        navbarheight = getNavBarHeight(context);
        Log.d(TAG, "screenWidth=" + screenWidth + " screenHeight=" + screenHeight + " density=" + density);
        Log.d(TAG, "xdpi=" + xdpi + " ydpi=" + ydpi + " densityDpi=" + densityDpi + "scaleDensity" + scaleDensity);
    }

    public static int getStatusBarHeight(Context context) {
        if (statusbarheight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusbarheight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (statusbarheight == 0) {
            statusbarheight = ScreenUtils.dip2px(25);
        }
        return statusbarheight;
    }

    public static int getNavBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static int dip2px(float dipValue) {
        final float scale = ScreenUtils.getDisplayDensity();
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(float pxValue) {
        final float scale = ScreenUtils.getDisplayDensity();
        return (int) (pxValue / scale + 0.5f);
    }

    private static float getDisplayDensity() {
        if (density == 0) {
            getInfo(Utils.getContext());
        }
        return density;
    }

    public static int getDisplayWidth() {
        if (screenWidth == 0) {
            getInfo(Utils.getContext());
        }
        return screenWidth;
    }

    public static int getDisplayHeight() {
        getInfo(Utils.getContext());
        return screenHeight;
    }

    public static int getScreenMin() {
        if (screenMin == 0) {
            getInfo(Utils.getContext());
        }
        return screenMin;
    }

    public static int getScreenMax() {
        if (screenMin == 0) {
            getInfo(Utils.getContext());
        }
        return screenMax;
    }

    public static int getDialogWidth() {
        dialogWidth = (int) (getScreenMin() * RATIO);
        return dialogWidth;
    }

    public static float getScreenHeight() {
        float screenHeight = getDisplayHeight();
        float screenWidth = getDisplayWidth();
        if (screenWidth > screenHeight) {
            screenHeight = screenWidth;
        }
        float statusBarHeight = ScreenUtils.statusbarheight;
        float navHeight = navbarheight;
        String deviceName = android.os.Build.MANUFACTURER;
        if (!TextUtils.isEmpty(deviceName) && deviceName.toLowerCase().contains("meizu")) {
            int sbAutoHide = Settings.System.getInt(Utils.getContext().getContentResolver(), "mz_smartbar_auto_hide", 0);
            if (sbAutoHide == 1) {
                return screenHeight - statusBarHeight;
            }
            return screenHeight - statusBarHeight - navHeight;
        }
        return screenHeight - statusBarHeight;
    }

    public static int sp2px(float spValue) {
        final float fontScale = Utils.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(float pxValue) {
        final float fontScale = Utils.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
}
