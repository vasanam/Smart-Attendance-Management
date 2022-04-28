package com.vasana.smartattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.vasana.smartattendance.uitl.Api;
import com.vasana.smartattendance.uitl.RetrofitInstance;
import com.vasana.smartattendance.uitl.storage.RedisProvider;
import com.vasana.smartattendance.uitl.storage.base.KRedis;

public class BaseActivity extends AppCompatActivity {
    public Api api;
    public KRedis redis;

    private KProgressHUD progressHUD;
    Validator validator = new Validator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        api = RetrofitInstance.getRetrofitInstance().create(Api.class);

        redis = RedisProvider.getRedis(this);

        progressHUD = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(3)
                .setDimAmount(0.1f);
    }

    public void shout(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showLoading() {
        if (!progressHUD.isShowing())
            progressHUD.show();
//        progressHUD.setLabel(" \n message \n");
    }

    public void hideLoading() {
        try {
            if (progressHUD.isShowing())
                progressHUD.dismiss();
        } catch (Exception exception) {
            exception.getStackTrace();
        }
    }

}
