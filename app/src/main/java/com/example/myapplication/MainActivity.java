package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class MainActivity extends AppCompatActivity {
    public int angle;
    SeekBar seekBar;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar= findViewById(R.id.seekBar);
        textView= findViewById(R.id.textView);

        MyDrawable mydrawing = new MyDrawable();
        seekBar.setProgress(45);
        angle=45;
        textView.setText("Fractal Tree; Angle: "+45);
        ImageView image = findViewById(R.id.imageView);
        image.setImageDrawable(mydrawing);
        image.setContentDescription("Fractal Tree");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                angle=progress;
                textView.setText("Fractal Tree; Angle: "+progress);
                MyDrawable mydrawing = new MyDrawable();
                ImageView image = findViewById(R.id.imageView);
                image.setImageDrawable(mydrawing);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public class MyDrawable extends Drawable {
        private final Paint redPaint;

        public MyDrawable() {
            // Set up color and text size
            redPaint = new Paint();
            redPaint.setARGB(255, 255, 0, 0);
            redPaint.setStrokeWidth(10);
        }

        @Override
        public void draw(Canvas canvas) {
            // Get the drawable's bounds
            int width = getBounds().width();
            int height = getBounds().height();
            //float radius = Math.min(width, height) / 2;

            // Draw a red circle in the center
            int leng=400;
            canvas.translate(width/2, height);
            createStick(canvas, leng);

            //canvas.drawCircle(width/2, height/2, radius, redPaint);
        }

        public void createStick(Canvas canvas, int leng)
        {
            canvas.drawLine(0, 0, 0, -leng, redPaint);
            canvas.translate(0, -leng);

            if(leng>1) {
                canvas.save();
                canvas.rotate(angle);
                createStick(canvas, (int) Math.round(leng * 0.67));
                canvas.restore();
                canvas.save();
                canvas.rotate(-angle);
                createStick(canvas, (int) Math.round(leng * 0.67));
                canvas.restore();
            }
        }



        @Override
        public void setAlpha(int alpha) {
            // This method is required
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            // This method is required
        }

        @Override
        public int getOpacity() {
            // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
            return PixelFormat.OPAQUE;
        }
    }
}