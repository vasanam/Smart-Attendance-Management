package com.vasana.smartattendance.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vasana.smartattendance.R;
import com.vasana.smartattendance.pojo.MenuOption;

import java.util.ArrayList;
import java.util.List;

public class MenuRecycleAdapter extends RecyclerView.Adapter<MenuRecycleAdapter.MyViewHolder> {

    private List<MenuOption> menuOptionList = new ArrayList<>();
    private RecyclerClickListener recyclerClickListener;

    public RecyclerClickListener getRecyclerClickListener() {
        return recyclerClickListener;
    }

    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener) {
        this.recyclerClickListener = recyclerClickListener;
    }

    public List<MenuOption> getMenuOptionList() {
        return menuOptionList;
    }

    public void setMenuOptionList(List<MenuOption> menuOptionList) {
        this.menuOptionList = menuOptionList;
        notifyDataSetChanged();
    }

    public MenuRecycleAdapter() {
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView subTitle;

        public MyViewHolder(final View view) {
            super(view);
            title = view.findViewById(R.id.label);
            subTitle = view.findViewById(R.id.subLabel);
        }

    }


    @NonNull
    @Override
    public MenuRecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuRecycleAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(menuOptionList.get(position).getTitle());
        holder.subTitle.setText(String.valueOf(menuOptionList.get(position).getSubTitle()));
        holder.itemView.setOnClickListener(view -> {
            if (recyclerClickListener!=null)
            recyclerClickListener.onclick(position);
        });
    }

    @Override
    public int getItemCount() {
        return menuOptionList.size();
    }
}