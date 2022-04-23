package com.vasana.smartattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vasana.smartattendance.adapters.MenuRecycleAdapter;
import com.vasana.smartattendance.models.Student;
import com.vasana.smartattendance.models.SubjectsItem;
import com.vasana.smartattendance.pojo.MenuOption;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StudentClassList extends AppCompatActivity {


    @BindView(R.id.classListRv)
    RecyclerView classListRv;

    final private MenuRecycleAdapter adapter = new MenuRecycleAdapter();

    final private List<MenuOption> menuOptionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_class_list);
        ButterKnife.bind(this);
        configureClassList();
    }

    private void configureClassList() {
        final Student student = (Student) getIntent().getSerializableExtra("student");
        for (SubjectsItem subject:student.getClassesItem().getSubjects())
            menuOptionList.add(new MenuOption(subject.getName(),subject.getDescription()));
        classListRv.setAdapter(adapter);
        adapter.setMenuOptionList(menuOptionList);

        adapter.setRecyclerClickListener(position -> {
            switch (position) {
                case 0: {

                }
            }
        });
    }
}