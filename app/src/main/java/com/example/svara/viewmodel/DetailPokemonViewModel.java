package com.example.svara.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.svara.data.Repository;
import com.example.svara.data.remote.response.DetailPokemonResponse;
import com.example.svara.data.remote.response.PokemonResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class DetailPokemonViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<DetailPokemonResponse> _detailPokemon = new MutableLiveData<>();
    public LiveData<DetailPokemonResponse> detailPokemon(){
        return _detailPokemon;
    }
    @Inject
    public DetailPokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public void setIdPokemon(int id){
        getDetailPokemon(id);
    }

    private void getDetailPokemon(int id){
        repository.getDetailPokemon(id).enqueue(new Callback<DetailPokemonResponse>() {
            @Override
            public void onResponse(Call<DetailPokemonResponse> call, Response<DetailPokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    _detailPokemon.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<DetailPokemonResponse> call, Throwable t) {

            }
        });
    }

    public void setCaughtPokemon(){

    }


}
