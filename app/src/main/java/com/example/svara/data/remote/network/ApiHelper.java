package com.example.svara.data.remote.network;

import com.example.svara.data.remote.response.DetailPokemonResponse;
import com.example.svara.data.remote.response.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.Path;

public interface ApiHelper {
    Call<PokemonResponse> getPokemonList(int limit);

    Call<DetailPokemonResponse> getDetailPokemon(int id);

}
