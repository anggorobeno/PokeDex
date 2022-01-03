package com.example.svara.data;

import com.example.svara.data.remote.network.ApiHelper;
import com.example.svara.data.remote.network.ApiService;
import com.example.svara.data.remote.response.DetailPokemonResponse;
import com.example.svara.data.remote.response.PokemonResponse;

import javax.inject.Inject;

import retrofit2.Call;

public class Repository {
    private ApiHelper apiHelper;

    @Inject
    public Repository(ApiHelper apiHelper) {
        this.apiHelper = apiHelper;
    }
    public Call<PokemonResponse> getPokemonList(int limit) {
        return apiHelper.getPokemonList(limit);
    }
    public Call<DetailPokemonResponse> getDetailPokemon(int id){
        return apiHelper.getDetailPokemon(id);
    }

}
