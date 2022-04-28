package com.vasana.smartattendance;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.vasana.smartattendance.models.AttendanceResponse;
import com.vasana.smartattendance.models.AttendanceResponseItem;
import com.vasana.smartattendance.models.Student;
import com.vasana.smartattendance.pojo.StudentAttendanceFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentAttendanceActivity extends BaseActivity {

    @BindView(R.id.pie_chart)
    PieChart chart;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);
        ButterKnife.bind(this);
        student = (Student) getIntent().getSerializableExtra("student");
        attendance();
        configureChart();
    }

    private void configureChart() {
        chart.setUsePercentValues(true);
        chart.getDescription().setEnabled(false);
        chart.setExtraOffsets(5, 10, 5, 5);
        chart.setDragDecelerationFrictionCoef(0.95f);
        chart.setCenterText("");
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);
        chart.setTransparentCircleColor(Color.WHITE);
        chart.setTransparentCircleAlpha(110);
        chart.setHoleRadius(58f);
        chart.setTransparentCircleRadius(61f);
        chart.setDrawCenterText(true);
        chart.setRotationAngle(0);
        // enable rotation of the chart by touch
        chart.setRotationEnabled(true);
        chart.setHighlightPerTapEnabled(true);
        chart.animateY(1400, Easing.EaseInOutQuad);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        chart.setEntryLabelColor(Color.WHITE);
        chart.setEntryLabelTextSize(12f);
    }

    private void setData(int present, int absent) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(Float.valueOf(String.valueOf(present)), "Present"));
        entries.add(new PieEntry(Float.valueOf(String.valueOf(absent)), "Absent"));
        PieDataSet dataSet = new PieDataSet(entries, "Student attendance ");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);
        chart.highlightValues(null);
        chart.invalidate();
    }

    private void attendance() {
        showLoading();
        api.fetchAttendance(new StudentAttendanceFilter(student.getId())).enqueue(new Callback<List<AttendanceResponseItem>>() {
            @Override
            public void onResponse(Call<List<AttendanceResponseItem>> call, Response<List<AttendanceResponseItem>> response) {
                hideLoading();
                int pre = 0, ab = 0;
                for (AttendanceResponseItem att : response.body())
                    if (att.getStatus().equals("Absent"))
                        ab++;
                    else pre++;
                setData(pre, ab);
            }

            @Override
            public void onFailure(Call<List<AttendanceResponseItem>> call, Throwable t) {
                hideLoading();
                t.getMessage();
                t.printStackTrace();
            }
        });
    }
}