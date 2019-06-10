package com.fengkai.zhouyang.yangyanghongkong.utils;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.fengkai.zhouyang.yangyanghongkong.application.MainAplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileUtil {
    public static final int GO_PHOTO = 2;

    /**
     * 判断sd卡是否存在
     *
     * @return
     */
    public static boolean existSDCard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isChildOf(File parent, File child) {
        if (null != parent && null != child) {

            try {
                String parentPath = parent.getCanonicalPath();
                String childPath = child.getCanonicalPath();
                return child.getCanonicalPath().startsWith(parent.getCanonicalPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        while (child != null) {
//            if (child.equals(parent)) {
//                return true;
//            }
//            child = child.getParentFile();
//        }
        return false;
    }

    /**
     * 在SD卡上创建一个文件夹
     *
     * @param filepath
     */
    public static String createSDCardDir(String filepath) {
        if (existSDCard()) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            // 得到一个路径，内容是sdcard的文件夹路径和名字
            String path = sdcardDir.getPath() + "/" + filepath;
            File path1 = new File(path);
            if (!path1.exists()) {
                // 若不存在，创建目录，可以在应用启动的时候创建
                path1.mkdirs();
            }
            return path;
        } else {
            return "";
        }
    }

    /**
     * 外部存储卡App私有目录的file根目录
     *
     * @return 返回上述目录，sdcard不存在或者有异常的话返回""。
     */
    public static String getSDCardFilePathOrEmpty() {
        if (existSDCard()) {
            File sdcardDir = MainAplication.getInstance().getExternalFilesDir(null);
            if (null != sdcardDir) {
                return sdcardDir.getAbsolutePath();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * 外部存储卡App私有目录的file根目录
     *
     * @return 返回上述目录，sdcard不存在或者有异常的话返回data下的file目录
     */
    public static String getSDCardFilePath() {
        if (existSDCard()) {
            File sdcardDir = MainAplication.getInstance().getExternalFilesDir(null);
            if (null != sdcardDir) {
                return sdcardDir.getAbsolutePath();
            } else {
                File cacheDir = MainAplication.getInstance().getFilesDir();
                return cacheDir.getAbsolutePath();
            }
        } else {
            File cacheDir = MainAplication.getInstance().getFilesDir();
            return cacheDir.getAbsolutePath();
        }
    }

    /**
     * 获取sdcard卡上App私有目录的file目录
     *
     * @return 返回上述目录，sdcard不存在或者有异常返回app data的file目录
     */
    public static File getSDCardDirFile() {
        if (existSDCard()) {
            File sdcardDir = MainAplication.getInstance().getExternalFilesDir(null);
            if (null != sdcardDir) {
                return sdcardDir;
            } else {
                File cacheDir = MainAplication.getInstance().getFilesDir();
                return cacheDir;
            }
        } else {
            File cacheDir = MainAplication.getInstance().getFilesDir();
            return cacheDir;
        }
    }

    /**
     * 外部存储卡App私有目录的cache根目录
     *
     * @return 返回上述目录，sdcard不存在或者有异常的话返回""。
     */
    public static String getSDCardCachePathOrEmpty() {
        if (existSDCard()) {
            File sdcardDir = MainAplication.getInstance().getExternalCacheDir();
            if (null != sdcardDir) {
                return sdcardDir.getAbsolutePath();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * 外部存储卡App私有目录的cache根目录
     *
     * @return 返回上述目录，sdcard不存在或者有异常的话返回data下的cache目录
     */
    public static String getSDCardCachePath() {
        if (existSDCard()) {
            File sdcardDir = MainAplication.getInstance().getExternalCacheDir();
            if (null != sdcardDir) {
                return sdcardDir.getAbsolutePath();
            } else {
                File cacheDir = MainAplication.getInstance().getCacheDir();
                return cacheDir.getAbsolutePath();
            }
        } else {
            File cacheDir = MainAplication.getInstance().getCacheDir();
            return cacheDir.getAbsolutePath();
        }
    }


    /**
     * 获取sdcard卡上App私有目录的cache目录
     *
     * @return 返回上述目录，sdcard不存在或者有异常返回app data目录的cache目录
     */
    public static File getSDCardCacheFile() {
        if (existSDCard()) {
            File sdcardDir = MainAplication.getInstance().getExternalCacheDir();
            if (null != sdcardDir) {
                return sdcardDir;
            } else {
                File cacheDir = MainAplication.getInstance().getCacheDir();
                return cacheDir;
            }
        } else {
            File cacheDir = MainAplication.getInstance().getCacheDir();
            return cacheDir;
        }
    }

    /**
     * 下载保存文件
     *
     * @param filepath
     * @param _urlStr
     */
    public static void downloadFile(String filepath, String _urlStr) {
        File file = new File(filepath);
        // 如果目标文件已经存在，则删除。产生覆盖旧文件的效果
        if (file.exists()) {
            file.delete();
        }
        try {
            // 构造URL
            URL url = new URL(_urlStr);
            // 打开连接
            URLConnection con = url.openConnection();
            // 获得文件的长度
//			int contentLength = con.getContentLength();
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(filepath);
            // 开始读取
            try {
                while ((len = is.read(bs)) != -1) {
                    os.write(bs, 0, len);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 完毕，关闭所有链接
                os.close();
                os.flush();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件的内容
     *
     * @param filename 文件名称
     * @return
     */
    public static String readFile(String path, String filename) {
        FileInputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        try {
            // 获得输入流
            inStream = new FileInputStream(new File(path, filename));
            // new一个缓冲区
            byte[] buffer = new byte[1024];
            int len;
            // 使用ByteArrayOutputStream类来处理输出流
            outStream = new ByteArrayOutputStream();
            while ((len = inStream.read(buffer)) != -1) {
                // 写入数据
                outStream.write(buffer, 0, len);
            }
            // 得到文件的二进制数据
            byte[] data = outStream.toByteArray();
            // 关闭流
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    public static double getFolderSize(String filepath) throws Exception {
        File file = new File(filepath);
        double size = 0;
        File[] fileList = file.listFiles();
        if (fileList != null) {
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i].getPath());
                } else {
                    size = size + fileList[i].length();
                }
            }
        }
        return size / 1024;
    }

    public static void delete(final File file) {
        if (null == file) {
            return;
        }
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            int size = files == null ? 0 : files.length;
            for (int i = 0; i < size; i++) {
                delete(files[i]);
            }
        } else {
            file.delete();
        }
    }

    /**
     * Unzip a zip file.  Will overwrite existing files.
     *
     * @param zipFile Full path of the zip file you'd like to unzip.
     * @param location Full path of the directory you'd like to unzip to (will be created if it doesn't exist).
     * @throws IOException
     */
    private static final int BUFFER_SIZE = 2048;

    public static boolean unzip(String zipFile, String location) throws IOException {
        int size;
        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            if (!location.endsWith(File.separator)) {
                location += File.separator;
            }
            File f = new File(location);
            if (!f.exists()) {
                f.mkdirs();
            }
            ZipInputStream zin = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile), BUFFER_SIZE));
            try {
                ZipEntry ze;
                while ((ze = zin.getNextEntry()) != null) {
                    String path = location + ze.getName();
                    if (ze.getName().contains("../")) {
                        continue;
                    }
                    File unzipFile = new File(path);
                    if (ze.isDirectory()) {
                        if (!unzipFile.isDirectory()) {
                            unzipFile.mkdirs();
                        }
                    } else {
                        // check for and create parent directories if they don't exist
                        File parentDir = unzipFile.getParentFile();
                        if (null != parentDir) {
                            if (!parentDir.isDirectory()) {
                                parentDir.mkdirs();
                            }
                        }
                        // unzip the file
                        FileOutputStream out = new FileOutputStream(unzipFile, false);
                        BufferedOutputStream fout = new BufferedOutputStream(out, BUFFER_SIZE);
                        try {
                            while ((size = zin.read(buffer, 0, BUFFER_SIZE)) != -1) {
                                fout.write(buffer, 0, size);
                            }
                            zin.closeEntry();
                        } finally {
                            fout.flush();
                            fout.close();
                        }
                    }
                }
            } catch (IOException e) {
                return false;
            } finally {
                zin.close();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 将保单保存在SD卡下
     *
     * @param mContext 上下文
     * @param _urlStr  保单路径
     */
    public static void saveImageForPolicy(Context mContext, String _urlStr) {
        try {
            //创建保单文件夹
            File appDir = new File(Environment.getExternalStorageDirectory(), "carowner/policy");
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            //保单名称-取当前时间
            String fileName = System.currentTimeMillis() + ".jpg";
            URL url = new URL(_urlStr);// 构造URL
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            Bitmap mBitmap = BitmapFactory.decodeStream(is);
            File file = new File(appDir, fileName);
            FileOutputStream out = new FileOutputStream(file);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            //其次把文件插入到系统图库
            MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            LocalBroadcastManager.getInstance(mContext).sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 过滤文件名中"../"等非法字符，避免出现安全漏洞
     */
    public static String handleFilePath(String path) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
        map.put("e", "e");
        map.put("f", "f");
        map.put("g", "g");
        map.put("h", "h");
        map.put("i", "i");
        map.put("j", "j");
        map.put("k", "k");
        map.put("l", "l");
        map.put("m", "m");
        map.put("n", "n");
        map.put("o", "o");
        map.put("p", "p");
        map.put("q", "q");
        map.put("r", "r");
        map.put("s", "s");
        map.put("t", "t");
        map.put("u", "u");
        map.put("v", "v");
        map.put("w", "w");
        map.put("x", "x");
        map.put("y", "y");
        map.put("z", "z");

        map.put("A", "A");
        map.put("B", "B");
        map.put("C", "C");
        map.put("D", "D");
        map.put("E", "E");
        map.put("F", "F");
        map.put("G", "G");
        map.put("H", "H");
        map.put("I", "I");
        map.put("J", "J");
        map.put("K", "K");
        map.put("L", "L");
        map.put("M", "M");
        map.put("N", "N");
        map.put("O", "O");
        map.put("P", "P");
        map.put("Q", "Q");
        map.put("R", "R");
        map.put("S", "S");
        map.put("T", "T");
        map.put("U", "U");
        map.put("V", "V");
        map.put("W", "W");
        map.put("X", "X");
        map.put("Y", "Y");
        map.put("Z", "Z");

        map.put(":", ":");
        map.put("/", "/");
        map.put("\\", "\\");

        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            String c = path.charAt(i) + "";
            if (map.get(c) != null) {
                temp.append(c);
            }
        }
        path = temp.toString();
        return path;
    }

    public static void goPhotoSelect(Activity activity) {
        String device = Build.MANUFACTURER;
        Intent innerIntent = new Intent(); // "android.intent.action.GET_CONTENT"
        if (device.equals("Xiaomi")) {//小米系统采用19及以上的api，进入的是文件管理，而不是图片库
            innerIntent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            if (Build.VERSION.SDK_INT < 19) {
                innerIntent.setAction(Intent.ACTION_GET_CONTENT);
            } else {
                innerIntent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            }
        }
        innerIntent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(innerIntent, "11");
        activity.startActivityForResult(wrapperIntent, GO_PHOTO);
    }

    public static void goPhotoSelect(Fragment fragment) {
        String device = Build.MANUFACTURER;
        Intent innerIntent = new Intent(); // "android.intent.action.GET_CONTENT"
        if (device.equals("Xiaomi")) {//小米系统采用19及以上的api，进入的是文件管理，而不是图片库
            innerIntent.setAction(Intent.ACTION_GET_CONTENT);
        } else {
            if (Build.VERSION.SDK_INT < 19) {
                innerIntent.setAction(Intent.ACTION_GET_CONTENT);
            } else {
                innerIntent.setAction(Intent.ACTION_OPEN_DOCUMENT);
            }
        }
        innerIntent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(innerIntent, "11");
        fragment.startActivityForResult(wrapperIntent, GO_PHOTO);
    }

    public static String parsePhotoPath(Context context, Intent intent) {
        String path = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        // 获取选中图片的路径
        if (intent == null) {
            return path;
        }
        Cursor cursor = context.getContentResolver().query(intent.getData(), proj, null, null, null);

        String photo_path;
        if (cursor != null && cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            photo_path = cursor.getString(column_index);
            if (photo_path == null) {
                path = Utils.getPath(context, intent.getData());
            }
        }
        Toast.makeText(context, "mPath:" + path, Toast.LENGTH_SHORT).show();
        return path;
    }

    public static void CopyToClipboard(Context context, String text) {
        ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        //clip.getText(); // 粘贴
        clip.setText(text); // 复制
    }
}
