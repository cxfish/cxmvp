package com.hcx.framework.cxmvplibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;


/**
 * 通用方法
 */
public class CommenUtils {

    public static void loadCircleImg(android.support.v4.app.Fragment context, String url, ImageView imageView, int defaultAvatar) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions
                .circleCrop()
                .error(defaultAvatar)
                .placeholder(defaultAvatar);
        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }

    public static void loadRoundImg(android.support.v4.app.Fragment context, String url, ImageView imageView, int defaultAvatar) {
        RequestOptions myOptions = new RequestOptions()
                .transform(new GlideRoundTransform(context.getActivity(), 6));
        myOptions
                .error(defaultAvatar)
                .placeholder(defaultAvatar);
        Glide.with(context).load(url).apply(myOptions).into(imageView);
    }



    public static String getWeekStr(String sdate) {
        String str = "";
        if ("7".equals(sdate)) {
            str = "星期日";
        } else if ("1".equals(sdate)) {
            str = "星期一";
        } else if ("2".equals(sdate)) {
            str = "星期二";
        } else if ("3".equals(sdate)) {
            str = "星期三";
        } else if ("4".equals(sdate)) {
            str = "星期四";
        } else if ("5".equals(sdate)) {
            str = "星期五";
        } else if ("6".equals(sdate)) {
            str = "星期六";
        }
        return str;
    }

    public static String getTurnStr(String sdate) {
        String str = "";
        if ("7".equals(sdate)) {
            str = "第七轮";
        } else if ("1".equals(sdate)) {
            str = "第一轮";
        } else if ("2".equals(sdate)) {
            str = "第二轮";
        } else if ("3".equals(sdate)) {
            str = "第三轮";
        } else if ("4".equals(sdate)) {
            str = "第四轮";
        } else if ("5".equals(sdate)) {
            str = "第五轮";
        } else if ("6".equals(sdate)) {
            str = "第六轮";
        } else if ("8".equals(sdate)) {
            str = "第八轮";
        } else if ("9".equals(sdate)) {
            str = "第九轮";
        }
        return str;
    }

    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        return str.length() == 11;
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    /**
     * 单独设置内部字体颜色
     *
     * @param text
     * @param keyworld
     * @return
     */
    public static SpannableStringBuilder getSpannableTextColor(String text, String keyworld) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
        if (text.contains(keyworld)) {
            int spanStartIndex = text.indexOf(keyworld);
            int spacEndIndex = spanStartIndex + keyworld.length();
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), spanStartIndex, spacEndIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        return spannableStringBuilder;
    }
}
