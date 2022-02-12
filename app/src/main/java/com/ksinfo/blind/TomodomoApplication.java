package com.ksinfo.blind;

import android.app.Application;

import com.ksinfo.blind.util.ApplicationComponent;
import com.ksinfo.blind.util.DaggerApplicationComponent;
import com.ksinfo.blind.util.MainActivityModule;
import com.ksinfo.blind.util.NetworkModule;

public final class TomodomoApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        MainActivityModule mainActivityModule = new MainActivityModule(this);
        NetworkModule networkModule = new NetworkModule(getString(R.string.base_url));
        applicationComponent = DaggerApplicationComponent.builder()
                                                         .mainActivityModule(mainActivityModule)
                                                         .networkModule(networkModule)
                                                         .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}