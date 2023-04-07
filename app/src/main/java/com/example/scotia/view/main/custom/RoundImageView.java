package com.example.scotia.view.main.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import com.example.scotia.R;

public class RoundImageView extends androidx.appcompat.widget.AppCompatImageView {

    private int defaultRadius = 0;
    private int radius;
    private int leftTopRadius;
    private int rightTopRadius;
    private int rightBottomRadius;
    private int leftBottomRadius;



    public RoundImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Round_Image_View);
        radius = array.getDimensionPixelOffset(R.styleable.Round_Image_View_radius, defaultRadius);
        leftTopRadius = array.getDimensionPixelOffset(R.styleable.Round_Image_View_left_top_radius, defaultRadius);
        rightTopRadius = array.getDimensionPixelOffset(R.styleable.Round_Image_View_right_top_radius, defaultRadius);
        rightBottomRadius = array.getDimensionPixelOffset(R.styleable.Round_Image_View_right_bottom_radius, defaultRadius);
        leftBottomRadius = array.getDimensionPixelOffset(R.styleable.Round_Image_View_left_bottom_radius, defaultRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int maxLeft = Math.max(leftTopRadius, leftBottomRadius);
        int maxRight = Math.max(rightTopRadius, rightBottomRadius);
        int minWidth = maxLeft + maxRight;
        int maxTop = Math.max(leftTopRadius, rightTopRadius);
        int maxBottom = Math.max(leftBottomRadius, rightBottomRadius);
        int minHeight = maxTop + maxBottom;
        if (width >= minWidth && height > minHeight) {
            Path path = new Path();
            path.moveTo(leftTopRadius, 0);
            path.lineTo(width - rightTopRadius, 0);
            path.quadTo(width, 0, width, rightTopRadius);

            path.lineTo(width, height - rightBottomRadius);
            path.quadTo(width, height, width - rightBottomRadius, height);

            path.lineTo(leftBottomRadius, height);
            path.quadTo(0, height, 0, height - leftBottomRadius);

            path.lineTo(0, leftTopRadius);
            path.quadTo(0, 0, leftTopRadius, 0);

            canvas.clipPath(path);
        }
        super.onDraw(canvas);
    }

}
