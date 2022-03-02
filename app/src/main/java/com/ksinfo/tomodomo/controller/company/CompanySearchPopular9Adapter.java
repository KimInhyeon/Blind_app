package com.ksinfo.tomodomo.controller.company;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.model.itf.CompanyInterface;
import com.ksinfo.tomodomo.model.vo.company.CompanyMainVO;
import com.ksinfo.tomodomo.model.vo.company.CompanyReviewAverageVO;
import com.ksinfo.tomodomo.model.vo.company.CompanyReviewVO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanySearchPopular9Adapter extends RecyclerView.Adapter<CompanySearchPopular9Adapter.CompanySearchPopular9Holder> {
    private List<CompanyMainVO> companyMainVOList;
    private String url;
    private CompanyInterface companyInterface;

    public CompanySearchPopular9Adapter(List<CompanyMainVO> companyMainVOList, String url, CompanyInterface companyInterface) {
        this.companyMainVOList = companyMainVOList;
        this.url = url;
        this.companyInterface = companyInterface;
    }

    @NonNull
    @Override
    public CompanySearchPopular9Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cp_all_company_search_popular9, parent, false);
        // CompanySearchPopular9Holder companySearchPopular9Holder = new CompanySearchPopular9Holder(itemView,companyMainVOList,companyInterface)

//        CompanySearchQueryHolder companySearchQueryHolder = new CompanySearchQueryHolder(itemView,textView);
//        companySearchQueryHolder.setDeleteFunction(queryList,this);
        return new CompanySearchPopular9Holder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CompanySearchPopular9Holder holder, int position) {
        CompanyMainVO company = companyMainVOList.get(position);
        String url = this.url + "resources/images/company/" + company.getCompanyId() + ".png";

        Glide.with(holder.itemView).load(url).error(R.drawable.bd_p_thumbs_up).into(holder.imageView);
        holder.textView.setText(company.getCompanyName());
        holder.textView2.setText(Integer.toString(company.getAllPoint()));
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//
//                intent.putExtra("companyId", company.getCompanyId());
//                setResult(RESULT_OK, intent);
//                finish();
//                try {
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

                companyInterface.getCompanyAverage(company.getCompanyId()).enqueue(new Callback<CompanyReviewAverageVO>() {
                    @Override
                    public void onResponse(Call<CompanyReviewAverageVO> call, Response<CompanyReviewAverageVO> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(holder.itemView.getContext(), CompanyReviewListActivity.class);
                            CompanyReviewAverageVO companyReviewAverageVO = response.body();
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("companyAverageVO", companyReviewAverageVO);
                            Log.d("feeeeeeee", response.body().toString());
                            intent.putExtra("companyName",company.getCompanyName());
                            intent.putExtra("companyId",company.getCompanyId());




                            companyInterface.getCompanyReviewList(company.getCompanyId(), 1).enqueue(new Callback<ArrayList<CompanyReviewVO>>() {
                                @Override
                                public void onResponse(Call<ArrayList<CompanyReviewVO>> call, Response<ArrayList<CompanyReviewVO>> response) {
                                    if (response.isSuccessful()) {

                                        ArrayList<CompanyReviewVO> companyReviewVOArrayList = response.body();
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.putParcelableArrayListExtra("companyReviewVOArrayList", companyReviewVOArrayList);
                                        holder.itemView.getContext().startActivity(intent);
                                        Log.d("ㅣㅏㅓㅣㅓㅣㅓ", response.body().toString());

                                    }
                                }

                                @Override
                                public void onFailure(Call<ArrayList<CompanyReviewVO>> call, Throwable t) {

                                }
                            });







                        }
                    }

                    @Override
                    public void onFailure(Call<CompanyReviewAverageVO> call, Throwable t) {
                        Log.d("fdlfkjdfkldjf","kjsdlfjslfjsd");
                    }
                });







            }
        });
    }

    @Override
    public int getItemCount() {

        return companyMainVOList.size();
    }

    static class CompanySearchPopular9Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        private ImageView imageView2;

        public CompanySearchPopular9Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cp_all_company_search_popular9_logo);
            textView = itemView.findViewById(R.id.cp_all_company_search_popular9_name);
            textView2 = itemView.findViewById(R.id.cp_all_company_search_popular9_rate);
            imageView2 = itemView.findViewById(R.id.cp_all_company_search_popular9_move);
        }
    }


}
