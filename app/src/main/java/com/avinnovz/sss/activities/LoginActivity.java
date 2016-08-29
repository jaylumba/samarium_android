package com.avinnovz.sss.activities;

import android.os.Build;
import android.os.Bundle;

import com.avinnovz.sss.R;
import com.avinnovz.sss.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private static final int REQUEST_PERMISSIONS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            final String[] requiredPermission = new String[]{
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_CONTACTS,
                    android.Manifest.permission.CAMERA
            };
            requestPermissions(requiredPermission, REQUEST_PERMISSIONS);
        }
    }

    @OnClick(R.id.btnSignIn)
    public void signIN(){
        moveToOtherActivity(MainActivity.class);
    }
}
