package com.avinnovz.sss.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avinnovz.sss.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 5/26/16.
 */
public class CustomProgressDialogFragment extends DialogFragment {

    @BindView(R.id.tvLoadingMessage) TextView tvLoadingMessage;
    private View view;
    private Dialog mDialog;
    private String message;

    public static CustomProgressDialogFragment newInstance(final String message) {
        final CustomProgressDialogFragment frag = new CustomProgressDialogFragment();
        frag.message = message;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.dialog_custom_progress, null);
        ButterKnife.bind(this, view);
        tvLoadingMessage.setText(message);
        mDialog = new Dialog(getActivity());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(view);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        mDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);
        return mDialog;
    }

    public void updateMessage(final String message) {
        if (tvLoadingMessage != null) {
            tvLoadingMessage.setText(message);
        }
    }
}
