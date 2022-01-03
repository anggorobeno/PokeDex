package com.example.svara.data.remote.network;

import com.example.svara.data.remote.response.DetailPokemonResponse;
import com.example.svara.data.remote.response.PokemonResponse;

import javax.inject.Inject;

import retrofit2.Call;

public class ApiHelperImpl implements ApiHelper{
    private ApiService apiService;

    @Inject
    public ApiHelperImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Call<PokemonResponse> getPokemonList(int limit) {
        return apiService.getPokemonList(limit);
    }

    @Override
    public Call<DetailPokemonResponse> getDetailPokemon(int id) {
        return apiService.getDetailPokemon(id);
    }
}
