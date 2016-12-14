package com.vigorx.viewdemo.customtab;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.vigorx.viewdemo.R;

public class TabLabel extends View {

	private static final String INSTANCE_STATUS = "instance_status";
	private static final String STATUS_ALPHA = "status_alpha";
	
	@Override
	protected Parcelable onSaveInstanceState() {
		Bundle bundle = new Bundle();
		bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
		bundle.putFloat(STATUS_ALPHA, tabAlpha);
		return bundle;
	}

	@Override
	protected void onRestoreInstanceState(Parcelable state) {
		if (state instanceof Bundle)
		{
			Bundle bundle = (Bundle) state;
			tabAlpha = bundle.getFloat(STATUS_ALPHA);
			super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
			return;
		}
		super.onRestoreInstanceState(state);
	}

	private int color = 0xFF45C01A;
	private Bitmap iconBitmap;
	private String text = "";
	private int textSize = (int) TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

	private Canvas tabCanvas;
	private Bitmap bitmap;
	private Paint paint;
	private float tabAlpha;
	private Rect iconRect;
	private Rect textRect;
	private Paint textPaint;

	public TabLabel(Context context, AttributeSet attrs,
					int defStyleAttr) {
		super(context, attrs, defStyleAttr);

		TypedArray values = context.obtainStyledAttributes(attrs,
				R.styleable.TabLabel);
		int count = values.getIndexCount();
		for (int i = 0; i < count; i++) {
			int attr = values.getIndex(i);
			switch (attr) {
			case R.styleable.TabLabel_tab_icon:
				BitmapDrawable drawable = (BitmapDrawable) values
						.getDrawable(attr);
				iconBitmap = drawable.getBitmap();
				break;
			case R.styleable.TabLabel_tab_color:
				color = values.getColor(attr, 0xFF45C01A);
				break;
			case R.styleable.TabLabel_tab_text:
				text = values.getString(attr);
				break;
			case R.styleable.TabLabel_tab_text_size:
				textSize = (int) values.getDimension(attr, (int) TypedValue
						.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
								getResources().getDisplayMetrics()));

				break;
			}
		}
		
		values.recycle();
		
		textRect = new Rect();
		textPaint = new Paint();
		textPaint.setTextSize(textSize);
		textPaint.setColor(0Xff555555);
		textPaint.getTextBounds(text, 0, text.length(), textRect);
	}

	public TabLabel(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TabLabel(Context context) {
		this(context, null);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
				- getPaddingRight(), getMeasuredHeight() - getPaddingTop()
				- getPaddingBottom() - textRect.height());

		int left = getMeasuredWidth() / 2 - iconWidth / 2;
		int top = getMeasuredHeight() / 2 - (textRect.height() + iconWidth)
				/ 2;
		iconRect = new Rect(left, top, left + iconWidth, top + iconWidth);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawBitmap(iconBitmap, null, iconRect, null);
		int alpha = (int) Math.ceil(255 * tabAlpha);
		setupTargetBitmap(alpha);
		drawSourceText(canvas, alpha);
		drawTargetText(canvas, alpha);
		canvas.drawBitmap(bitmap, 0, 0, null);
	}

	private void drawTargetText(Canvas canvas, int alpha) {
		textPaint.setColor(color);
		textPaint.setAlpha(alpha);
		int x = getMeasuredWidth() / 2 - textRect.width() / 2;
		int y = iconRect.bottom + textRect.height();
		canvas.drawText(text, x, y, textPaint);
	}

	private void drawSourceText(Canvas canvas, int alpha) {
		textPaint.setColor(0xff333333);
		textPaint.setAlpha(255 - alpha);
		int x = getMeasuredWidth() / 2 - textRect.width() / 2;
		int y = iconRect.bottom + textRect.height();
		canvas.drawText(text, x, y, textPaint);
	}

	private void setupTargetBitmap(int alpha) {
		bitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
				Config.ARGB_8888);
		tabCanvas = new Canvas(bitmap);
		paint = new Paint();
		paint.setColor(color);
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setAlpha(alpha);
		tabCanvas.drawRect(iconRect, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
		paint.setAlpha(255);
		tabCanvas.drawBitmap(iconBitmap, null, iconRect, paint);
	}
	
	public void setIconAlpha(float alpha)
	{
		this.tabAlpha = alpha;
		invalidateView();
	}

	private void invalidateView()
	{
		if (Looper.getMainLooper() == Looper.myLooper())
		{
			invalidate();
		} else
		{
			postInvalidate();
		}
	}
	
}
