package com.vigor.component.canvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vigor.component.R;

public class CanvasDemoActivity extends AppCompatActivity {

    private CanvasView mCanvas;
    private Button mClear;
    private Button mMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_demo);
        mCanvas = (CanvasView) findViewById(R.id.canvas_path);
        mClear = (Button) findViewById(R.id.button_clear);
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCanvas.clearCanvas();
            }
        });

        mMove = (Button) findViewById(R.id.button_move);
        mMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCanvas.movePath(20, 20);
            }
        });
    }
}
