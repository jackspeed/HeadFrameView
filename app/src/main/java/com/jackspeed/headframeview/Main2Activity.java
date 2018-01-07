package com.jackspeed.headframeview;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final RelativeLayout headContainer = findViewById(R.id.layout_head);
        final HeadFrameView headView = new HeadFrameView(this);
        headContainer.removeAllViews();
        headContainer.addView(headView);
        int left = 100;
        int top = 100;
        int width = 100;
        headView.setRect(left, top, left + width, top + width, "46，男士", "开心", getBitmap(R.mipmap.happy));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                headView.setRect(100, 250, 450, 550, "18，男士", "愤怒", getBitmap(R.mipmap.happy));
                headContainer.removeAllViews();
                headContainer.addView(headView);
            }
        }, 2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                headView.setRect(300, 300, 600, 600, "32，男士", "面无表情", getBitmap(R.mipmap.no_express));
                headContainer.removeAllViews();
                headContainer.addView(headView);
            }
        }, 3000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                headView.setRect(200, 200, 500, 500, "32，男士", "面无表情", getBitmap(R.mipmap.happy));
                headContainer.removeAllViews();
                headContainer.addView(headView);
            }
        }, 4000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                headView.setRect(200, 200, 400, 400, "32，男士", "惊讶", getBitmap(R.mipmap.happy));
                headContainer.removeAllViews();
                headContainer.addView(headView);
            }
        }, 5000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                headView.setRect(150, 250, 500, 400, "99，女士", "惊讶", getBitmap(R.mipmap.fennu));
                headContainer.removeAllViews();
                headContainer.addView(headView);
            }
        }, 7000);
    }

    private Bitmap getBitmap(int resId) {
        return BitmapFactory.decodeResource(getResources(), resId);
    }

}
