package com.peiyuan.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebView;

/**
 */
public class AppUtils {

    /**
     * 检测是否有网络连接。
     *
     * @param context
     * @return 是否网络在线。
     */
    public static boolean isNetworkOnline(Context context) {

        if (context == null)
            throw new IllegalArgumentException("context is null!");

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }

    }

    public static float getDensity(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        return displayMetrics.density;
    }


    public static final String PORTRAIT_DESIGN_240 = "portrait_design_240";
    public static final String PORTRAIT_DESIGN_180 = "portrait_design_180";
    //    public static final String PORTRAIT_DESIGN_120 = "portrait_design_120";
    public static final String PORTRAIT_DESIGN_96 = "portrait_design_96";
    public static final String PORTRAIT_DESIGN_64 = "portrait_design_64";

    /**
     * 将接口给的图片 url 按分辨率转成对应的图片大小 url。
     *
     * @param imageUrl   来自服务端的图片 url。
     * @param designSize 设计给的图片大小。
     * @return
     */
    public static String dealImageUrl(Context context, String imageUrl, String designSize) {

        if (TextUtils.isEmpty(imageUrl) || TextUtils.isEmpty(designSize)) {
            throw new IllegalArgumentException("imageUrl or designSize is null!");
        }

        String finalImageUrl = imageUrl;

        int density = (int) getDensity(context);
        Log.d("density", "density:" + density);

        switch (designSize) {

            case PORTRAIT_DESIGN_96:

                if (density == 1.5) {//75图 (s_ 宽高75)
                    if (finalImageUrl.contains("/s_")) {
                    }
                } else if (density >= 2.0) {//96图 (t_ 宽96)
                    if (finalImageUrl.contains("/s_")) {
                        finalImageUrl = finalImageUrl.replace("/s_", "/t_");
                    }
                }

                break;

            case PORTRAIT_DESIGN_180:

                if (density == 1.5) {//150图 (t150_ 宽150)
                    if (finalImageUrl.contains("/s_")) {
                        finalImageUrl = finalImageUrl.replace("/s_", "/t150_");
                    }
                } else if (density >= 2.0) {//240图 (m_ 宽240)
                    if (finalImageUrl.contains("/s_")) {
                        finalImageUrl = finalImageUrl.replace("/s_", "/m_");
                    }
                }

                break;

            case PORTRAIT_DESIGN_240:
                if (density == 1.5) {//t150图 (s_ 宽高75)
                     if (finalImageUrl.contains("/b_")) {
                        finalImageUrl = finalImageUrl.replace("/b_", "/m_");
                    }
                } else if (density >= 2.0) {//240图 (t_ 宽240)
                    if (finalImageUrl.contains("/b_")) {
                        finalImageUrl = finalImageUrl.replace("/b_", "/b45_");//b45:(450)//b:(640)
                    }
                }
                break;
        }

        return finalImageUrl;
    }

    /**
     * @param context
     * @param imageUrl
     * @param designSize
     * @return
     */
    public static String dealPortraitUrl(Context context, String imageUrl, String designSize) {

        if (TextUtils.isEmpty(imageUrl) || TextUtils.isEmpty(designSize)) {
            throw new IllegalArgumentException("imageUrl or designSize is null!");
        }

        String finalImageUrl = imageUrl;

        int density = (int) getDensity(context);
        Log.d("density", "density:" + density);

        switch (designSize) {

            case PORTRAIT_DESIGN_96:

                if (density == 1.5) {//75图 用96
                    if (finalImageUrl.contains("-")) {
                        String[] strArray = finalImageUrl.split("-");
                        finalImageUrl = strArray[0] + "-96.jpg";
                    }
                } else if (density >= 2.0) {//96图 用150
                    if (finalImageUrl.contains("-")) {
                        String[] strArray = finalImageUrl.split("-");
                        finalImageUrl = strArray[0] + "-150.jpg";
                    }
                }

                break;

            case PORTRAIT_DESIGN_180:

                if (density == 1.5) {//150图 (t15_ 宽150)
                    if (finalImageUrl.contains("-")) {
                        String[] strArray = finalImageUrl.split("-");
                        finalImageUrl = strArray[0] + "-150.jpg";
                    }
                } else if (density >= 2.0) {//240图 (m_ 宽240)
                    if (finalImageUrl.contains("-")) {
                        String[] strArray = finalImageUrl.split("-");
                        finalImageUrl = strArray[0] + "-240.jpg";
                    }
                }
                break;
            default:
                finalImageUrl = imageUrl;
                break;
        }

        return finalImageUrl;
    }


    /**
     * 获取应用版本号。
     *
     * @param context
     * @return 版本号。
     */

    public static String getAppVersion(Context context) {

        if (context == null)
            throw new IllegalArgumentException("context is null!");

        PackageManager packageManager = context.getPackageManager();
        String versionName = null;

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        Log.d("AppUtils", "getAppVersion:" + versionName);

        return versionName;
    }

    /**
     * 获取 UA。
     *
     * @param context
     * @return UA。
     */
    public static String getUserAgentString(Context context) {

        WebView webView = new WebView(context);
        webView.layout(0, 0, 0, 0);
        String ua = webView.getSettings().getUserAgentString();
        webView = null;

        Log.d("AppUtils", "getUserAgentString:" + ua);

        return ua;
    }

    /**
     * 获取设备唯一标识。
     *
     * @param context
     * @return deviceId。
     */
    public static String getDeviceId(Context context) {

        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        Log.d("AppUtils", "getDeviceId:" + tm.getDeviceId());

        return tm.getDeviceId();
    }


}
