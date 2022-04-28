package com.vasana.smartattendance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.vasana.smartattendance.models.LoginRequest;
import com.vasana.smartattendance.models.LoginResponse;
import com.vasana.smartattendance.uitl.storage.GlobalConstants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.number)
    EditText numberEt;
    @BindView(R.id.password)
    EditText passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (redis.retrieveBoolean(GlobalConstants.RedisConstants.IS_LOGGED_IN, false)) {
            if (redis.retrieveBoolean(GlobalConstants.RedisConstants.IS_STUDENT, false)) {
                Intent switcher = new Intent(LoginActivity.this, StudentDashBoard.class);
                startActivity(switcher);
            } else {
                Intent switcher = new Intent(LoginActivity.this, ProfessorDashBoard.class);
                startActivity(switcher);
            }
            finish();
        }

    }


    @OnClick(R.id.login_btn)
    void login() {
        if (validator.validate(numberEt, "Please enter a valid User name..") && validator.validate(passwordEt, "Please enter a valid password..")) {

            showLoading();
            api.login(new LoginRequest(passwordEt.getText().toString(), numberEt.getText().toString())).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    shout(response.body().getMessage());
                    hideLoading();
                    if (response.body().isStatus()) {
                        redis.setAccessToken(response.body().getToken());
                        redis.setUserId(response.body().getUser().getId());
                        redis.storeItem(GlobalConstants.RedisConstants.IS_LOGGED_IN, true);

                        if (response.body().getUser().getType() == 0) {
                            redis.storeItem(GlobalConstants.RedisConstants.IS_STUDENT, true);
                            redis.setUser(response.body().getStudent());
                            redis.storeItem(GlobalConstants.RedisConstants.STUDENT_ID, response.body().getStudent().getId());
                            Intent switcher = new Intent(LoginActivity.this, StudentDashBoard.class);
                            startActivity(switcher);
                        } else {
                            redis.storeItem(GlobalConstants.RedisConstants.IS_STUDENT, false);
                            redis.setProfessor(response.body().getProfessor());
                            redis.storeItem(GlobalConstants.RedisConstants.PROFESSOR_ID, response.body().getProfessor().getId());
                            Intent switcher = new Intent(LoginActivity.this, ProfessorDashBoard.class);
                            startActivity(switcher);
                        }
                        finish();
                    }

                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    shout(t.getMessage());
                    t.printStackTrace();
                }
            });
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