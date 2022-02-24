package com.ksinfo.tomodomo.controller.company;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.tomodomo.R;

import java.util.ArrayList;
import java.util.List;

public class CompanySearchQueryAdapter extends RecyclerView.Adapter<CompanySearchQueryAdapter.CompanySearchQueryHolder> {
    private List<String> queryList;
    private TextView textView;
    public CompanySearchQueryAdapter(TextView textView) {
        queryList = new ArrayList<>();
        this.textView=textView;
    }


    @NonNull
    @Override
    public CompanySearchQueryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cp_all_company_search_recent_query, parent, false);
        CompanySearchQueryHolder companySearchQueryHolder = new CompanySearchQueryHolder(itemView,textView);
        companySearchQueryHolder.setDeleteFunction(queryList,this);
        return companySearchQueryHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CompanySearchQueryHolder holder, int position) {
        Log.d("mvncmvncmvncm", queryList.get(position));

        holder.query.setText(queryList.get(position));
    }

    @Override
    public int getItemCount() {
        return queryList.size();
    }

    public void addQuery(String query) {
        if (queryList.contains(query)) {
            queryList.remove(query);
        }
        queryList.add(0, query);

        notifyDataSetChanged();
    }

    static class CompanySearchQueryHolder extends RecyclerView.ViewHolder {
        private final TextView query;
        private final ImageView deleteBtn;
        private TextView textView;

        public CompanySearchQueryHolder(@NonNull View itemView,TextView textView) {
            super(itemView);

            query = itemView.findViewById(R.id.cp_all_company_search_recent_query_tv);
            deleteBtn = itemView.findViewById(R.id.cp_all_company_serach_recent_delete_tv);
            this.textView=textView;
        }
        public void setDeleteFunction(List<String> queryList,CompanySearchQueryAdapter companySearchQueryAdapter) {
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    queryList.remove(getAbsoluteAdapterPosition());
                    companySearchQueryAdapter.notifyDataSetChanged();
                    if(queryList.isEmpty()){
                        textView.setVisibility(View.VISIBLE);
                    }

                }
            });
        }
    }

}
