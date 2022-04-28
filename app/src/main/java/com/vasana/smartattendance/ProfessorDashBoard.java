package com.vasana.smartattendance;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.vasana.smartattendance.adapters.MenuRecycleAdapter;
import com.vasana.smartattendance.adapters.PrimaryMenuRecycleAdapter;
import com.vasana.smartattendance.adapters.RecyclerClickListener;
import com.vasana.smartattendance.models.AttendanceResponseItem;
import com.vasana.smartattendance.models.ClassesItem;
import com.vasana.smartattendance.models.Professor;
import com.vasana.smartattendance.models.Professor;
import com.vasana.smartattendance.models.SubjectsItem;
import com.vasana.smartattendance.pojo.MenuOption;
import com.vasana.smartattendance.pojo.MenuOptionPrimary;
import com.vasana.smartattendance.pojo.ProfessorAttendanceFilter;
import com.vasana.smartattendance.pojo.StudentAttendanceFilter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfessorDashBoard extends BaseActivity {

    @BindView(R.id.menu_rv)
    RecyclerView menuRow;

    @BindView(R.id.label)
    TextView label;

    private Professor professor;

    PrimaryMenuRecycleAdapter adapter = new PrimaryMenuRecycleAdapter();

    List<MenuOptionPrimary> menuOptionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_dash_board);
        ButterKnife.bind(this);
        configureMenuOptions();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchProf();
    }

    private void configureMenuOptions() {
        menuOptionList.add(new MenuOptionPrimary("Class list", "View class List.", R.drawable.claa_icon));
//        menuOptionList.add(new MenuOptionPrimary("Professor list", "View Professor list.", R.drawable.list));
        menuOptionList.add(new MenuOptionPrimary("Attendance list", "View attendance List.", R.drawable.att_list));
        menuOptionList.add(new MenuOptionPrimary("Generate barcode", "Create new barcode for Professors.", R.drawable.qr_icon));
        menuOptionList.add(new MenuOptionPrimary("Settings ", "Configure application.", R.drawable.settings));

        menuRow.setAdapter(adapter);
        adapter.setMenuOptionList(menuOptionList);

        adapter.setRecyclerClickListener(position -> {
            switch (position) {
                case 0: {
                    Intent switcher = new Intent(this, ProfessorClassListActivity.class);
                    switcher.putExtra("prof", professor);
                    startActivity(switcher);
                    break;
                }
                case 4: {
                    showCLassList(ProfessorStudentList.class);
                    break;
                }
                case 1: {
                    showCLassList(ProfessorAttendanceList.class);
                    break;
                }
                case 2: {
                    showCLassList(ProfessorBarCodeGeneratorActivity.class);
                    break;
                }
                case 3: {
                    Intent switcher = new Intent(this, ProfessorSettingsActivity.class);
                    switcher.putExtra("from", false);
                    switcher.putExtra("prof", professor);
                    startActivity(switcher);
                    break;
                }
            }
        });

    }


    final private MenuRecycleAdapter classAdapter = new MenuRecycleAdapter();
    final private List<MenuOption> classList = new ArrayList<>();

    private void showCLassList(Class destination) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(this).inflate(R.layout.class_list_dialog, null);
        RecyclerView list = view.findViewById(R.id.dialog_class_list_rv);
        classList.clear();
        for (ClassesItem sub : professor.getClasses())
            classList.add(new MenuOption(sub.getName(), ""));
        list.setAdapter(classAdapter);
        classAdapter.setMenuOptionList(classList);
        classAdapter.setRecyclerClickListener(position -> {
            dialog.dismiss();
           showSubjectDialog(position,destination);
        });
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        dialog.show();
    }

    final private MenuRecycleAdapter subAdapter = new MenuRecycleAdapter();
    final private List<MenuOption> subList = new ArrayList<>();

    void showSubjectDialog(int position, Class destination) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(this).inflate(R.layout.class_list_dialog, null);
        RecyclerView list = view.findViewById(R.id.dialog_class_list_rv);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        titleTextView.setText("Select a subject to continue");

        subList.clear();
        for (SubjectsItem sub : professor.getClasses().get(position).getSubjects())
            subList.add(new MenuOption(sub.getName(), sub.getDescription()));
        list.setAdapter(subAdapter);
        subAdapter.setMenuOptionList(subList);

        subAdapter.setRecyclerClickListener(pos -> {
            dialog.dismiss();
            Intent switcher = new Intent(this, destination);
            switcher.putExtra("classid",professor.getClasses().get(position).getId());
            switcher.putExtra("subid",professor.getClasses().get(position).getSubjects().get(pos).getId());
            startActivity(switcher);
        });

        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        dialog.show();
    }

    private void fetchProf() {
        showLoading();
        api.getProfessor(redis.getUserId()).enqueue(new Callback<Professor>() {
            @Override
            public void onResponse(Call<Professor> call, Response<Professor> response) {
                hideLoading();
                professor = response.body();
                label.setText(professor.getUserid().getUsername());
            }

            @Override
            public void onFailure(Call<Professor> call, Throwable t) {
                shout(t.getMessage());
                t.printStackTrace();
            }
        });
    }


}