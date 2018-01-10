package com.vigor.component.boundedbitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.vigor.component.R;

public class BoundedBitmapDemoActivity extends AppCompatActivity {

    // 图片的圆角半径，圆角大小和图片像素密度一致。
    private final int CORNER_RADIUS = 200;
    private final int STROKE_WIDTH = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounded_bitmap_demo);
        setCircleBitmap();
        setRoundedBitmap();
        setCircleBitmapWithLine();
        
    }

    /**
     * 设置圆形图片
     */
    private void setCircleBitmap() {
        Bitmap dst = rectangleToSquare(R.drawable.liuwen);
        RoundedBitmapDrawable circleDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), dst);
        circleDrawable.getPaint().setAntiAlias(true);
        circleDrawable.setCircular(true);
        ImageView imageCircle = (ImageView) findViewById(R.id.imageView_liu_circle);
        imageCircle.setImageDrawable(circleDrawable);
    }

    /**
     * 设置带边框的圆形图片
     */
    private void setCircleBitmapWithLine() {
        Bitmap dst = rectangleToSquare(R.drawable.liuwen);
        drawLine(dst);
        RoundedBitmapDrawable circleDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), dst);
        circleDrawable.getPaint().setAntiAlias(true);
        circleDrawable.setCircular(true);
        ImageView imageCircle = (ImageView) findViewById(R.id.imageView_line);
        imageCircle.setImageDrawable(circleDrawable);
    }

    /**
     * 设置圆角图片
     */
    private void setRoundedBitmap() {
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.liuwen);
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), src);
        roundedDrawable.getPaint().setAntiAlias(true);
        roundedDrawable.setCornerRadius(CORNER_RADIUS);
        ImageView imageRadius = (ImageView) findViewById(R.id.imageView_liu_radius);
        imageRadius.setImageDrawable(roundedDrawable);
    }

    /**
     * 为传入的圆形图片描画边框。
     * @param circleBitmap 传入的图片。
     */
    private void drawLine(Bitmap circleBitmap) {
        Canvas canvas = new Canvas(circleBitmap);
        canvas.drawBitmap(circleBitmap, 0, 0, null);
        Paint borderPaint = new Paint();
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(STROKE_WIDTH);
        borderPaint.setColor(Color.BLUE);
        borderPaint.setAntiAlias(true);
        canvas.drawCircle(canvas.getWidth()/2, canvas.getWidth()/2,
                canvas.getWidth()/2, borderPaint);
    }

    /**
     * 由于RoundedBitmapDrawable类直接生成圆形图片,可能会导致图片单方向压缩或拉伸。</br>
     * 所以生成圆形图片首先需要对原始图片进行裁剪，将图片裁剪成正方形，最后再生成圆形图片
     * @param resId 资源ID
     * @return 对应的图片。
     */
    private Bitmap rectangleToSquare(int resId) {
        Bitmap src = BitmapFactory.decodeResource(getResources(), resId);
        Bitmap dst;
        if (src.getWidth() >= src.getHeight()) {
            dst = Bitmap.createBitmap(src, src.getWidth() / 2 - src.getHeight() / 2,
                    0, src.getHeight(), src.getHeight());
        } else {
            dst = Bitmap.createBitmap(src, 0, src.getHeight() / 2 - src.getWidth() / 2,
                    src.getWidth(), src.getWidth());
        }
        return dst;
    }
}
