package com.example.bernardojr.whereverigo.infra;

import android.content.Context;
import android.util.AttributeSet;


public class ImagemRetangular extends android.support.v7.widget.AppCompatImageView {
    public ImagemRetangular(Context context) {
        super(context);
    }

    public ImagemRetangular(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImagemRetangular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        setMeasuredDimension(width, (int) Math.round(width*0.7));
    }
}
