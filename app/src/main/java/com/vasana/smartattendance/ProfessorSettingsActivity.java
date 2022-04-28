package com.vasana.smartattendance;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.vasana.smartattendance.models.Professor;
import com.vasana.smartattendance.models.Student;
import com.vasana.smartattendance.models.SubjectsItem;
import com.vasana.smartattendance.models.UpdateUserDetails;
import com.vasana.smartattendance.models.User;
import com.vasana.smartattendance.pojo.MenuOption;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import im.delight.android.webview.AdvancedWebView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class ProfessorSettingsActivity extends BaseActivity {

    @BindView((R.id.name))
    TextView textView;

    @BindView((R.id.updateNumber))
    TextView updateNumber;

    @BindView((R.id.updateStatus))
    TextView updateStatus;

    @BindView((R.id.statusTv))
    TextView statusTv;

    @BindView(R.id.dp)
    CircleImageView dp;

    private Professor professor;
    boolean isProfessor = false;
    private Student student;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_settings);
        ButterKnife.bind(this);
        if (getIntent().getBooleanExtra("from", false)) {
            student = (Student) getIntent().getSerializableExtra("student");
            user = student.getUserid();
            textView.setText(student.getUserid().getUsername());
            statusTv.setText((student.getUserid().getStatus() == null) ? "--" : student.getUserid().getStatus());
            updateStatus.setText(student.getUserid().getStatus() == null ? "Update status" : student.getUserid().getStatus());
            updateNumber.setText(student.getUserid().getMobile() == null ? "update number" : student.getUserid().getMobile());

        } else {
            professor = (Professor) getIntent().getSerializableExtra("prof");
            textView.setText(professor.getUserid().getUsername());
            user = professor.getUserid();
            statusTv.setText((professor.getUserid().getStatus() == null) ? "--" : professor.getUserid().getStatus());
            updateStatus.setText(professor.getUserid().getStatus() == null ? "Update status" : professor.getUserid().getStatus());
            updateNumber.setText(professor.getUserid().getMobile() == null ? "update number" : professor.getUserid().getMobile());
        }

        updateNumber.setOnClickListener(view -> updateMobileNumber());
        updateStatus.setOnClickListener(view -> updateMobileNumber());

        if (user.getDp() == null)
            Glide.with(this).load(R.drawable.ic_baseline_account_circle_24).into(dp);
        else Glide.with(this).load(user.getDp()).into(dp);


        Timber.e(user.toString());

    }

    @OnClick(R.id.logout)
    void logout() {
        redis.deleteAll();
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    void updateMobileNumber() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(this).inflate(R.layout.update_user, null);
        EditText number = view.findViewById(R.id.numner);
        Button pri = view.findViewById(R.id.primaryButton);
        Button sec = view.findViewById(R.id.secondaryButton);
        EditText status = view.findViewById(R.id.status);
        status.setText(user.getStatus() == null ? user.getStatus() : "");
        number.setText(user.getMobile() == null ? user.getMobile() : "");

        pri.setOnClickListener(view1 -> {
            if (validator.validate(status, "Status cannot be empty") && validator.validate(number, "Number cannot be empty", 10)) {
                dialog.dismiss();
                updateCall(new UpdateUserDetails(number.getText().toString(), status.getText().toString()));
            }
        });
        sec.setOnClickListener(v -> dialog.dismiss());
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        dialog.show();
    }

    void updateCall(UpdateUserDetails updateUserDetails) {
        showLoading();
        api.updateUser(user.getId(), updateUserDetails).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                hideLoading();
                user = response.body();
                statusTv.setText(updateUserDetails.getStatus());
                updateStatus.setText(updateUserDetails.getStatus());
                updateNumber.setText(updateUserDetails.getMobile());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                hideLoading();
                shout(t.getMessage());
            }
        });
    }

    @OnClick(R.id.termsAndConditions)
    void tc() {
        AdvancedWebView.Browsers.openUrl(this, "http://www.example.org/");
    }

    @OnClick(R.id.privacyPolicy)
    void pp() {
        AdvancedWebView.Browsers.openUrl(this, "http://www.example.org/");
    }

    @OnClick(R.id.customerCare)
    void cc() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + "+18166898784"));
        startActivity(callIntent);
    }
}