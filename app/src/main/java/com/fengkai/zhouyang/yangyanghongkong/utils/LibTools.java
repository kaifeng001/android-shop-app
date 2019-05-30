package com.fengkai.zhouyang.yangyanghongkong.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fengkai.zhouyang.yangyanghongkong.application.MainAplication;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by lory on 2017/2/15.
 */
public class LibTools {

    // 域名解析设置读取的锁
    private static final Object LOCK = new Object();

    /**
     * 年-月-日
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 年-月-日 时-分-秒
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static Typeface iconfont = null;//矢量图字体库
    public static Typeface paxIconfont = null;//平安行矢量图字体库
    public static Typeface numIconfont = null;//矢量图字体库
    public static final String SYS_EMUI = "sys_emui";
    public static final String SYS_MIUI = "sys_miui";
    public static final String SYS_FLYME = "sys_flyme";
    public static final String SYS_VIVO = "sys_vivo";
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    private static final String KEY_EMUI_API_LEVEL = "ro.build.hw_emui_api_level";
    private static final String KEY_EMUI_VERSION = "ro.build.version.emui";
    private static final String KEY_EMUI_CONFIG_HW_SYS_VERSION = "ro.confg.hw_systemversion";
    private static final String KEY_VIVO_OS_VERSION = "ro.vivo.os.version";

    private static int mCurrentPage = -1;

    private static boolean appOnForeground = false;


    public static boolean isEmpty(String value) {
        if (value == null || value.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static int dp2px(int dp) {
        // 1px = 1dp * (dpi / 160)

        DisplayMetrics metrics = MainAplication.getInstance().getApplicationContext().getResources().getDisplayMetrics();
        int dpi = metrics.densityDpi;

        return (int) (dp * (dpi / 160f) + 0.5f);
    }


    /**
     * md5 32位小写加密
     *
     * @param sSecret
     * @return
     */
    public static String getMd5Value(String sSecret) {
        if (sSecret == null) {
            return null;
        }
        try {
            MessageDigest bmd5 = MessageDigest.getInstance("MD5");
            bmd5.update(sSecret.getBytes());
            int i;
            StringBuffer buf = new StringBuffer();
            byte[] b = bmd5.digest();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 验证短信验证码格式
     *
     * @param code
     * @return bool 是否符合短信验证码
     */
    public static boolean verifyCode(String code) {
        if (TextUtils.isEmpty(code)) {
            return false;
        }
        Pattern p = Pattern.compile("^\\d{4,6}$");
        Matcher m = p.matcher(code);
        return m.matches();
    }

    /**
     * 验证手机号码
     *
     * @param phone
     * @return bool 是否是手机号
     */
    public static boolean verifyMobileNo(String phone) {
        Pattern p = Pattern
                .compile("^0?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9]|16[0-9]|19[0-9])[0-9]{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    // 手机号格式化xxx xxxx xxxx
    public static String formatPhoneNumber(String phone) {
        if (!verifyMobileNo(phone)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < phone.length(); i++) {
            if (i != 3 && i != 8 && phone.charAt(i) == ' ') {
                continue;
            } else {
                stringBuilder.append(phone.charAt(i));
                if ((stringBuilder.length() == 4 || stringBuilder
                        .length() == 9) && stringBuilder.charAt(stringBuilder.length() - 1) != ' ') {
                    stringBuilder.insert(stringBuilder.length() - 1, ' ');
                }
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 判断字符串是否是数字
     *
     * @param str 需要判断的字符串
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            int chr = str.charAt(i);
            if (chr < 48 || chr > 57) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public static boolean isAppOnForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null) {
            return false;
        }

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName) && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

    public static boolean isAppOnForeground() {
        return appOnForeground;
    }

    public static void setAppOnForeground(boolean state) {
        appOnForeground = state;
    }

    /**
     * 判断当前进程是否是主进程
     *
     * @param context
     * @return
     */
    public static boolean checkPID(Context context) {
        if (context == null) {
            return false;
        }
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessList = mActivityManager.getRunningAppProcesses();
        if (appProcessList == null || appProcessList.isEmpty()) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcessList) {
            if (appProcess.pid == pid) {
                if ("com.pingan.carowner".equalsIgnoreCase(appProcess.processName)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * @param format {@link #YYYY_MM_DD}
     * @return 固定格式 的今天时间
     */
    public static String getToday(String format) {
        return getStringByDate(new Date(), format);
    }

    /**
     * @param data   被 转换的时间
     * @param format {@link #YYYY_MM_DD}
     * @return
     */
    public static String getStringByDate(Date data, String format) {
        if (null == data) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format,
                Locale.getDefault());
        return dateFormat.format(data);
    }

    /**
     * isConnectNet(判断网络状态)
     *
     * @return boolean变量
     */
    public static boolean isConnectNet(Context context) {
        try {
            ConnectivityManager con = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo.State mobile = con.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
            NetworkInfo.State wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
            if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
                // Toast.makeText(context, "移动数据可用", Toast.LENGTH_SHORT).show();
                return true;
            } else if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
                // Toast.makeText(context, "wifi可用", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * isConnectNet(判断网络状态)
     *
     * @return boolean变量
     */
    public static boolean isConnectWifi(Context context) {
        ConnectivityManager con = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State wifi = con.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
            return true;
        } else {
            return false;
        }
    }

    public static Typeface getIconFont(Context context) {
        if (iconfont == null) {
            iconfont = Typeface.createFromAsset(context.getAssets(), "fonts/iconfont.ttf");
        }
        return iconfont;
    }

    public static Typeface getNumIconFont(Context context) {
        if (numIconfont == null) {
            numIconfont = Typeface.createFromAsset(context.getAssets(), "fonts/icommon.ttf");
        }
        return numIconfont;


    }

    /**
     * 图片读取
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 隐藏软件盘
     */
    public static void hideKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

    }

    /**
     * 隐藏软件盘
     */
    public static void hideSwitchKeyboard(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(),
                0);

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 判断是否是同一天
     */
    public static boolean isSameDay(Date d1, Date d2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(d1);
        cal2.setTime(d2);
        boolean sameDay = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        return sameDay;
    }

    public static int calDiffDay(Date date1, Date date2) {
        long difftime = date1.getTime() - date2.getTime();
        long nd = 1000 * 24 * 60 * 60;//每天毫秒数
        long diffday = difftime / nd;   // 计算差多少天
        return (int) diffday;
    }

    /**
     * 根据 timestamp 生成各类时间状态串
     *
     * @param timestamp 距1970 00:00:00 GMT的秒数
     * @return 时间状态串(如 ： 刚刚 、 5分钟前 、 5小时前 、 昨天 、 前天 、 月日 、 年月日)
     */
    public static String getTimeStatus(String timestamp) {
        if (timestamp == null || "".equals(timestamp)) {
            return "";
        }

        try {
            timestamp = formatTimestamp(timestamp);
            long _timestamp = Long.parseLong(timestamp);
            if (System.currentTimeMillis() - _timestamp < 60 * 1000) {
                return "刚刚";
            } else if (System.currentTimeMillis() - _timestamp < 60 * 60 * 1000) {
                return ((System.currentTimeMillis() - _timestamp) / 1000 / 60)
                        + "分钟前";
            } else if (System.currentTimeMillis() - _timestamp < 24 * 60 * 60 * 1000) {
                return ((System.currentTimeMillis() - _timestamp) / 1000 / 60 / 60)
                        + "小时前";
            } else {
                Calendar now = Calendar.getInstance();
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(_timestamp);
                SimpleDateFormat sdf;
                if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                        && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                        && now.get(Calendar.DATE) - c.get(Calendar.DATE) == 1) {
                    sdf = new SimpleDateFormat("昨天", Locale.CHINA);
                    return sdf.format(c.getTime());
                } else if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                        && c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                        && now.get(Calendar.DATE) - c.get(Calendar.DATE) == 2) {
                    sdf = new SimpleDateFormat("前天", Locale.CHINA);
                    return sdf.format(c.getTime());
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                            Locale.CHINA);
                    return dateFormat.format(_timestamp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDealNum(String count) {
        String countStr = count;
        if (!TextUtils.isEmpty(countStr)) {
            double dCount = Double.parseDouble(countStr);
            if (dCount > 9999) {
                DecimalFormat df = new DecimalFormat("0.0");
                countStr = df.format(dCount / 10000) + "万";
            }
        } else {
            countStr = "0";
        }
        return countStr;
    }


    public static String getPersonNum(int count) {
        String countStr = count + "";
        if (!TextUtils.isEmpty(countStr)) {
            double dCount = Double.parseDouble(countStr);
            if (dCount > 999) {
                DecimalFormat df = new DecimalFormat("0.0");
                countStr = df.format(dCount / 1000) + "K";
            }
        } else {
            countStr = " ";
        }
        return countStr;
    }

    /**
     * 对时间戳格式进行格式化，保证时间戳长度为13位
     *
     * @param timestamp 时间戳
     * @return 返回为13位的时间戳
     */
    public static String formatTimestamp(String timestamp) {
        if (timestamp == null || "".equals(timestamp)) {
            return "";
        }
        String tempTimeStamp = timestamp + "00000000000000";
        StringBuffer stringBuffer = new StringBuffer(tempTimeStamp);
        return stringBuffer.substring(0, 13);
    }

    public static void addUnderline(TextView textView) {
        textView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        textView.getPaint().setAntiAlias(true);
    }

    //去除无用的零
    public static String removeTailZero(double money) {
        if (money - (int) money == 0) {
            return String.valueOf((int) money);
        } else {
            return String.valueOf(money);
        }
    }

    public static Double string2Double(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return 0.0;
        }
    }


    /**
     * 隐藏软键盘
     */
    public static void hideSoftKey(Context ctx, EditText ed) {
        if (ctx != null) {
            InputMethodManager imm;
            imm = (InputMethodManager) ctx
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(ed.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

    }


    /**
     * 显示软键盘
     */
    public static void showSoftKeyboard(final Context context, final View view) {
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
            }
        }, 200);
    }

    /**
     * 解析出url参数中的键值对
     *
     * @return url请求参数部分
     */
    public static Map<String, String> URLRequest(String strUrlParam) {
        Map<String, String> mapRequest = new HashMap<String, String>();

        String[] arrSplit = null;

        if (strUrlParam == null) {
            return mapRequest;
        }
        arrSplit = strUrlParam.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("[=]");
            //解析出键值
            if (arrSplitEqual.length > 1) {
                //正确解析
                mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);

            } else {
                if (!"".equals(arrSplitEqual[0])) {
                    //只有参数没有值，不加入
                    mapRequest.put(arrSplitEqual[0], "");
                }
            }
        }
        return mapRequest;
    }


    // 通过文件头来判断是否gif
    public static boolean isGifByFile(File file) {
        try {
            int length = 10;
            InputStream is = new FileInputStream(file);
            byte[] data = new byte[length];
            is.read(data);
            String type = getType(data);
            is.close();

            if ("gif".equals(type)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private static String getType(byte[] data) {
        String type = "";
        if (data[1] == 'P' && data[2] == 'N' && data[3] == 'G') {
            type = "png";
        } else if (data[0] == 'G' && data[1] == 'I' && data[2] == 'F') {
            type = "gif";
        } else if (data[6] == 'J' && data[7] == 'F' && data[8] == 'I'
                && data[9] == 'F') {
            type = "jpg";
        }
        return type;
    }

    /**
     * 保存图片之相册
     *
     * @param context
     * @param bmp
     */

    /**
     * 保存图片之相册
     *
     * @param context
     * @param bmpFile
     */
    public static void saveImageToGallery(final Context context, File bmpFile) {
        if (bmpFile == null) {
            return;
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    bmpFile.getAbsolutePath(), bmpFile.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        MediaScannerConnection.scanFile(context,
                new String[]{bmpFile.getAbsolutePath()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                    }
                });
    }


    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public static final boolean isGPSopen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        if (gps) {
            return true;
        }
        return false;
    }

    /**
     * 从字符串中截取连续4位数字 用于从短信中获取动态密码
     *
     * @param str 短信内容
     * @return 截取得到的4位动态密码
     */
    public static String getDynamicPassword(String str) {
        Pattern continuousNumberPattern = Pattern.compile("[0-9\\.]+");
        Matcher m = continuousNumberPattern.matcher(str);
        String dynamicPassword = "";
        while (m.find()) {
            if (m.group().length() == 4) {
                dynamicPassword = m.group();
            }
        }
        return dynamicPassword;
    }

    /**
     * 从字符串中截取连续4位数字 用于从短信中获取动态密码
     *
     * @param str 短信内容
     * @return 截取得到的4位动态密码
     */
    public static String getDynamicPasswordSix(String str) {
        Pattern continuousNumberPattern = Pattern.compile("[0-9\\.]+");
        Matcher m = continuousNumberPattern.matcher(str);
        String dynamicPassword = "";
        while (m.find()) {
            if (m.group().length() == 6) {
                dynamicPassword = m.group();
            }
        }
        return dynamicPassword;
    }


    public static String getNameByUrl(Context context, String url) {
        int start = url.indexOf("//");
        String key;
        if (start >= 0) {
            key = url.substring(url.indexOf("/", start + 2) + 1, url.length());

        } else {
            key = url.substring(url.indexOf("/", 0) + 1, url.length());
        }

        String key1 = key.replace("/", "");
        return key1;
    }

    public static String changeText(String frame) {
        if (frame == null || frame.length() == 0) {
            return "";
        } else if (frame.length() < 12) {
            return frame;
        } else {
            String str1 = frame.substring(0, 4);
            String str2 = frame.substring(4, 8);
            String str3 = frame.substring(8, 12);
            String str4 = frame.substring(12);
            return str1 + " " + str2 + " " + str3 + " " + str4;
        }
    }

    public static String getStarPhone(String string) {
        if (string != null && string.length() >= 11) {
            String result = string.substring(0, string.length() - 4);
            result = result + "****";
            return result;
        } else {
            return "";
        }
    }

    public static boolean hasArticleId(String url) {
        if (url.contains("?")) {
            String str = url.substring(url.indexOf("?") + 1);
            Map<String, String> params = paramsStringToMap(str);
            String article_id = params.get("article_id");
            if (!TextUtils.isEmpty(article_id)) {
                return true;
            }
        }
        return false;
    }

    public static Map<String, String> paramsStringToMap(String params) {
        Map<String, String> map = new HashMap<>();
        if (TextUtils.isEmpty(params)) {
            return map;
        } else {
            if (params.contains("&")) {
                String[] pairs = params.split("&");
                for (int i = 0; i < pairs.length; i++) {
                    if (pairs[i].contains("=")) {
                        String[] param = pairs[i].split("=");
                        if (param.length >= 2) {
                            map.put(param[0], param[1]);
                        } else if (param.length >= 1) {
                            map.put(param[0], "");
                        }
                    } else {
                        map.put(pairs[i], "");
                    }
                }
                return map;
            } else if (params.contains("=")) {
                String[] param = params.split("=");
                if (param.length >= 2) {
                    map.put(param[0], param[1]);
                } else if (param.length >= 1) {
                    map.put(param[0], "");
                }
                return map;
            } else {
                return map;
            }
        }
    }


    public static String formatDate(Date date, String format) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static Date parse(String strDate, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将字符串转换成Bitmap类型并保存到本地
     */
    public static String base64ToBitmap(String base64) {

        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(base64, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
            bitmap = setAlphToWhite(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = "";
        if (bitmap != null) {
            result = saveBitmap(bitmap);
        }
        return result;
    }

    /*
     * 背景透明的图片，在微信端会显示为黑底，这里把透明背景设置为白色
     * */
    public static Bitmap setAlphToWhite(Bitmap src) {
        Bitmap bitmap = src.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        int width = src.getWidth();
        int height = src.getHeight();
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, width, height, paint);
        canvas.drawBitmap(src, 0, 0, paint);
        return bitmap;
    }

    public static String saveBitmap(Bitmap bm) {
        File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/carowner");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/carowner/share_h5.png");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (f.exists()) {
            return f.getAbsolutePath();
        }
        return "";
    }

    /**
     * 图片转base64
     */
    public static String _bitmapToBase64String(String filePath) {
        try {
            Bitmap bm = BitmapFactory.decodeFile(filePath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if (null == bm) {
                return "";
            }
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

            byte[] b = baos.toByteArray();
            baos.close();
            return Base64.encodeToString(b, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取屏幕宽和高
     *
     * @param context
     * @return
     */
    public static int[] getScreenHW(Context context) {
        int[] hw = new int[3];
        try {
            WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics dm = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(dm);
            hw[0] = dm.widthPixels;//屏幕宽带(像素)
            hw[1] = dm.heightPixels;//屏幕高度(像素)
            hw[2] = dm.densityDpi;//屏幕密度(120/160/240)
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hw;
    }

    /**
     * 手机屏幕宽度
     *
     * @param ctx
     * @return
     */
    public static int getWindowWidth(Context ctx) {
        Display display = ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        return metrics.widthPixels;
    }

    /**
     * 手机屏幕高度
     *
     * @param ctx
     * @return
     */
    public static int getWindowHeight(Context ctx) {

        Display display = ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        return metrics.heightPixels;
    }

    /**
     * 回收imageview使用的image
     */
    public static void recycleImageView(ImageView view) {
        try {
            if (view == null) {
                return;
            }
            Drawable drawable = view.getDrawable();
            if (drawable instanceof BitmapDrawable) {
                Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
                if (bmp != null && !bmp.isRecycled()) {
                    view.setImageBitmap(null);
                    bmp.recycle();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean abs24hours(String time) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
            Date date = sdf.parse(time);
            long _timestamp = date.getTime();
            if (Math.abs(System.currentTimeMillis() - _timestamp) < 24 * 60 * 60 * 1000) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getAlaphColor(String value, String color) {
        try {
            int a = (int) Float.parseFloat(value) * 255;
            if (a < 0) {
                a = 0;
            } else if (a > 255) {
                a = 255;
            }
            String hex = Integer.toHexString(a);
            if (hex.length() < 2) {
                hex = "0" + hex;
            }

            return "#" + hex + color.substring(1, color.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return color;
    }

    public static String getSystem() {
        String SYS = "";
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            if (prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null) {
                SYS = SYS_MIUI;//小米
            } else if (prop.getProperty(KEY_EMUI_API_LEVEL, null) != null
                    || prop.getProperty(KEY_EMUI_VERSION, null) != null
                    || prop.getProperty(KEY_EMUI_CONFIG_HW_SYS_VERSION, null) != null) {
                SYS = SYS_EMUI;//华为
            } else if (getMeizuFlymeOSFlag().toLowerCase().contains("flyme")) {
                SYS = SYS_FLYME;//魅族
            } else if (prop.getProperty(KEY_VIVO_OS_VERSION, null) != null) {
                SYS = SYS_VIVO;//VIVO
            }
        } catch (IOException e) {
            e.printStackTrace();
            //没有存储权限，反射方式再调下
            if( SystemProperties.getOrNull(KEY_MIUI_VERSION_CODE) != null
                    || SystemProperties.getOrNull(KEY_MIUI_VERSION_NAME) != null
                    || SystemProperties.getOrNull(KEY_MIUI_INTERNAL_STORAGE) != null) {
                SYS = SYS_MIUI;
            } else if (SystemProperties.getOrNull(KEY_EMUI_API_LEVEL) != null
                    || SystemProperties.getOrNull(KEY_EMUI_VERSION) != null
                    || SystemProperties.getOrNull(KEY_EMUI_CONFIG_HW_SYS_VERSION) != null) {
                SYS = SYS_EMUI;
            } else if (getMeizuFlymeOSFlag().toLowerCase().contains("flyme")) {
                SYS = SYS_FLYME;//魅族
            } else if (SystemProperties.getOrNull(KEY_VIVO_OS_VERSION) != null) {
                SYS = SYS_VIVO;//VIVO
            }
            return SYS;
        }
        return SYS;
    }

    public static String getMeizuFlymeOSFlag() {
        return getSystemProperty("ro.build.display.id", "");
    }

    private static String getSystemProperty(String key, String defaultValue) {
        try {
            Class<?> clz = Class.forName("android.os.SystemProperties");
            Method get = clz.getMethod("get", String.class, String.class);
            return (String) get.invoke(clz, key, defaultValue);
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public static void openStart(Context context){
        if(Build.VERSION.SDK_INT < 23){
            return;
        }
        String system = LibTools.getSystem();
        Intent intent = new Intent();
        if(system.equals(LibTools.SYS_EMUI)){//华为
            ComponentName componentName = new ComponentName("com.huawei.systemmanager","com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
            intent.setComponent(componentName);
        }else if(system.equals(LibTools.SYS_MIUI)){//小米
            ComponentName componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
            intent.setComponent(componentName);
        }
        try{
            context.startActivity(intent);
        }catch (Exception e){//抛出异常就直接打开设置页面
            intent=new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }
    }

    /**
     * 计算两个时间相隔天数
     *
     * @return
     */
    public static double getDateDifCount(String timeDay, String today) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");//输入日期的格式
        Date date1 = null;

        try {
            date1 = simpleDateFormat.parse(timeDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date2 = null;
        try {
            date2 = simpleDateFormat.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        GregorianCalendar cal1 = new GregorianCalendar();
        GregorianCalendar cal2 = new GregorianCalendar();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return (cal2.getTimeInMillis() - cal1.getTimeInMillis()) / (1000 * 3600 * 24);
    }

    public static long getTimesMorning() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return (calendar.getTimeInMillis());

    }


    public static int getDigterInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }


    /**
     * 将字符串转换成Bitmap类型并保存到本地
     */
    public static String base64ToBitmapForH5(String base64, int type) {

        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(base64, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = "";
        if (bitmap != null) {
            File fileDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/carowner");
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/carowner/share_for_h5.jpeg");
            if (f.exists()) {
                f.delete();
            }
            try {
                FileOutputStream out = new FileOutputStream(f);
                if (type < Bitmap.CompressFormat.values().length) {
                    bitmap.compress(Bitmap.CompressFormat.values()[type], 95, out);
                } else {
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 95, out);
                }
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (f.exists()) {
                return f.getAbsolutePath();
            }
            return "";
        }
        return result;
    }


    /*
     * 保存base64字符串图片到本地
     */
    public static void savePhotoToSDCard(final Context context, final String base64, final String imageName, final String imagePath, final int quality, final int compressFormat) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/carowner/";
        String name = String.valueOf(System.currentTimeMillis());
        final int t_quality;
        if (!TextUtils.isEmpty(imagePath)) {
            path += imagePath;
        }
        if (!TextUtils.isEmpty(imageName)) {
            name = imageName;
        }
        switch (compressFormat) {
            case 0:
                name += ".JPEG";
                break;
            case 1:
                name += ".PNG";
                break;
            case 2:
                name += ".WEBP";
                break;
            default:
                name += ".JPEG";
        }
        if (quality <= 10 || quality > 100) {
            t_quality = 100;
        } else {
            t_quality = quality;
        }
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        final File file = new File(path + "/" + name);
        if (file.exists()) {
            file.delete();
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] decodeBase64 = Base64.decode(base64, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodeBase64, 0, decodeBase64.length);
                    FileOutputStream out = new FileOutputStream(file);
                    if (compressFormat < Bitmap.CompressFormat.values().length) {
                        bitmap.compress(Bitmap.CompressFormat.values()[compressFormat], t_quality, out);
                    } else {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, t_quality, out);
                    }
                    out.flush();
                    out.close();
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri contentUri = Uri.fromFile(file);
                    mediaScanIntent.setData(contentUri);
                    context.sendBroadcast(mediaScanIntent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("savePhotoToSDCard");
        thread.start();
    }

    private final static String IM_SOURCE_STR = "native://onlineConsultation?entityId=11&params={\"fromid\":\"%s\",\"fromdesc\":\"用户手机号%s在%s环节请求咨询\"}";

    public static String getConsultation(String entityId, String params) {
        if (TextUtils.isEmpty(params)) {
            return String.format("native://onlineConsultation?entityId=%s", entityId);
        } else {
            return String.format("native://onlineConsultation?entityId=%s&params=%s", entityId, params);
        }
    }

    /**
     * 时间戳转化为Sting
     *
     * @param t 时间戳
     * @return 时间  eg: 1970-01-06 11:45:55
     */
    public static String timeFormat(String t) {
        if (TextUtils.isEmpty(t)) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(new Date(Long.valueOf(t + "000")));
        String substring = d.substring(d.indexOf("-") + 1, d.lastIndexOf(":"));
        return substring;
    }

    public static String formatDate(String src) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = fmt.parse(src);
            return fmt.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return src;
    }

    /**
     * 将简单的json转成map
     */
    public static HashMap<String, Object> JsonObject2HashMap(JSONObject jo) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            for (Iterator<String> keys = jo.keys(); keys.hasNext(); ) {
                String key1 = keys.next();
                map.put(key1, jo.opt(key1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 判断某一时间是否在一个区间内
     *
     * @param sourceTime 时间区间,半闭合,如[10:00-20:00)
     * @param curTime    需要判断的时间 如10:00
     * @return
     * @throws IllegalArgumentException
     */
    public static boolean isInTime(String sourceTime, String curTime) {
        if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
            return false;
        }
        if (curTime == null || !curTime.contains(":")) {
            return false;
        }
        String[] args = sourceTime.split("-");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        try {
            long now = sdf.parse(curTime).getTime();
            long start = sdf.parse(args[0]).getTime();
            long end = sdf.parse(args[1]).getTime();
            if ("00:00".equals(args[1])) {
                args[1] = "24:00";
            }
            if (end < start) {
                if (now >= end && now < start) {
                    return false;
                } else {
                    return true;
                }
            } else {
                if (now >= start && now < end) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean hasLargeMemory() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        if (maxMemory >= 268435456) {
            return true;
        }
        return false;
    }

    public static int getProcessPid(Context context, String pkname) {
        int pid = -1;
        if (TextUtils.isEmpty(pkname) || context == null) {
            return pid;
        }

        int i = 1;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mRunningProcess = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo amProcess : mRunningProcess) {
            if (amProcess.processName.equals(pkname)) {
                pid = amProcess.pid;
                return pid;
            }
        }

        return pid;
    }

//    public static void setCurrentPage(int pos) {//lory 20181213 for 优化cpu
//        mCurrentPage = pos;
//    }
//
//    public static int getCurrentPage() {//lory 20181213 for 优化cpu
//        return mCurrentPage;
//    }


    public static long getCurrentMemory(Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
        if (activityManager != null) {
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return (memoryInfo.totalMem - memoryInfo.availMem) / (1024 * 1024);
        }
        return 0;
    }

    public static long getAppLimitMemory(Activity activity) {
        ActivityManager activityManager = (ActivityManager) activity.getSystemService(ACTIVITY_SERVICE);
        if (activityManager != null) {
            return activityManager.getMemoryClass();
        }
        return 0;
    }

    /**
     * 判断是否是特殊服务号（系统通知、用车助手、互动消息）
     *
     * @param entityId
     * @return
     */
    public static boolean isSpecialEntity(String entityId) {
        return "9".equals(entityId) || "24".equals(entityId) || "201".equals(entityId);
    }
}
