package com.vigor.component.surfaceview;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.vigor.component.R;

import java.io.IOException;

public class SurfaceViewDemoActivity extends AppCompatActivity {

    private static final String TAG = "videocapture";
    private static final String VIDEO_SAVE = "myvideo.mp4";
    private Context mContext;
    private LinearLayout mPreview;
    private CameraPreview mCameraPreview;
    private Camera mCamera;
    private Button mCaptureButton, mSwitchCameraButton;
    private MediaRecorder mMediaRecorder;
    private boolean mIsCameraFront = false;
    private int mScreenOrientation = 90;
    boolean mRecording = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface_view_demo);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mContext = this;
        mPreview = (LinearLayout) findViewById(R.id.camera_preview);

        mCameraPreview = new CameraPreview(mContext, mCamera);
        mPreview.addView(mCameraPreview);

        mCaptureButton = (Button) findViewById(R.id.button_capture);
        mCaptureButton.setOnClickListener(captureListener);

        mSwitchCameraButton = (Button) findViewById(R.id.button_changecamera);
        mSwitchCameraButton.setOnClickListener(switchCameraListener);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mScreenOrientation = 0;
        } else {
            mScreenOrientation = 90;
        }
    }

    View.OnClickListener captureListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mRecording) {
                mMediaRecorder.stop();
                releaseMediaRecorder();
                Toast.makeText(mContext, "Video captured!", Toast.LENGTH_SHORT).show();
                mRecording = false;
            } else {
                if (!prepareMediaRecorder()) {
                    Toast.makeText(mContext, "Fail in prepareMediaRecorder", Toast.LENGTH_SHORT).show();
                    finish();
                }
                runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            mMediaRecorder.start();
                        } catch (final Exception ex) {
                            Log.d(TAG, ex.getMessage());
                        }
                    }
                });
                mRecording = true;
            }
        }

    };

    View.OnClickListener switchCameraListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!mRecording) {
                int camerasNumber = Camera.getNumberOfCameras();
                if (camerasNumber > 1) {
                    // release the old camera instance
                    // switch camera, from the front and the back and vice versa
                    releaseCamera();
                    chooseCamera();
                } else {
                    Toast.makeText(mContext, "Sorry, your phone has only one camera!", Toast.LENGTH_LONG).show();
                }
            }

        }

    };

    protected boolean prepareMediaRecorder() {
        mMediaRecorder = new MediaRecorder();

        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);

        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_720P));

        String sdcardPath = Environment.getExternalStorageDirectory().getPath();
        String filePath = sdcardPath + "/" + VIDEO_SAVE;
        mMediaRecorder.setOutputFile(filePath);
        mMediaRecorder.setMaxDuration(600000); // Set max duration 60 sec.
        mMediaRecorder.setMaxFileSize(50000000); // Set max file size 50M

        try {
            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            releaseMediaRecorder();
            return false;
        } catch (IOException e) {
            releaseMediaRecorder();
            return false;
        }
        return true;
    }

    protected void releaseMediaRecorder() {
        if (mMediaRecorder != null) {
            mMediaRecorder.reset(); // clear recorder configuration
            mMediaRecorder.release(); // release the recorder object
            mMediaRecorder = null;
            mCamera.lock(); // lock camera for later use
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!hasCamera(mContext)) {
            Toast.makeText(mContext, "Sorry, you phone does have a camera", Toast.LENGTH_SHORT).show();
            finish();
        }

        if (mCamera == null) {
            if (findFrontFacingCamera() < 0) {
                Toast.makeText(this, "No front facing camera found.", Toast.LENGTH_SHORT).show();
                mSwitchCameraButton.setVisibility(View.GONE);
            }
            mCamera = Camera.open(findBackFacingCamera());
            mIsCameraFront = false;
            mCamera.setDisplayOrientation(mScreenOrientation);
            mCameraPreview.refreshCamera(mCamera);
        }
    }

    @Override
    protected void onPause() {
        releaseCamera();
        super.onPause();
    }

    private void chooseCamera() {
        if (mIsCameraFront) {
            int cameraId = findBackFacingCamera();
            if (cameraId >= 0) {
                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(mScreenOrientation);
                mCameraPreview.refreshCamera(mCamera);
                mIsCameraFront = false;
            }
        } else {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                mCamera = Camera.open(cameraId);
                mCamera.setDisplayOrientation(mScreenOrientation);
                mCameraPreview.refreshCamera(mCamera);
            }
            mIsCameraFront = true;
        }
    }

    private int findBackFacingCamera() {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    private int findFrontFacingCamera() {
        int cameraId = -1;
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                break;
            }
        }
        return cameraId;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    private boolean hasCamera(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mScreenOrientation = 0;
            mCamera.setDisplayOrientation(mScreenOrientation);
            Toast.makeText(mContext, "横屏", Toast.LENGTH_SHORT).show();
        } else {
            mScreenOrientation = 90;
            mCamera.setDisplayOrientation(mScreenOrientation);
            Toast.makeText(mContext, "竖屏", Toast.LENGTH_SHORT).show();
        }
    }
}
