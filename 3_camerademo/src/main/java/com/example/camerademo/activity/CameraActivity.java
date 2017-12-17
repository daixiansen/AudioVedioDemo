package com.example.camerademo.activity;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.camerademo.R;
import com.example.camerademo.camera.CameraHolder;
import com.example.camerademo.camera.preview.CameraSurfaceView;
import com.example.camerademo.util.DisplayUtil;


public class CameraActivity extends AppCompatActivity implements CameraSurfaceView.Callback, CameraHolder.CamOpenOverCallback {
    private static final String TAG = CameraActivity.class.getSimpleName();
    CameraSurfaceView surfaceView = null;
    ImageButton shutterBtn;
    // 预览比例
    public float previewRate = -1f;
    private ImageView iv_camera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        initUI();
        initViewParams();
        shutterBtn.setOnClickListener(new BtnListeners());
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.camera, menu);
        return true;
    }

    private void initUI() {
        surfaceView = (CameraSurfaceView) findViewById(R.id.camera_surfaceview);
        shutterBtn = (ImageButton) findViewById(R.id.btn_shutter);
        iv_camera = (ImageView) findViewById(R.id.iv_camera);
        surfaceView.setOnCallback(this);
        iv_camera.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                CameraHolder.getInstance().cameraChange(CameraActivity.this);
            }
        });
    }

    private void initViewParams() {
        previewRate = DisplayUtil.getScreenRate(this); //默认全屏的比例预览
        Log.e(TAG,"previewRate :" + previewRate);
        LayoutParams params = surfaceView.getLayoutParams();
        Point p = DisplayUtil.getScreenMetrics(this);
        params.width = p.x;
        params.height = p.y;
        surfaceView.setLayoutParams(params);

        //手动设置拍照ImageButton的大小为120dip×120dip,原图片大小是64×64
        LayoutParams p2 = shutterBtn.getLayoutParams();
        p2.width = DisplayUtil.dip2px(this, 80);
        p2.height = DisplayUtil.dip2px(this, 80);
        shutterBtn.setLayoutParams(p2);
    }

    @Override
    public void surfaceCreated(SurfaceHolder var1) {
        CameraHolder.getInstance().doOpenCamera(this);
    }

    @Override
    public void surfaceChanged(SurfaceHolder var1, int var2, int var3, int var4) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder var1) {
        CameraHolder.getInstance().doStopCamera();
    }

    @Override
    public void cameraHasOpened() {
        CameraHolder.getInstance().doStartPreview(surfaceView.getSurfaceHolder(), previewRate);
    }

    private class BtnListeners implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (v.getId()) {
                case R.id.btn_shutter:
                    CameraHolder.getInstance().doTakePicture();
                    break;
                default:
                    break;
            }
        }

    }

}
