package com.avinnovz.sss.textwatchers;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by jayan on 8/29/2016.
 */
public class BaseTextWatcher implements TextWatcher {
    protected EditText editText;
    protected TextInputLayout textInputLayout;

    public BaseTextWatcher(EditText editText, TextInputLayout textInputLayout){
        this.editText = editText;
        this.textInputLayout = textInputLayout;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(editText.getTag() == null){
            editText.setTag("dirty");
        }
        textInputLayout.setError(null);
//        textInputLayout.setErrorEnabled(false);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
