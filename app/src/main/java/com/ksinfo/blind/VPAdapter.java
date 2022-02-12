package com.ksinfo.blind;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public final class VPAdapter extends RecyclerView.Adapter<VPAdapter.ViewHolder> {
    private final ArrayList<ViewPagerItem> viewPagerItemArrayList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView tcHeading;
        private final TextView tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivimage);
            tcHeading = itemView.findViewById(R.id.tvHeading);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }

    public VPAdapter(ArrayList<ViewPagerItem> viewPagerItemArrayList) {
        this.viewPagerItemArrayList = viewPagerItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.viewpager_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewPagerItem viewPagerItem = viewPagerItemArrayList.get(position);

        holder.imageView.setImageResource(viewPagerItem.getImageID());
        holder.tcHeading.setText(viewPagerItem.getHeading());
        holder.tvDesc.setText(viewPagerItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return viewPagerItemArrayList.size();
    }
}