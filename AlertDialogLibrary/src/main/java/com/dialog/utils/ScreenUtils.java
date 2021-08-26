package com.dialog.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Uesr: Jf
 * Time : 2018/1/18.
 * Annotations:屏幕 信息获取
 */

public class ScreenUtils {

    public static ScreenUtils getInstance(){
        return ScreenUtilsHoler.INSTANCE;
    }

    private static class ScreenUtilsHoler{
        private static ScreenUtils INSTANCE =new ScreenUtils();
    }

    public  Display DefaultDisplay(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(activity.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }

    public  Display DefaultDisplay_(Activity activity) {
        WindowManager wm = activity.getWindowManager();
        return wm.getDefaultDisplay();
    }

    public  DisplayMetrics DisplayMetrics(Activity activity) {
        WindowManager manager = activity.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    public  DisplayMetrics DisplayMetrics_(Activity activity) {
        Resources resources = activity.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm;
    }


    /**
     * dip转换为px
     * @param context
     * @param dpValue
     * @return
     */
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转换为dp
     * @param context
     * @param pxValue
     * @return
     */
    public  int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
