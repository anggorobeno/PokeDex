package com.example.svara.data.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.svara.data.remote.network.ApiHelper;
import com.example.svara.data.remote.network.ApiService;
import com.example.svara.data.remote.response.ApiResponse;
import com.example.svara.data.remote.response.DetailPokemonResponse;
import com.example.svara.data.remote.response.PokemonResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {
    private ApiService apiService;

    @Inject
    public RemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

    public LiveData<ApiResponse<PokemonResponse>> getListPokemon() {
        MutableLiveData<ApiResponse<PokemonResponse>> result = new MutableLiveData<>();
        apiService.getPokemonList(10).enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(ApiResponse.success(response.body()));
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {

            }
        });
        return result;
    }

    public LiveData<ApiResponse<DetailPokemonResponse>> getDetailPokemon(int id){
        MutableLiveData<ApiResponse<DetailPokemonResponse>> result = new MutableLiveData<>();
        apiService.getDetailPokemon(id).enqueue(new Callback<DetailPokemonResponse>() {
            @Override
            public void onResponse(Call<DetailPokemonResponse> call, Response<DetailPokemonResponse> response) {
                if (response.body() != null && response.isSuccessful()){
                    result.setValue(ApiResponse.success(response.body()));
                }
//                else result.setValue(ApiResponse.error(response.message(),null));
            }

            @Override
            public void onFailure(Call<DetailPokemonResponse> call, Throwable t) {
//                result.setValue(ApiResponse);

            }
        });
        return result;
    }

}
