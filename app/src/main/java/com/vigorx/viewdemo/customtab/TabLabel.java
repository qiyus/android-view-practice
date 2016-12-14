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

    private int mTabAlpha;
    private int mTabColor = 0xFF45C01A;
    private Bitmap mTabIcon;
    private String mTabText = "";
    private int mTabTextSize;
    private Rect mTabTextRect;
    private Paint mTabTextPaint;
    private Rect mTabIconRect;

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATUS, super.onSaveInstanceState());
        bundle.putInt(STATUS_ALPHA, mTabAlpha);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mTabAlpha = bundle.getInt(STATUS_ALPHA, 0);
            super.onRestoreInstanceState(bundle.getParcelable(INSTANCE_STATUS));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public TabLabel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 读取组件属性
        TypedArray values = context.obtainStyledAttributes(attrs,
                R.styleable.TabLabel);

        int count = values.getIndexCount();
        for (int i = 0; i < count; i++) {
            int attr = values.getIndex(i);
            switch (attr) {
                case R.styleable.TabLabel_tab_icon:
                    BitmapDrawable drawable = (BitmapDrawable) values
                            .getDrawable(attr);
                    if (drawable != null) {
                        mTabIcon = drawable.getBitmap();
                    }
                    break;
                case R.styleable.TabLabel_tab_color:
                    mTabColor = values.getColor(attr, 0xFF45C01A);
                    break;
                case R.styleable.TabLabel_tab_text:
                    mTabText = values.getString(attr);
                    break;
                case R.styleable.TabLabel_tab_text_size:
                    mTabTextSize = (int) values.getDimension(attr, (int) TypedValue
                            .applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
                                    getResources().getDisplayMetrics()));

                    break;
                default:
            }
        }

        values.recycle();

        // 计算文本描画区域。
        mTabTextRect = new Rect();
        Paint paint = new Paint();
        paint.getTextBounds(mTabText, 0, mTabText.length(), mTabTextRect);
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

        // 计算Icon描画区域
        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
                - getPaddingRight(), getMeasuredHeight() - getPaddingTop()
                - getPaddingBottom() - mTabTextRect.height());

        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = getMeasuredHeight() / 2 - (mTabTextRect.height() + iconWidth)
                / 2;
        mTabIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 画Icon
        drawIcon(canvas);

        // 画文本
        drawText(canvas);
    }

    private void drawText(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(mTabTextSize);

        // 画底层文本
        paint.setColor(0xff333333);
        paint.setAlpha(255 - mTabAlpha);
        int x = getMeasuredWidth() / 2 - mTabTextRect.width() / 2;
        int y = mTabIconRect.bottom + mTabTextRect.height();
        canvas.drawText(mTabText, x, y, paint);

        // 画上层文本
        paint.setColor(mTabColor);
        paint.setAlpha(mTabAlpha);
        x = getMeasuredWidth() / 2 - mTabTextRect.width() / 2;
        y = mTabIconRect.bottom + mTabTextRect.height();
        canvas.drawText(mTabText, x, y, paint);
    }

    private void drawIcon(Canvas canvas) {
        // 画底层Icon
        canvas.drawBitmap(mTabIcon, null, mTabIconRect, null);

        // 画上层Icon
        canvas.drawBitmap(createTargetBitmap(), 0, 0, null);
    }

    private Bitmap createTargetBitmap() {
        // 利用指定颜色矩形和Icon不透明部分的交集，设置Icon透明度，来改变不透明部分的颜色。
        Bitmap bitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(),
                Config.ARGB_8888);
        Canvas iconCanvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setColor(mTabColor);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setAlpha(mTabAlpha);
        iconCanvas.drawRect(mTabIconRect, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setAlpha(255);
        iconCanvas.drawBitmap(mTabIcon, null, mTabIconRect, paint);

        return bitmap;
    }

    public void setIconAlpha(float alpha) {
        this.mTabAlpha = (int) Math.ceil(255 * alpha);
        invalidateView();
    }

    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

}
