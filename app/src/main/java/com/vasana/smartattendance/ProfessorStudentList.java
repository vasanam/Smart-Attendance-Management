package com.vasana.smartattendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.vasana.smartattendance.adapters.MenuRecycleAdapter;
import com.vasana.smartattendance.adapters.StudentAdapter;
import com.vasana.smartattendance.pojo.MenuOption;
import com.vasana.smartattendance.pojo.StudentPojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfessorStudentList extends AppCompatActivity {

    @BindView(R.id.studentListRv)
    RecyclerView studentListRv;

    final private StudentAdapter adapter = new StudentAdapter();

    final private List<StudentPojo> menuOptionList = new ArrayList<>();

    final private ArrayList<Integer> imageList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_student_list);

        adapter.setContext(this);

        imageList.add(R.drawable.janu);
        imageList.add(R.drawable.teju);
        imageList.add(R.drawable.siva);
        imageList.add(R.drawable.mani);

        ButterKnife.bind(this);
        configureClassList();
    }


    @OnClick(R.id.addStudent)
    void addStudent() {
        showAddStudentDialog();
    }

    private void configureClassList() {
        int i = 0;
        while (i < 100) {
            menuOptionList.add(new StudentPojo("Student " + i, "Some Description about student " + i,getRandomElement(imageList)));
            i++;
        }
        studentListRv.setAdapter(adapter);
//        adapter.setMenuOptionList(menuOptionList);

        adapter.setRecyclerClickListener(position -> {

        });
    }

    public int getRandomElement(List<Integer> list)
    {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    void showAddStudentDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(this).inflate(R.layout.add_class_dialog, null);

        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        dialog.show();
    }

}