package com.vasana.smartattendance;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.vasana.smartattendance.adapters.PrimaryMenuRecycleAdapter;
import com.vasana.smartattendance.pojo.MenuOptionPrimary;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentDashBoard extends BaseActivity {


    @BindView(R.id.student_menu_rv)
    RecyclerView menuRv;

    PrimaryMenuRecycleAdapter adapter = new PrimaryMenuRecycleAdapter();

    List<MenuOptionPrimary> menuOptionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash_board);
        ButterKnife.bind(this);
        configureMenuOptions();
    }

    private void configureMenuOptions() {
        menuOptionList.add(new MenuOptionPrimary("Class list", "View class List.", R.drawable.claa_icon));
        menuOptionList.add(new MenuOptionPrimary("Attendance", "View attendance List.", R.drawable.att_list));
        menuOptionList.add(new MenuOptionPrimary("Scan barcode", "Create new barcode for students.", R.drawable.qr_icon));
        menuOptionList.add(new MenuOptionPrimary("Settings ", "Configure application.", R.drawable.settings));

        menuRv.setAdapter(adapter);
        adapter.setMenuOptionList(menuOptionList);

        adapter.setRecyclerClickListener(position -> {
            switch (position) {
                case 0: {
                    navigate(StudentClassList.class);
                    break;
                }
                case 1: {
                    navigate(StudentAttendanceActivity.class);
                    break;
                }
                case 2: {
                    navigate(StudentScanBarcodeActivity.class);
                    break;
                }
                case 3: {
                   navigate(ProfessorSettingsActivity.class);
                }
            }
        });
    }


    public void navigate(Class destination) {
        Intent switcher = new Intent(this, destination);
        startActivity(switcher);
    }

}