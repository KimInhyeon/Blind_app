package com.ksinfo.tomodomo.controller.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.TomodomoApplication;
import com.ksinfo.tomodomo.controller.member.LoginActivity;
import com.ksinfo.tomodomo.databinding.CmMainBinding;

import javax.inject.Inject;

public final class MainActivity extends AppCompatActivity {
    private CmMainBinding binding;
    @Inject VPAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = CmMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TomodomoApplication tomodomoApplication = (TomodomoApplication) getApplication();
        tomodomoApplication.getApplicationComponent().inject(this);

        binding.cmMainViewpager.setAdapter(vpAdapter);
        binding.cmMainViewpager.setClipToPadding(false);
        binding.cmMainViewpager.setClipChildren(false);
        binding.cmMainViewpager.setOffscreenPageLimit(2);
        binding.cmMainViewpager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        binding.cmMainLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}