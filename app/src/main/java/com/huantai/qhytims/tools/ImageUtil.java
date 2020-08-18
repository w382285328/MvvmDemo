package com.huantai.qhytims.tools;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.huantai.qhytims.R;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;


public class ImageUtil {
    /**
     * 图片压缩先压缩图片尺寸 再压缩图片质量
     *
     * f要缩放的图片
     * f1存放缩放的file f1为null时不存图片只看
     */
    public static Bitmap comp(File f) {
        Bitmap bitmap = compressBit(f);
        return compressImage(bitmap, f, f , 1000);//400
    }

    public static Bitmap compressBit(File f){
        Bitmap image = null;
        try {
            BitmapFactory.Options opts = new BitmapFactory.Options();
            if (f.length() > 1024 * 1024) {
                opts.inTempStorage = new byte[100 * 1024];
                opts.inSampleSize = 4;
            }
            opts.inPreferredConfig = Bitmap.Config.RGB_565;
            opts.inPurgeable = true;

            image = BitmapFactory.decodeStream(new FileInputStream(f), null,
                    opts);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // if(url.endsWith(".png")){
        // image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        // }else if(url.endsWith(".jpeg")){
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        // }
        int options = 90;
        //400
        while (baos.toByteArray().length / 1024 > 200) { // 判断如果图片大于1M,进行压缩避免在生成图片时溢出
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩50%，把压缩后的数据存放到baos中
            if(options<=1){
                break;
            }
            double c= (double)options/2;
            options =(int) Math.round(c);

        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 400f;// 这里设置高度为800f
        float ww = 240f;// 这里设置宽度为480f
//        float hh = 800f;// 这里设置高度为800f
//        float ww = 480f;// 这里设置宽度为480f


        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());

        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return bitmap;
    }

    private static Bitmap compressImage(Bitmap image, File f1, File f , int length) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // if(url.endsWith(".png")){
        // image.compress(Bitmap.CompressFormat.PNG, 90, baos);//
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        // }else if(url.endsWith(".jpeg")){
        image.compress(Bitmap.CompressFormat.JPEG, 90, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        // }
        int options = 90;
        while (baos.toByteArray().length / 102 > length/2) { // 1024循环判断如果压缩后图片是否大于length(kb),大于继续压缩
            baos.reset();// 重置baos即清空baos
             image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
//            options -= 10;// 每次都减少10
            if(options<4){
                break;
            }
            double c= ((double)options*3)/4;
            options =(int) Math.round(c);
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片

        int degree = readPictureDegree(f.getPath());
        bitmap = rotateBitmap(bitmap, degree);
        saveBmpToPath(bitmap,f1.getPath());
//        if (f1 != null) {
//            FileOutputStream fos = null;
//            try {
//                fos = new FileOutputStream(f1);
//                byte[] b = baos.toByteArray();
//                BufferedOutputStream bos = new BufferedOutputStream(fos);
//                bos.write(b);
//                bos.close();
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        return bitmap;
    }

    private static int readPictureDegree(String path) {
        // 判断图片方向
        int digree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(path);
        } catch (IOException e) {
            e.printStackTrace();

            exif = null;
        }
        if (exif != null) {
            int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            switch (ori) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    digree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    digree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    digree = 270;
                    break;
                default:
                    digree = 0;
                    break;
            }

        }
        return digree;
//        int degree = 0;
//        try {
//            ExifInterface exifInterface = new ExifInterface(path);
//            int orientation = exifInterface.getAttributeInt(
//                    ExifInterface.TAG_ORIENTATION,
//                    ExifInterface.ORIENTATION_NORMAL);
//            switch (orientation) {
//                case ExifInterface.ORIENTATION_ROTATE_90:
//                    degree = 90;
//                    break;
//                case ExifInterface.ORIENTATION_ROTATE_180:
//                    degree = 180;
//                    break;
//                case ExifInterface.ORIENTATION_ROTATE_270:
//                    degree = 270;
//                    break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return degree;
    }

    private static Bitmap rotateBitmap(Bitmap bitmap, int rotate) {
        Bitmap bitmapRe;
        // 旋转图片
        Matrix m = new Matrix();
        m.postRotate(rotate);
        bitmapRe = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), m, true);
        if (bitmap!=bitmapRe){
            bitmap.recycle();
        }
        return  bitmapRe;
    }
    /**
     * @Description 保存图片到指定路径
     * @param bitmap
     *            要保存的图片
     * @param filePath
     *            目标路径
     * @return 是否成功
     */
    public static boolean saveBmpToPath(final Bitmap bitmap, final String filePath) {
        if (bitmap == null || filePath == null) {
            return false;
        }
        boolean result = false; // 默认结果
        File file = new File(filePath);
        OutputStream outputStream = null; // 文件输出流
        try {
            outputStream = new FileOutputStream(file);
            result = bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
                    outputStream); // 将图片压缩为JPEG格式写到文件输出流，100是最大的质量程度
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close(); // 关闭输出流
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }
    /**
     * 将字符串转换成Bitmap类型
     * @param string
     * @return
     */
    public static Bitmap stringToBitmap(String string){
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    /**
     * 将Bitmap转换成字符串
     */
    public static String bitmapToString(Bitmap bitmap){
        ByteArrayOutputStream bStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,bStream);
        byte[] bytes=bStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }
    /**
     * 创建文件 文件夹路径格式为/__/ 文件路径为/_/_
     */
    @SuppressLint("NewApi")
    public static File getfile(String folder_path, String file_path) {
        String FileUrl = Environment.getExternalStorageDirectory()
                + folder_path;
        File folder = new File(FileUrl);
        // 文件夹是否存在 不存在则创建
        if (!folder.exists()) {
            folder.mkdir();
        }
        File tempFile = new File(Environment.getExternalStorageDirectory()
                .getPath() + file_path);
        tempFile.setExecutable(true);// 设置可执行权限
        tempFile.setReadable(true);// 设置可读权限
        tempFile.setWritable(true);// 设置可写权限
        // 文件不存在则创建
        if (!tempFile.exists()) {
            try {
                tempFile.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return tempFile;
    }
    public static String getCurrentImageTime() {
        String time = getImageTime().format(System.currentTimeMillis());
        return time;
    }
    public static SimpleDateFormat getImageTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf;
    }
    public static void lubanUtil(String photos, String finalPath, Context context, OnCompressListener listen){
        Luban.with(context)
                .load(photos)
                .ignoreBy(100)
                .setTargetDir(finalPath)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(listen).launch();

    }
    public static String getPath(Context context){
        File file = new File(Common.PATH_FOLDER);
        if (!file.exists()) {
            file.mkdirs();
        }
        return Common.PATH_FOLDER;
//        String path = Common.getPhotoFilePath(context);
//        File file1 = new File(path);
//        try {
//            file1.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return  path;
    }


    /**
     *  根据Uri获取文件真实地址
     */
    public static String getRealFilePath(Context context, Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String realPath = null;
        if (scheme == null)
            realPath = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            realPath = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA},
                    null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        realPath = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        if (TextUtils.isEmpty(realPath)) {
            if (uri != null) {
                String uriString = uri.toString();
                int index = uriString.lastIndexOf("/");
                String imageName = uriString.substring(index);
                File storageDir;

                storageDir = Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES);
                File file = new File(storageDir, imageName);
                if (file.exists()) {
                    realPath = file.getAbsolutePath();
                } else {
                    storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                    File file1 = new File(storageDir, imageName);
                    realPath = file1.getAbsolutePath();
                }
            }
        }
        return realPath;
    }

    public static void showImage(Context context, String url, ImageView imageView){
        Glide.with(context).
                load(url)
                .error( R.mipmap.icon_error) //异常时候显示的图片
                .placeholder( R.mipmap.icon_progress) //加载成功前显示的图片
                .into(imageView);
    }
    public static void showImage(Context context, Uri url, ImageView imageView){
        Glide.with(context).
                load(url)
                .error( R.mipmap.icon_error) //异常时候显示的图片
                .placeholder( R.mipmap.icon_progress) //加载成功前显示的图片
                .into(imageView);
    }
    public static void showCircleImage(Context context, Uri url, ImageView imageView){
        Glide.with(context).
                load(url)
                .error( R.mipmap.user_gray) //异常时候显示的图片
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .placeholder( R.mipmap.icon_progress) //加载成功前显示的图片
                .into(imageView);
    }
    public static void showSquareImage(Context context, Uri url, ImageView imageView){
        Glide.with(context).
                load(url)
                .error( R.mipmap.user_gray) //异常时候显示的图片
                .centerCrop()
                .placeholder( R.mipmap.icon_progress) //加载成功前显示的图片
                .into(imageView);
    }

    public static void showCircleImage(Context context, String url, ImageView imageView){
        Glide.with(context).
                load(url)
                .error( R.mipmap.user_gray) //异常时候显示的图片
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .placeholder( R.mipmap.icon_progress) //加载成功前显示的图片
                .into(imageView);
    }
}
