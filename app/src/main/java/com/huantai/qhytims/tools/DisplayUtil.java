package com.huantai.qhytims.tools;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;


import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import androidx.annotation.Nullable;

public class DisplayUtil {
	 public static int px2dip(Context context, float pxValue) {
	        final float scale = context.getResources().getDisplayMetrics().density;  
	        return (int) (pxValue / scale + 0.5f);  
	    }  
	  
	    public static int dip2px(Context context, float dipValue) {
	        final float scale = context.getResources().getDisplayMetrics().density;  
	        return (int) (dipValue * scale + 0.5f);  
	    }  
	  
	    public static int px2sp(Context context, float pxValue) {
	        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
	        return (int) (pxValue / fontScale + 0.5f);  
	    }  
	  
	    public static int sp2px(Context context, float spValue) {
	        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
	        return (int) (spValue * fontScale + 0.5f);  
	    }

	public static int getScreenWidth(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}

	public static int getScreenHeight(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getHeight();
		return width;
	}


	/**
	 * 通过imageWidth 的高度，自动适应宽度
	 * * @param simpleDraweeView view
	 * * @param imagePath  Uri
	 * * @param imageWidth width
	 */
	public static void setImageWidthByHeight(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageHeight) {
		if(TextUtils.isEmpty(imagePath)){
			return;
		}
		final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
		ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
			@Override
			public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
				if (imageInfo == null) {
					return;
				}
				int height = imageInfo.getHeight();
				int width = imageInfo.getWidth();
				if(width>4000){//图片高度大于4000会有问题
					width = 4000;
				}
				layoutParams.height = imageHeight;
				layoutParams.width = (int) ((float) (imageHeight * width) / (float) height);
				simpleDraweeView.setLayoutParams(layoutParams);
			}

			@Override
			public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
				Log.d("TAG", "Intermediate image received");
			}

			@Override
			public void onFailure(String id, Throwable throwable) {
				throwable.printStackTrace();
			}
		};
		DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(Uri.parse(imagePath)).build();
		simpleDraweeView.setController(controller);
	}

	/**
	 * 通过imageWidth 的高度，自动适应宽度
	 * * @param simpleDraweeView view
	 * * @param imagePath  Uri
	 * * @param imageWidth width
	 */
	public static void setImageHeightByWidth(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageWidth) {
		final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
		ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
			@Override
			public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
				if (imageInfo == null) {
					return;
				}
				int height = imageInfo.getHeight();
				int width = imageInfo.getWidth();
				if(height>4000){//图片高度大于4000会有问题
					height = 4000;
				}
				layoutParams.width = imageWidth;
				layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
				simpleDraweeView.setLayoutParams(layoutParams);
			}

			@Override
			public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
				Log.d("TAG", "Intermediate image received");
			}

			@Override
			public void onFailure(String id, Throwable throwable) {
				throwable.printStackTrace();
			}
		};
		DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(Uri.parse(imagePath)).build();
		simpleDraweeView.setController(controller);
	}
}
