package com.ksinfo.tomodomo.controller.company;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ksinfo.tomodomo.R;

import org.jetbrains.annotations.NotNull;

public class FragmentHelp extends Fragment implements  View.OnClickListener{
    public FragmentHelp(){

    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cp_fragment_help,container,false);
        ConstraintLayout layout = v.findViewById(R.id.cp_frag_help);
        ImageView imageView = v.findViewById(R.id.iv_back);

         imageView.setOnClickListener(this::onClick);
        return  v;

       // return inflater.inflate(R.layout.fragment_help,container,false);


    }
    //ImageView guideBack = (ImageView) findViewById(R.id.iv_back);

    @Override
    public void onClick(View v) {


        if(v.getId() == (R.id.iv_back)){
            View view = ((CompanyReviewActivity) getActivity()).findViewById(R.id.background);
            view.setVisibility(View.INVISIBLE);
            Log.d("console","console");
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().remove(FragmentHelp.this).commit();
            fragmentManager.popBackStack();
        }
    }






}
