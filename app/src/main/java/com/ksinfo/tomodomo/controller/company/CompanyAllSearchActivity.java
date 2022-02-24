package com.ksinfo.tomodomo.controller.company;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.model.itf.CompanyInterface;
import com.ksinfo.tomodomo.model.vo.company.CompanyMainVO;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyAllSearchActivity extends AppCompatActivity {
   // private Set<String> querySet = new LinkedHashSet<>();
    @Inject
    CompanyInterface companyInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_all_company_search);
        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);
        companyInterface.getPopularCompanyList().enqueue(new Callback<List<CompanyMainVO>>() {
            @Override
            public void onResponse(Call<List<CompanyMainVO>> call, Response<List<CompanyMainVO>> response) {
                if(response.isSuccessful()){
                    List<CompanyMainVO> companyList = response.body();

                    RecyclerView recyclerView2= findViewById(R.id.cp_all_company_search_rv_query2);
                    TextView textView2 = findViewById(R.id.cp_all_company_tv_recent_search2);
                    if(companyList.isEmpty()){
                        textView2.setVisibility(View.VISIBLE);
                    }else{
                        textView2.setVisibility(View.GONE);
                        CompanySearchPopular9Adapter companySearchPopular9Adapter = new CompanySearchPopular9Adapter(companyList,getString(R.string.base_url),companyInterface);
                        recyclerView2.setAdapter(companySearchPopular9Adapter);
                    }





                }
            }

            @Override
            public void onFailure(Call<List<CompanyMainVO>> call, Throwable t) {

            }
        });
        SearchView searchView = findViewById(R.id.cp_all_company_search_search);
        TextView textView = findViewById(R.id.cp_all_company_tv_recent_search);
        ConstraintLayout constraintLayout = findViewById(R.id.cp_all_company_cl_recent_search);
        RecyclerView recyclerView= findViewById(R.id.cp_all_company_search_rv_query);
        CompanySearchQueryAdapter companySearchQueryAdapter = new CompanySearchQueryAdapter(textView);
        recyclerView.setAdapter(companySearchQueryAdapter);





        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("dfdsfsdfs",query);
                //querySet.add(query);
                companySearchQueryAdapter.addQuery(query);
                //textView.setVisibility(View.VISIBLE);
                //textView.setVisibility(View.INVISIBLE); 공간 차지 안보임
                textView.setVisibility(View.GONE);
                searchView.setQuery("", false); // 문구 초기화
                searchView.clearFocus(); // 포커스 초기화




                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }


}
