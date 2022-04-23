package com.vasana.smartattendance;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.vasana.smartattendance.models.Professor;
import com.vasana.smartattendance.models.Student;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfessorSettingsActivity extends BaseActivity {

    @BindView((R.id.name))
    TextView textView;

    private Professor professor;
    boolean isProfessor = false;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_settings);
        ButterKnife.bind(this);
        if (getIntent().getBooleanExtra("from", false)) {
            student = (Student) getIntent().getSerializableExtra("student");
            textView.setText(student.getUserid().getUsername());
        } else {
            professor = (Professor) getIntent().getSerializableExtra("prof");
            textView.setText(professor.getUserid().getUsername());
        }
    }

    @OnClick(R.id.logout)
    void logout() {
        redis.deleteAll();
        final Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}