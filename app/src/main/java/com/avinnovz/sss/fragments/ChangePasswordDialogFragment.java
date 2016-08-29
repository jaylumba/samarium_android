package com.avinnovz.sss.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.avinnovz.sss.R;
import com.avinnovz.sss.base.BaseActivity;
import com.avinnovz.sss.helpers.AppConstants;
import com.avinnovz.sss.textwatchers.BaseTextWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ctmanalo on 7/13/16.
 */
public class ChangePasswordDialogFragment extends DialogFragment {

    private View view;
    private Dialog mDialog;
    private BaseActivity activity;
    private OnChangePasswordListener onChangePasswordListener;

    @BindView(R.id.tiOldPassword) TextInputLayout tiOldPassword;
    @BindView(R.id.tiNewPassword) TextInputLayout tiNewPassword;
    @BindView(R.id.tiConfirmPassword) TextInputLayout tiConfirmPassword;
    @BindView(R.id.etOldPassword) EditText etOldPassword;
    @BindView(R.id.etNewPassword) EditText etNewPassword;
    @BindView(R.id.etConfirmPassword) EditText etConfirmPassword;

    public static ChangePasswordDialogFragment newInstance() {
        final ChangePasswordDialogFragment fragment = new ChangePasswordDialogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_change_password, null);
        ButterKnife.bind(this, view);
        activity = (BaseActivity) getActivity();

        /* Add textwatcher for clearing error message when typing */
        etOldPassword.addTextChangedListener(new BaseTextWatcher(etOldPassword,tiOldPassword));
        etNewPassword.addTextChangedListener(new BaseTextWatcher(etNewPassword,tiNewPassword));
        etConfirmPassword.addTextChangedListener(new BaseTextWatcher(etConfirmPassword,tiConfirmPassword));

        mDialog = new Dialog(getActivity());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        mDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        return mDialog;
    }

    @OnClick({R.id.btnChangePassword, R.id.btnCancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnChangePassword:
                if (!activity.isNetworkAvailable()) {
                    activity.showToast(AppConstants.WARN_CONNECTION);
                } else if (etOldPassword.getText().toString().isEmpty()) {
                    activity.setError(tiOldPassword, AppConstants.WARN_FIELD_REQUIRED);
                } else if (etNewPassword.getText().toString().isEmpty()) {
                    activity.setError(tiNewPassword, AppConstants.WARN_FIELD_REQUIRED);
                } else if (!etNewPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                    activity.setError(tiConfirmPassword, AppConstants.WARN_PASSWORD_NOT_MATCH);
                } else {
                    String oldPassword = etOldPassword.getText().toString();
                    String newPassword = etNewPassword.getText().toString();
                    onChangePasswordListener.onConfirm(oldPassword, newPassword);
                    mDialog.dismiss();
                }
                break;
            case R.id.btnCancel:
                mDialog.dismiss();
                break;
        }
    }

    public interface OnChangePasswordListener {
        void onConfirm(final String oldPassword, final String newPassword);
    }

    public void setOnChangePasswordListener(OnChangePasswordListener onChangePasswordListener) {
        this.onChangePasswordListener = onChangePasswordListener;
    }
}
