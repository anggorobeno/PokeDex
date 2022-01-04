package com.example.svara.viewmodel;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.svara.data.PokemonRepository;
import com.example.svara.data.Repository;
import com.example.svara.data.local.entity.PokemonEntity;
import com.example.svara.data.remote.response.PokemonResponse;
import com.example.svara.utils.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class PokemonViewModel extends ViewModel {
    private PokemonRepository repository;


    @Inject
    public PokemonViewModel(PokemonRepository repository) {
        this.repository = repository;
    }

    public LiveData<Resource<List<PokemonEntity>>> getListPokemon(){
        return repository.getPokemonList();
    }



}
