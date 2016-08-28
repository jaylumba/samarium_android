package com.avinnovz.sss.activities;

import android.os.Bundle;

import com.avinnovz.sss.R;
import com.avinnovz.sss.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSignIn)
    public void signIN(){
        moveToOtherActivity(MainActivity.class);
    }
}
