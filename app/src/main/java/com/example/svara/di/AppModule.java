package com.example.svara.di;


import com.example.svara.BuildConfig;
import com.example.svara.data.remote.network.ApiHelper;
import com.example.svara.data.remote.network.ApiHelperImpl;
import com.example.svara.data.remote.network.ApiService;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)

public class AppModule {
    public static final String BASE_URL = "https://pokeapi.co/";

    @Provides
    static String provideBaseUrl() {
        return BASE_URL;
    }

    @Provides
    static OkHttpClient provideOkHttpClient() {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
        } else {
            return new OkHttpClient.Builder()
                    .build();
        }


    }

    @Provides
    static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    static ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    static ApiHelper provideApiHelper(ApiHelperImpl apiHelper){
        return apiHelper;
    }




}
