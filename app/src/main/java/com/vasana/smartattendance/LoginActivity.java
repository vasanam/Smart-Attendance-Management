package com.vasana.smartattendance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.number)
    EditText numberEt;
    @BindView(R.id.password)
    EditText passwordEt;
    private final Validator validator = new Validator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_btn)
    void login() {
        if (validator.validate(numberEt, "Please enter a valid User name..") && validator.validate(passwordEt, "Please enter a valid password..")) {
            Intent switcher = new Intent(this, ProfessorDashBoard.class);
            startActivity(switcher);
        }
    }

    @OnClick(R.id.student_btn)
    void studentLogin() {
        if (validator.validate(numberEt, "Please enter a valid User name..") && validator.validate(passwordEt, "Please enter a valid password..")) {
            Intent switcher = new Intent(this, StudentDashBoard.class);
            startActivity(switcher);
        }
    }
}