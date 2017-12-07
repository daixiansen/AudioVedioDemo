package com.example.a2_audiodemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by daixiansen on 2017/8/23.
 * SurfaceView 使用模板代码
 */

public class CustomSurfaceView1 extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private static final String TAG = CustomSurfaceView1.class.getSimpleName();
    private SurfaceHolder mSurfaceHolder;
    private boolean mIsDrawing; // 子线程标志位
    private Canvas mCanvas;
    private Paint mPaint;

    public CustomSurfaceView1(Context context) {
        super(context, null);
        initView();
    }

    public CustomSurfaceView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomSurfaceView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mSurfaceHolder = getHolder();
        //注册回调方法
        mSurfaceHolder.addCallback(this);
        //设置一些参数方便后面绘图
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
        mPaint = new Paint();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 创建
        mIsDrawing = true;
        //开启子线程
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // 改变
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 销毁
        mIsDrawing = false;
    }


    @Override
    public void run() {
        while (mIsDrawing) {
            drawSomething();
        }
    }

    private void drawSomething() {

        try {
            //获得canvas对象
            mCanvas = mSurfaceHolder.lockCanvas();
            if (mCanvas != null) {
                //绘制背景
                mCanvas.drawColor(Color.WHITE);
                //绘图
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.a);
                mCanvas.drawBitmap(bitmap, 0, 0, mPaint);
                Log.e(TAG, "drawSomething...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mCanvas != null) {
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
