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
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ksinfo.tomodomo.R;

public class FragmentHelp extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.cp_fragment_help, container, false);
        ConstraintLayout layout = v.findViewById(R.id.cp_frag_help);
        ImageView imageView = v.findViewById(R.id.iv_back);

        imageView.setOnClickListener(this::onClick);
        return v;

        // return inflater.inflate(R.layout.fragment_help,container,false);


    }
    //ImageView guideBack = (ImageView) findViewById(R.id.iv_back);

    @Override
    public void onClick(View v) {
        if (v.getId() == (R.id.iv_back)) {
            View view = ((CompanyReviewActivity) getActivity()).findViewById(R.id.background);
            view.setVisibility(View.INVISIBLE);
            Log.d("console", "console");
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().remove(FragmentHelp.this).commit();
            fragmentManager.popBackStack();

            NestedScrollView scrollView = ((CompanyReviewActivity) getActivity()).findViewById(R.id.cp_review_sv);
            scrollView.fullScroll(400);
//            NestedScrollView view2 = ((CompanyReviewActivity) getActivity()).findViewById(R.id.cp_review_sv);
//            view2.fullScroll();

        }
    }


}