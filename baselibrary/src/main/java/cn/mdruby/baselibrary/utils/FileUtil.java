package cn.mdruby.baselibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Properties;

/**
 * Created by Went_Gone on 2018/3/16.
 */

public class FileUtil {
    public static String getMIMEType(Context context,File file){
        String property = "";
        String fName = file.getName();
      /* 取得扩展名 */
        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
        Properties pro = new Properties();
        InputStream is = null;
        try {
            is = context.getAssets().open("mime.properties");
            pro.load(is);
            property = pro.getProperty(end, "*/*");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }

    public static Intent getFileIntent(Context context,File file,String providerName) {
        String type = getMIMEType(context,file);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri photoURI = getUri(context,intent,file,providerName);
        intent.setDataAndType(photoURI, type);
        return intent;
    }

    private static Uri getUri(Context context,Intent intent, File file,String providerName) {
        Uri uri = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //判断版本是否在7.0以上
            uri = FileProvider.getUriForFile(context,
                            context.getPackageName() + "."+providerName,
                            file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    /**
     * 格式化单位
     * @param file
     * @return
     */
    public static String getFormatSize(File file){
        String size = "0M";
        if (file!=null &&file.exists()){
            try {
                FileInputStream fis = new FileInputStream(file);
                long sizeLong = fis.available();
                String formatSize = getFormatSize(sizeLong);
                size = getFormatSize(sizeLong);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "B";
            return "0.0M";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
//            return  "0.0M";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }
}
