package com.vigor.component.boundedbitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.vigor.component.R;

public class BoundedBitmapDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounded_bitmap_demo);

        // 由于RoundedBitmapDrawable类没有直接提供生成圆形图片的方法,
        // 所以生成圆形图片首先需要对原始图片进行裁剪，将图片裁剪成正方形，最后再生成圆形图片
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.liuwen);
        Bitmap dst;
        if (src.getWidth() >= src.getHeight()) {
            dst = Bitmap.createBitmap(src, src.getWidth() / 2 - src.getHeight() / 2,
                    0, src.getHeight(), src.getHeight());
        } else {
            dst = Bitmap.createBitmap(src, 0, src.getHeight() / 2 - src.getWidth() / 2,
                    src.getWidth(), src.getWidth());
        }
        RoundedBitmapDrawable circleDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), dst);
        circleDrawable.getPaint().setAntiAlias(true);
        circleDrawable.setCornerRadius(dst.getWidth());
        ImageView imageCircle = (ImageView) findViewById(R.id.imageView_liu_circle);
        imageCircle.setImageDrawable(circleDrawable);

        Bitmap tom = BitmapFactory.decodeResource(getResources(), R.drawable.tom);
        RoundedBitmapDrawable roundedDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), tom);
        roundedDrawable.getPaint().setAntiAlias(true);
        roundedDrawable.setCornerRadius(50);
        ImageView imageRadius = (ImageView) findViewById(R.id.imageView_liu_radius);
        imageRadius.setImageDrawable(roundedDrawable);
    }
}
