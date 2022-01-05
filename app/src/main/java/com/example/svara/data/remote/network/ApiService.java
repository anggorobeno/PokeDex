package com.example.svara.data.remote.network;

import com.example.svara.data.remote.response.DetailPokemonResponse;
import com.example.svara.data.remote.response.PokemonResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/v2/pokemon")
    Call<PokemonResponse> getPokemonList(
            @Query("offset") int offset,
            @Query("limit") int limit);

    @GET("api/v2/pokemon/{id}")
    Call<DetailPokemonResponse> getDetailPokemon(@Path("id") int id);

    @GET("api/v2/pokemon")
    Single<PokemonResponse> getPagedList(
            @Query("offset") int offset,
            @Query("limit") int limit);



}
