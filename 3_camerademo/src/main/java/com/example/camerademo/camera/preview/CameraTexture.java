package com.example.camerademo.camera.preview;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

public class CameraTexture extends TextureView implements TextureView.SurfaceTextureListener {
    private static final String TAG =CameraTexture.class.getSimpleName();
    private Callback mCallback;
    private long oldTime;

    public CameraTexture(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public CameraTexture(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CameraTexture(Context context) {
        super(context,null);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        if (mCallback != null) {
            mCallback.surfaceCreated(surfaceTexture);
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (mCallback != null) {
            mCallback.surfaceDestroyed(surfaceTexture);
        }
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        long lowTime = System.currentTimeMillis();
        long l = lowTime - oldTime;
        Log.e(TAG,"onSurfaceTextureUpdated -> " + l);
        oldTime = lowTime;
    }

    public interface Callback {
        void surfaceCreated(SurfaceTexture surfaceTexture);

        void surfaceChanged(SurfaceTexture surfaceTexture, int var2, int var3, int var4);

        void surfaceDestroyed(SurfaceTexture surfaceTexture);
    }


    public void setOnCallback(Callback callback){
        this.mCallback = callback;
    }


}
