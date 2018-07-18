package com.hcx.framework.cxmvplibrary.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hcx.framework.cxmvplibrary.R;
import com.hcx.framework.cxmvplibrary.baseact.BaseMvpActivity;
import com.hcx.framework.cxmvplibrary.baseact.NaviActivity;
import com.hcx.framework.cxmvplibrary.basefrg.BaseMvpFragment;


public class ActivitySwitcher {
    public static void startFragment(final Activity from,
                                     final Class<? extends Fragment> to) {
        final Bundle bundle = new Bundle();
        bundle.putString(NaviActivity.EXT_FRAGMENT, to.getName());
        start(from, NaviActivity.class, bundle);
    }

    public static void startFragment(final Activity from,
                                     final Class<? extends BaseMvpFragment> to, final Bundle bundle) {
        bundle.putString(NaviActivity.EXT_FRAGMENT, to.getName());
        start(from, NaviActivity.class, bundle);
    }

    public static void startFrgForResult(final Activity from,
                                         final Class<? extends BaseMvpFragment> to,
                                         final int requestCode) {
        startForResult(from,
                new Intent(from, NaviActivity.class).putExtra(NaviActivity.EXT_FRAGMENT, to.getName()),
                requestCode);
    }

    public static void startFrgForResult(final Activity from,
                                         final Class<? extends BaseMvpFragment> to,
                                         final Bundle bundle,
                                         final int requestCode) {
        bundle.putString(NaviActivity.EXT_FRAGMENT, to.getName());
        startForResult(from, new Intent(from, NaviActivity.class).putExtras(bundle), requestCode);
    }

    public static void popFragment(final Activity from,
                                   final Class<? extends BaseMvpFragment> to) {
        popFragment(from, to, new Bundle());
    }

    public static void popFragment(Activity from, Class<? extends BaseMvpFragment> to, Bundle bundle) {
        bundle.putString(NaviActivity.EXT_FRAGMENT, to.getName());
        Intent intent = new Intent(from, NaviActivity.class).putExtras(bundle);
        from.startActivity(intent);
        addAnim2T(from);
    }

    public static void popFrgForResult(final Activity from,
                                       final Class<? extends BaseMvpFragment> to,
//                                       final Bundle bundle,
                                       final int requestCode) {
//        bundle.putString(NaviActivity.EXT_FRAGMENT, to.getName());
//        Intent intent = new Intent(from, NaviActivity.class).putExtras(bundle);
        Intent intent = new Intent(from, NaviActivity.class);
        from.startActivityForResult(intent, requestCode);
        addAnim2T(from);
    }
    public static void popActForResult(final Activity from,
                                       final Class<? extends BaseMvpActivity> to,
//                                       final Bundle bundle,
                                       final int requestCode) {
//        bundle.putString(NaviActivity.EXT_FRAGMENT, to.getName());
//        Intent intent = new Intent(from, NaviActivity.class).putExtras(bundle);
        Intent intent = new Intent(from, to);
        from.startActivityForResult(intent, requestCode);
        addAnim2T(from);
    }
    public static void start(final Activity from,
                             final Class<? extends Activity> to, final Bundle bundle) {
        start(from, new Intent(from, to).putExtras(bundle));
    }

    public static void start(final Activity from,
                             final Class<? extends Activity> to) {
        start(from, new Intent(from, to));
    }

    public static void start(final Activity from, final Intent intent) {
        from.startActivity(intent);
        addAnimR2L(from);
    }

    public static void startForResult(final Activity from,
                                      final Intent intent, final int requestCode) {
        from.startActivityForResult(intent, requestCode);
        addAnimR2L(from);
    }

    public static void addAnimR2L(final Activity act) {
        act.overridePendingTransition(R.anim.in_from_right,
                R.anim.out_to_left);
    }

    private static void addAnim2R(final Activity act) {
        act.overridePendingTransition(0, R.anim.out_to_right);
    }

    private static void addAnim2T(final Activity act) {
        act.overridePendingTransition(R.anim.in_from_bottom, 0);
    }

    private static void addAnim2B(final Activity act) {
        act.overridePendingTransition(0, R.anim.out_to_bottom);
    }

    public static void finish(final Activity act) {
        act.finish();
        addAnim2R(act);
    }

    public static void finishDown(final Activity act) {
        act.finish();
        addAnim2B(act);
    }

    public static void goBackGround(final Activity act) {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        act.startActivity(intent);
    }

}
