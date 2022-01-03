package com.example.svara.viewmodel;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.svara.data.Repository;
import com.example.svara.data.remote.response.PokemonResponse;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class PokemonViewModel extends ViewModel {
    private Repository repository;
    private MutableLiveData<PokemonResponse> _pokemonList = new MutableLiveData<>();
    public LiveData<PokemonResponse> pokemonList(){
        return _pokemonList;
    }
    private int pageLimit = 10;

    @Inject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
        getPokemonList();
    }

    private void getPokemonList(){
        repository.getPokemonList(pageLimit).enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    _pokemonList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {


            }
        });

    }

}
