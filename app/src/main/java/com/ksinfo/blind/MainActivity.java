package com.ksinfo.blind;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.blind.databinding.ActivityMainBinding;
import com.ksinfo.blind.member.LoginActivity;

import javax.inject.Inject;

public final class MainActivity extends AppCompatActivity {
    @Inject VPAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TomodomoApplication tomodomoApplication = ((TomodomoApplication) getApplication());
        tomodomoApplication.getApplicationComponent().inject(this);

        binding.viewpager.setAdapter(vpAdapter);
        binding.viewpager.setClipToPadding(false);
        binding.viewpager.setClipChildren(false);
        binding.viewpager.setOffscreenPageLimit(2);
        binding.viewpager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
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