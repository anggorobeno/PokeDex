package com.example.svara.data;

import androidx.lifecycle.LiveData;

import com.example.svara.data.local.LocalDataSource;
import com.example.svara.data.local.entity.PokemonEntity;
import com.example.svara.data.local.room.PokemonDao;
import com.example.svara.data.remote.network.ApiHelper;
import com.example.svara.data.remote.network.ApiService;
import com.example.svara.data.remote.response.DetailPokemonResponse;
import com.example.svara.data.remote.response.PokemonResponse;
import com.example.svara.utils.AppExecutors;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class Repository {
    private ApiHelper apiHelper;
    private LocalDataSource localDataSource;
    private AppExecutors appExecutors;

    @Inject
    public Repository(ApiHelper apiHelper, LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.apiHelper = apiHelper;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    public Call<PokemonResponse> getPokemonList(int limit) {
        return apiHelper.getPokemonList(limit);
    }
    public Call<DetailPokemonResponse> getDetailPokemon(int id){
        return apiHelper.getDetailPokemon(id);
    }

    public LiveData<List<PokemonEntity>> getCaughtPokemon(){
        return localDataSource.getCaughtPokemon();
    }

    



}
