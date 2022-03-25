package com.vasana.smartattendance;

import android.widget.EditText;

public class Validator {
    public boolean validate(EditText editText) {
        return !editText.getText().toString().isEmpty();
    }

    public boolean validate(EditText editText, String errorMessage) {
        if (editText.getText().toString().isEmpty()) {
            editText.setError(errorMessage);
            return false;
        }
        return true;
    }

    public boolean validate(EditText editText, String errorMessage, int length) {
        if (editText.getText().toString().isEmpty() || editText.getText().toString().length() < length) {
            editText.setError(errorMessage);
            return false;
        }
        return true;
    }
}
