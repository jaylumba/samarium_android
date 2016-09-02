package com.avinnovz.sss.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Created by jayan on 9/2/2016.
 */
public class LoginDialogFragment extends DialogFragment {

    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tiEmail)
    TextInputLayout tiEmail;
    @BindView(R.id.tiPassword)
    TextInputLayout tiPassword;
    private View view;
    private Dialog mDialog;
    private BaseActivity activity;
    private OnLoginListener onLoginListener;

    public static LoginDialogFragment newInstance() {
        final LoginDialogFragment fragment = new LoginDialogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
//        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.dialog_fragment_login, null);
        ButterKnife.bind(this, view);
        activity = (BaseActivity) getActivity();

         /* Add textwatcher for clearing error message when typing */
        etEmail.addTextChangedListener(new BaseTextWatcher(etEmail,tiEmail));
        etPassword.addTextChangedListener(new BaseTextWatcher(etPassword,tiPassword));

        etEmail.setText("example@gmail.com");
        etPassword.setText("Password123");

        mDialog = new Dialog(getActivity());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout
                .LayoutParams.WRAP_CONTENT);
        return mDialog;
    }

    @OnClick(R.id.btnLogin)
    public void manageLogin() {
        final String email = etEmail.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        if (email.isEmpty()) {
            activity.setError(tiEmail, AppConstants.WARN_FIELD_REQUIRED);
        } else if (!activity.isValidEmail(email)) {
            activity.setError(tiEmail, AppConstants.WARN_INVALID_EMAIL_FORMAT);
        } else if (password.isEmpty()) {
            activity.setError(tiPassword, AppConstants.WARN_FIELD_REQUIRED);
        } else {
            if (onLoginListener != null) {
                onLoginListener.OnLogin(email, password);
            }
        }
    }

    public interface OnLoginListener {
        void OnLogin(final String email, final String password);
    }

    public void setOnLoginListener(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
    }
}
