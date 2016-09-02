package com.avinnovz.sss.activities;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.avinnovz.sss.R;
import com.avinnovz.sss.base.BaseActivity;
import com.avinnovz.sss.fragments.LoginDialogFragment;
import com.avinnovz.sss.helpers.AppConstants;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements SurfaceHolder.Callback {

    @BindView(R.id.surfaceView)
    SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mp;
    private int video_bg = R.raw.login_bg;
    private static final int REQUEST_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mp = new MediaPlayer();
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final String[] requiredPermission = new String[]{
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.INTERNET
            };
            requestPermissions(requiredPermission, REQUEST_PERMISSIONS);
        }
    }

    @OnClick(R.id.btnCreateAccount)
    public void showRegistrationPage() {
        startActivity(new Intent(this, RegistrationActivity.class));
        animateToLeft(this);
    }

    @OnClick(R.id.btnSignIn)
    public void showLoginDialogFragment() {
        if (isNetworkAvailable()) {
            final LoginDialogFragment loginDialogFragment = LoginDialogFragment.newInstance();
            loginDialogFragment.setOnLoginListener(new LoginDialogFragment.OnLoginListener() {
                @Override
                public void OnLogin(String email, String password) {
                    loginDialogFragment.dismiss();
                    moveToHome();
                }
            });
            loginDialogFragment.show(getFragmentManager(), "login");
        } else {
            showSnackbarError(AppConstants.WARN_CONNECTION);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        final Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + video_bg);

        try {
            mp.setDataSource(this, video);
            mp.setDisplay(surfaceHolder);
            mp.prepare();
            mp.setLooping(true);

            final Display display = getWindowManager().getDefaultDisplay();
            final Point size = new Point();
            display.getSize(size);

            //Get the SurfaceView layout parameters
            final android.view.ViewGroup.LayoutParams lp = surfaceView.getLayoutParams();

            //Set the width of the SurfaceView to the width of the screen
            lp.width = size.x;

            //Set the height of the SurfaceView to match the aspect ratio of the video
            //be sure to cast these as floats otherwise the calculation will likely be 0
            //lp.height = (int) (((float)videoHeight / (float)videoWidth) * (float)size.x);
            lp.height = size.y;

            //Commit the layout parameters
            surfaceView.setLayoutParams(lp);

            //Start video
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    private void moveToHome() {
        startActivity(new Intent(this, MainActivity.class));
        animateToLeft(this);
        finish();
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        LogHelper.log("res", "ON REQUEST PERMISSION");
//        switch (requestCode) {
//            case REQUEST_PERMISSIONS:
//                final boolean writeExternalPermitted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
//                final boolean readContactsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
//
//                LogHelper.log("res", "write external --> " + writeExternalPermitted);
//                LogHelper.log("res", "read contacts --> " + readContactsPermission);
//
//                if (writeExternalPermitted && readContactsPermission) {
//                    LogHelper.log("res", "must continue with initialization");
//                    moveToHome();
//                } else {
//                    /** close the app since the user denied the required permissions */
//                    showConfirmDialog("", "Permission Denied", "You need to grant MySSS " +
//                            "app with full permissions to use the app.", "Close", "", new OnConfirmDialogListener() {
//                        @Override
//                        public void onConfirmed(String action) {
//                            finish();
//                            System.exit(0);
//                        }
//
//                        @Override
//                        public void onCancelled(String action) {
//
//                        }
//                    });
//                    LogHelper.log("res", "permission denied");
//                }
//                break;
//        }
//    }
}
