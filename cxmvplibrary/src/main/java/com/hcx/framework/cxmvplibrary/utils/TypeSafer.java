package com.hcx.framework.cxmvplibrary.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class TypeSafer {

    public static void text(final TextView tv, final String txt) {
        if (null != tv) {
            tv.setText((null != txt) ? txt : "");
        }
    }

    public static void text(final TextView tv,final CharSequence txt){
        if (null != tv) {
            tv.setText((null != txt) ? txt : "");
        }
    }

    public static void textNotNull(final TextView tv, final String txt) {
        if (null != tv && null != txt) {
            tv.setText(txt);
        }
    }

    public static void textNotEmpty(final TextView tv, final String txt) {
        if (null != tv && !TextUtils.isEmpty(txt)) {
            tv.setText(txt);
        }
    }

    private static int getInt(final Integer integer) {
        return null != integer ? integer.intValue() : 0;
    }

    private static long getLong(final Long longData) {
        return null != longData ? longData.longValue() : 0;
    }

    public static void textInteger(final TextView tv, final String s) {
        if (null != tv && null != s) {
            tv.setText(String.format(Locale.getDefault(), "%.0f", parseFloat(s)));
        }
    }

    public static boolean getBoolean(final Boolean b) {
        return null != b && b;
    }

    public static void image(final ImageView iv, final Bitmap bitmap) {
        if (null != iv && null != bitmap && !bitmap.isRecycled()) {
            iv.setImageBitmap(bitmap);
        }
    }

    public static int parseInt(final String strValue) {
        if (null == strValue || strValue.isEmpty()) {
            return 0;
        }
        Integer IntValue = null;
        try {
            IntValue = Integer.parseInt(strValue);
        } catch (Exception e) {
        }
        return getInt(IntValue);
    }
    public static long parseLong(final String strValue) {
        if (null == strValue || strValue.isEmpty()) {
            return 0;
        }
        Long IntValue = null;
        try {
            IntValue = Long.parseLong(strValue);
        } catch (Exception e) {
        }
        return getLong(IntValue);
    }
    public static float parseFloat(final String strValue) {
        if (null == strValue || strValue.isEmpty()) {
            return 0;
        }
        Float value = null;
        try {
            value = Float.parseFloat(strValue);
        } catch (Exception e) {
        }
        return null == value ? 0 : value;
    }

}
