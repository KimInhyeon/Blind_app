package com.ksinfo.tomodomo.module;

import com.ksinfo.tomodomo.model.itf.BoardInterface;
import com.ksinfo.tomodomo.model.itf.CompanyInterface;
import com.ksinfo.tomodomo.model.itf.CompanyJobGroupInterface;
import com.ksinfo.tomodomo.model.itf.MemberInterface;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.MediaType;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public final class NetworkModule {
    private final String baseURL;

    public NetworkModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Provides
    @Singleton
    @Named("json")
    public MediaType provideJsonContentType() {
        return MediaType.parse("application/json");
    }

    @Provides
    @Singleton
    @Named("plain")
    public MediaType providePlainContentType() {
        return MediaType.parse("text/plain");
    }

    @Provides
    @Singleton
    public ScalarsConverterFactory provideScalarsConverterFactory() {
        return ScalarsConverterFactory.create();
    }

    @Provides
    @Singleton
    public JacksonConverterFactory provideJacksonConverterFactory() {
        return JacksonConverterFactory.create();
    }

    @Provides
    @Singleton
    @Named("scalarsRetrofit")
    public Retrofit provideScalarsRetrofit(ScalarsConverterFactory scalarsConverterFactory) {
        return new Retrofit.Builder()
                           .baseUrl(baseURL)
                           .addConverterFactory(scalarsConverterFactory)
                           .build();
    }

    @Provides
    @Singleton
    @Named("jacksonRetrofit")
    public Retrofit provideJacksonRetrofit(JacksonConverterFactory jacksonConverterFactory) {
        return new Retrofit.Builder()
                           .baseUrl(baseURL)
                           .addConverterFactory(jacksonConverterFactory)
                           .build();
    }

    @Provides
    @Singleton
    public MemberInterface provideMemberInterface(@Named("jacksonRetrofit") Retrofit retrofit) {
        return retrofit.create(MemberInterface.class);
    }

    @Provides
    @Singleton
    public BoardInterface provideBoardInterface(@Named("jacksonRetrofit") Retrofit retrofit) {
        return retrofit.create(BoardInterface.class);
    }

    @Provides
    @Singleton
    public CompanyJobGroupInterface provideCompanyJobGroupInterface(@Named("jacksonRetrofit") Retrofit retrofit) {
        return retrofit.create(CompanyJobGroupInterface.class);
    }

    @Provides
    @Singleton
    public CompanyInterface provideCompanyInterface(@Named("jacksonRetrofit") Retrofit retrofit) {
        return retrofit.create(CompanyInterface.class);
    }
}