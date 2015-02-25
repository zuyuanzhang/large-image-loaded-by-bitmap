package com.example.customview;

import java.util.Map;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.CollapsibleActionView;
import android.view.DragEvent;
import android.view.View;

public class MyView extends View {

	String text = "";
	int textcolor = -1;
	int textsize = -1;

	private Paint mPaint;
	private Rect mRect;
	Bitmap mBitmap = null;

	public MyView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public MyView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		mBitmap = Bitmap.createBitmap(400, 400, Config.ARGB_8888);
		// mBitmap = BitmapFactory.decodeResource(getResources(),
		// R.drawable.apple);
		int width = mBitmap.getWidth();
		int height = mBitmap.getHeight();
		System.out
				.println("the width is " + width + " the height is " + height);
		mBitmap = decodeSampledBitmapFromResource(getResources(),R.drawable.apple,500,500);
	}

	public MyView(Context context, AttributeSet attrs, int defstyle) {
		super(context, attrs, defstyle);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint mPaint = new Paint();
		mPaint.setColor(Color.RED);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Style.STROKE);
		// canvas.setBitmap(mBitmap);
		canvas.drawBitmap(mBitmap, new Matrix(), mPaint);
	}

	/**
	 * 获取压缩后的图片
	 * 
	 * @param res
	 * @param resId
	 * @param reqWidth
	 *            所需图片压缩尺寸最小宽度
	 * @param reqHeight
	 *            所需图片压缩尺寸最小高度
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// 首先不加载图片,仅获取图片尺寸
		final BitmapFactory.Options options = new BitmapFactory.Options();
		// 当inJustDecodeBounds设为true时,不会加载图片仅获取图片尺寸信息
		options.inJustDecodeBounds = true;
		// 此时仅会将图片信息会保存至options对象内,decode方法不会返回bitmap对象
		BitmapFactory.decodeResource(res, resId, options);

		// 计算压缩比例,如inSampleSize=4时,图片会压缩成原图的1/4
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// 当inJustDecodeBounds设为false时,BitmapFactory.decode...就会返回图片对象了
		options.inJustDecodeBounds = false;
		// 利用计算的比例值获取压缩后的图片对象
		return BitmapFactory.decodeResource(res, resId, options);
	}
	
	
	/**
	* 计算压缩比例值
	* @param options       解析图片的配置信息
	* @param reqWidth            所需图片压缩尺寸最小宽度
	* @param reqHeight           所需图片压缩尺寸最小高度
	* @return
	*/
	public static int calculateInSampleSize(BitmapFactory.Options options,
	             int reqWidth, int reqHeight) {
	       // 保存图片原宽高值
	       final int height = options. outHeight;
	       final int width = options. outWidth;
	       // 初始化压缩比例为1
	       int inSampleSize = 1;

	       // 当图片宽高值任何一个大于所需压缩图片宽高值时,进入循环计算系统
	       if (height > reqHeight || width > reqWidth) {

	             final int halfHeight = height / 2;
	             final int halfWidth = width / 2;

	             // 压缩比例值每次循环两倍增加,
	             // 直到原图宽高值的一半除以压缩值后都~大于所需宽高值为止
	             while ((halfHeight / inSampleSize) >= reqHeight
	                        && (halfWidth / inSampleSize) >= reqWidth) {
	                  inSampleSize *= 2;
	            }
	      }

	       return inSampleSize;
	}

}
