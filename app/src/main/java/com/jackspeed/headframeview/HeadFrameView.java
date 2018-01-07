package com.jackspeed.headframeview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.math.BigDecimal;

/**
 * @version V1.0 <>
 * @author  ycj
 * @date  2018-01-04 14:07
 */
public class HeadFrameView extends View {
    private TextPaint textPaint;
    private Paint bitmapPaint;
    private Bitmap iconBitmap;
    private int left;
    private int top;
    private int right;
    private int bottom;
    private String desc;
    private Paint mPaint;
    private String mood;

    public HeadFrameView(Context context) {
        super(context);
        initFramePaint();//画框的画笔
        initBitmapPaint();//画图片画笔
        iniTextPaint();//文字画笔
    }

    public HeadFrameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initFramePaint();//画框的画笔
        initBitmapPaint();//画图片画笔
        iniTextPaint();//文字画笔
    }

    public HeadFrameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFramePaint();
        initBitmapPaint();
        iniTextPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画框
        canvas.drawRect(left, top, right, bottom, mPaint);

        if (desc != null) {
            if (iconBitmap == null) {
                //图片信息为空，年龄性别跟情绪描述在一行信息描述
                canvas.drawText(desc
                        , left - getStringWidth(textPaint, desc) / 4
                        , top - getStringHeight(textPaint) / 2
                        , textPaint);
            } else {
                //年龄性在图片跟情绪描述中间
                canvas.drawText(desc
                        , left - getStringWidth(textPaint, desc) / 4
                        , top - getStringHeight(textPaint) / 2 - getBitmapHeight() / 3
                        , textPaint);
            }
        }

        if (iconBitmap != null && !iconBitmap.isRecycled()) {
            //画图片
            canvas.drawBitmap(iconBitmap
                    , right - getBitmapWidth()
                    , top - getBitmapHeight() - getStringWidth(textPaint, mood)
                    , bitmapPaint);
            iconBitmap.recycle();
            iconBitmap = null;
        }

        if (mood != null) {
            //情绪描述
            canvas.drawText(mood
                    , right - getStringWidth(textPaint, mood)
                    , top - getStringHeight(textPaint) / 2
                    , textPaint);
        }
    }

    private int getBitmapHeight() {
        return iconBitmap == null ? 0 : iconBitmap.getHeight();
    }

    private int getBitmapWidth() {
        return iconBitmap == null ? 0 : iconBitmap.getWidth();
    }


    private int getStringWidth(Paint paint, String str) {
        return str == null ? 0 : (int) paint.measureText(str);
    }

    private int getStringHeight(Paint paint) {
        //ceil() 函数向上舍入为最接近的整数。
        Paint.FontMetrics fr = paint.getFontMetrics();
        return (int) Math.ceil(fr.descent - fr.top) + 2;
    }


    public void setRect(int left, int top, int right, int bottom, String desc, String mood, Bitmap iconBitmap) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.desc = desc;
        this.mood = mood;
        this.iconBitmap = iconBitmap;
        //根据头像框宽高设置字体大小，最小为20px,最大为50
        setTextSize(top, bottom);
        postInvalidate(); // 更新界面
    }

    private void setTextSize(int top, int bottom) {
        BigDecimal a = new BigDecimal(bottom - top);
        BigDecimal b = new BigDecimal(10);
        float tmpSize = a.divide(b).floatValue();
        textPaint.setTextSize(tmpSize < 20 ? 20 : tmpSize > 50 ? 50 : tmpSize);
    }

    private void iniTextPaint() {
        // 设置画笔
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);
        //画笔设置为实心
        textPaint.setStyle(Paint.Style.FILL);
        // 字体大小，根据自己需求自己设定
        textPaint.setTextSize(50);
        // 采用默认的宽度
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        // 采用的颜色
        textPaint.setColor(Color.WHITE);
    }

    private void initBitmapPaint() {
        bitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmapPaint.setFilterBitmap(true);
        bitmapPaint.setDither(true);
        iconBitmap = ((BitmapDrawable) getResources()
                .getDrawable(R.mipmap.ic_launcher_round)).getBitmap();
    }


    private void initFramePaint() {
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        //设置Paint抗锯齿
        mPaint.setAntiAlias(true);
        // 设置Paint的颜色  --白色
        mPaint.setColor(Color.WHITE);
        // 设置paint的风格为“空心”，当然也可以设置为"实心"(Paint.Style.FILL)
        mPaint.setStyle(Paint.Style.STROKE);
        // 设置“空心”的外框的宽度
        mPaint.setStrokeWidth(3);
        //画笔设置成虚线效果
//        PathEffect pathEffect = new DashPathEffect(new float[]{10f, 5f}, 0);
//        mPaint.setPathEffect(pathEffect);
    }
}
