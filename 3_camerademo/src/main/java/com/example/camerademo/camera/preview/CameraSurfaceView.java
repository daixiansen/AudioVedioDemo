package com.example.camerademo.camera.preview;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG =CameraSurfaceView.class.getSimpleName();
    Context mContext;
    SurfaceHolder mSurfaceHolder;
    private Callback mCallback;


    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mContext = context;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.setFormat(PixelFormat.TRANSPARENT);
        //translucent半透明 transparent透明
        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Log.e(TAG, "surfaceCreated...");
        mCallback.surfaceCreated(holder);
        // surfaceView 创建后才能开启摄像头
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        mCallback.surfaceChanged(holder,format,width,height);
        // TODO Auto-generated method stub
        Log.i(TAG, "surfaceChanged...");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Log.e(TAG, "surfaceDestroyed...");
        mCallback.surfaceDestroyed(holder);
    }

    public SurfaceHolder getSurfaceHolder() {
        return mSurfaceHolder;
    }

    public interface Callback {
        void surfaceCreated(SurfaceHolder var1);

        void surfaceChanged(SurfaceHolder var1, int var2, int var3, int var4);

        void surfaceDestroyed(SurfaceHolder var1);
    }


    public void setOnCallback(Callback callback){
        this.mCallback = callback;
    }


}
