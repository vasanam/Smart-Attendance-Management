package com.vasana.smartattendance.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vasana.smartattendance.R;
import com.vasana.smartattendance.models.Student;
import com.vasana.smartattendance.models.StudentAbr;
import com.vasana.smartattendance.pojo.MenuOption;
import com.vasana.smartattendance.pojo.StudentPojo;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder> {

    private List<StudentAbr> studentPojoList = new ArrayList<>();
    private RecyclerClickListener recyclerClickListener;
    private Context context;

    public RecyclerClickListener getRecyclerClickListener() {
        return recyclerClickListener;
    }

    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener) {
        this.recyclerClickListener = recyclerClickListener;
    }

    public void setMenuOptionList(List<StudentAbr> studentPojoList) {
        this.studentPojoList = studentPojoList;
        notifyDataSetChanged();
    }

    public StudentAdapter() {
    }

    public StudentAdapter(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView subTitle;
        private final ImageView dp;

        public MyViewHolder(final View view) {
            super(view);
            title = view.findViewById(R.id.label);
            subTitle = view.findViewById(R.id.subLabel);
            dp = view.findViewById(R.id.logo);
        }

    }


    @NonNull
    @Override
    public StudentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(studentPojoList.get(position).getUserid().getUsername());
        holder.subTitle.setText(studentPojoList.get(position).getUserid().getStatus());
        Glide.with(holder.itemView.getContext())
                .load(studentPojoList.get(position).getUserid().getDp())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_account_circle_24).error(R.drawable.ic_baseline_account_circle_24))
                .into(holder.dp);
        holder.itemView.setOnClickListener(view -> {
            if (recyclerClickListener != null)
                recyclerClickListener.onclick(position);
        });
    }

    @Override
    public int getItemCount() {
        return studentPojoList.size();
    }
}