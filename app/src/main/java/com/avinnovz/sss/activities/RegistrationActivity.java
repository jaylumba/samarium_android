package com.avinnovz.sss.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.avinnovz.sss.R;
import com.avinnovz.sss.base.BaseActivity;
import com.avinnovz.sss.helpers.AppConstants;
import com.avinnovz.sss.textwatchers.BaseTextWatcher;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jayan on 9/2/2016.
 */
public class RegistrationActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarTitle)
    TextView toolbarTitle;
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.etContactNo)
    EditText etContactNo;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;
    @BindView(R.id.spnrGender)
    Spinner spnrGender;
    ;
    @BindView(R.id.tiFirstName)
    TextInputLayout tiFirstName;
    @BindView(R.id.tiLastName)
    TextInputLayout tiLastName;
    @BindView(R.id.tiContactNo)
    TextInputLayout tiContactNo;
    @BindView(R.id.tiAddress)
    TextInputLayout tiAddress;
    @BindView(R.id.tiEmail)
    TextInputLayout tiEmail;
    @BindView(R.id.tiPassword)
    TextInputLayout tiPassword;
    @BindView(R.id.tiConfirmPassword)
    TextInputLayout tiConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        initToolbar();
        initGenderSpinner();
        initTextWatcher();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarTitle.setText(R.string.title_registration);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        animateToRight(this);
    }

    @OnClick(R.id.btnCreateAccount)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCreateAccount:
                createAccount();
                break;
        }
    }

    private void createAccount() {
        if (!isNetworkAvailable()) {
            showToast(AppConstants.WARN_CONNECTION);
        } else if (etFirstName.getText().toString().isEmpty()) {
            setError(tiFirstName, AppConstants.WARN_FIELD_REQUIRED);
        } else if (etLastName.getText().toString().isEmpty()) {
            setError(tiLastName, AppConstants.WARN_FIELD_REQUIRED);
        } else if (etContactNo.getText().toString().isEmpty()) {
            setError(tiContactNo, AppConstants.WARN_FIELD_REQUIRED);
        } else if (etAddress.getText().toString().isEmpty()) {
            setError(tiAddress, AppConstants.WARN_FIELD_REQUIRED);
        } else if (!isValidEmail(etEmail.getText().toString())) {
            setError(tiEmail, AppConstants.WARN_INVALID_EMAIL_FORMAT);
        } else if (etPassword.getText().toString().isEmpty()) {
            setError(tiPassword, AppConstants.WARN_FIELD_REQUIRED);
        } else if (!etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
            setError(tiConfirmPassword, AppConstants.WARN_PASSWORD_NOT_MATCH);
        } else {
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String contactNo = etContactNo.getText().toString();
            String address = etAddress.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String gender = spnrGender.getSelectedItem().toString();
            String userLevel = "Regular User";
            moveToHome();
        }
    }

    private void initTextWatcher(){
        etFirstName.addTextChangedListener(new BaseTextWatcher(etFirstName,tiFirstName));
        etLastName.addTextChangedListener(new BaseTextWatcher(etLastName,tiLastName));
        etContactNo.addTextChangedListener(new BaseTextWatcher(etContactNo,tiContactNo));
        etAddress.addTextChangedListener(new BaseTextWatcher(etAddress,tiAddress));
        etEmail.addTextChangedListener(new BaseTextWatcher(etEmail,tiEmail));
        etPassword.addTextChangedListener(new BaseTextWatcher(etPassword,tiPassword));
        etConfirmPassword.addTextChangedListener(new BaseTextWatcher(etConfirmPassword,tiConfirmPassword));
    }

    private void initGenderSpinner() {
        ArrayList<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        initSpinner(spnrGender, genderList);
    }

    private void initSpinner(final Spinner spinner, final ArrayList<String> items) {
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(RegistrationActivity.this,
                R.layout.row_spinner, items);
        adapter.setDropDownViewResource(R.layout.row_spinner_dropdown);
        spinner.setAdapter(adapter);
    }

    private void moveToHome() {
        startActivity(new Intent(this, MainActivity.class));
        animateToLeft(this);
        finish();
    }
}
