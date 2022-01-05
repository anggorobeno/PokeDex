package com.example.svara.di;


import android.content.Context;

import com.example.svara.BuildConfig;
import com.example.svara.data.local.room.PokemonDB;
import com.example.svara.data.local.room.PokemonDao;
import com.example.svara.data.remote.network.ApiService;
import com.example.svara.utils.AppExecutors;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
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
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        HttpUrl originalHttpUrl = original.url();
                        HttpUrl url = originalHttpUrl.newBuilder().build();
                        Request.Builder requestBuilder = original.newBuilder()
                                .url(url);

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    })
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
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    static ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    static PokemonDB provideDatabase(@ApplicationContext Context context) {
        return PokemonDB.getInstance(context);
    }

    @Provides
    static PokemonDao provideDao(PokemonDB db) {
        return db.pokemonDao();
    }

    @Provides
    static AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }


}
