package com.vasana.smartattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.vasana.smartattendance.adapters.MenuRecycleAdapter;
import com.vasana.smartattendance.models.ClassesItem;
import com.vasana.smartattendance.models.Professor;
import com.vasana.smartattendance.models.SubjectsItem;
import com.vasana.smartattendance.pojo.MenuOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfessorClassListActivity extends AppCompatActivity {

    @BindView(R.id.classListRv)
    RecyclerView classListRv;
    final private MenuRecycleAdapter adapter = new MenuRecycleAdapter();
    final private List<MenuOption> menuOptionList = new ArrayList<>();
    private Professor professor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_class_list);
        ButterKnife.bind(this);
        professor = (Professor) getIntent().getSerializableExtra("prof");
        configureClassList();
    }

    @OnClick(R.id.addClass)
    void addClass() {
//        showAddClassDialog(position);
    }

    private void configureClassList() {
        for (ClassesItem classesItem : professor.getClasses())
            menuOptionList.add(new MenuOption(classesItem.getName(), ""));
        classListRv.setAdapter(adapter);
        adapter.setMenuOptionList(menuOptionList);
        adapter.setRecyclerClickListener(position -> showAddClassDialog(position));
    }

    final private MenuRecycleAdapter classAdapter = new MenuRecycleAdapter();
    final private List<MenuOption> classList = new ArrayList<>();

    void showAddClassDialog(int position) {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(this).inflate(R.layout.class_list_dialog, null);

        RecyclerView list = view.findViewById(R.id.dialog_class_list_rv);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        titleTextView.setText("Subject in selected class.");
        classList.clear();
        for (SubjectsItem sub:professor.getClasses().get(position).getSubjects())
        classList.add(new MenuOption(sub.getName(),sub.getDescription()));
        list.setAdapter(classAdapter);
        classAdapter.setMenuOptionList(classList);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        dialog.show();
    }


}