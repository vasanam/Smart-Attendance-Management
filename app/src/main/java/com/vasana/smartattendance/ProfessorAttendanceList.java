package com.vasana.smartattendance;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vasana.smartattendance.adapters.StudentAdapter;
import com.vasana.smartattendance.models.AttendanceResponseItem;
import com.vasana.smartattendance.models.Student;
import com.vasana.smartattendance.models.StudentAbr;
import com.vasana.smartattendance.pojo.ProfessorAttendanceFilter;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.android.segmented.SegmentedGroup;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfessorAttendanceList extends BaseActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.attendanceRv)
    RecyclerView attendanceRv;

    @BindView(R.id.att_selector)
    SegmentedGroup aatt_selector;

    @BindView(R.id.changeDate)
    FloatingActionButton changeDate;

    final private StudentAdapter adapter = new StudentAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_attendance_list);
        ButterKnife.bind(this);
        attendanceRv.setAdapter(adapter);
        aatt_selector.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (radioGroup.getCheckedRadioButtonId() == R.id.present_btn) {
                    adapter.setMenuOptionList(presetList);
                } else
                    adapter.setMenuOptionList(abList);
            }
        });

        changeDate.setOnClickListener(view -> {
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    ProfessorAttendanceList.this,
                    now.get(Calendar.YEAR), // Initial year selection
                    now.get(Calendar.MONTH), // Initial month selection
                    now.get(Calendar.DAY_OF_MONTH) // Inital day selection
            );
            dpd.show(getSupportFragmentManager(), "Datepickerdialog");
        });

        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy");
        Date date = new Date();
        attendance(formatter.format(date));
    }


    @OnClick(R.id.changeDate)
    void addStudent() {
//        showAddStudentDialog();
    }


    List<StudentAbr> presetList = new ArrayList<>();
    List<StudentAbr> abList = new ArrayList<>();

    private void attendance(String date) {
        showLoading();
        api.fetchAttendance(new ProfessorAttendanceFilter(date, getIntent().getStringExtra("classid"), getIntent().getStringExtra("subid"))).enqueue(new Callback<List<AttendanceResponseItem>>() {
            @Override
            public void onResponse(Call<List<AttendanceResponseItem>> call, Response<List<AttendanceResponseItem>> response) {
                hideLoading();
                abList.clear();
                presetList.clear();
                if (response.body().size()==0)
                    shout("No attendance recorded for the selected date.");
                for (AttendanceResponseItem att : response.body())
                    if (att.getStatus().equals("Absent"))
                        abList.add(att.getStudent());
                    else
                        presetList.add(att.getStudent());

                if (aatt_selector.getCheckedRadioButtonId() == R.id.present_btn) {
                    adapter.setMenuOptionList(presetList);
                } else
                    adapter.setMenuOptionList(abList);
            }

            @Override
            public void onFailure(Call<List<AttendanceResponseItem>> call, Throwable t) {
                hideLoading();
                t.getMessage();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        attendance(dayOfMonth + "-" + monthOfYear + "-" + year);
    }
}