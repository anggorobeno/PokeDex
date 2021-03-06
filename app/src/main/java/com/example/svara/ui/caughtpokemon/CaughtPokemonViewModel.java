package com.example.svara.ui.caughtpokemon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.svara.data.PokemonRepository;
import com.example.svara.data.local.entity.DetailPokemonEntity;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CaughtPokemonViewModel extends ViewModel {
    private PokemonRepository repository;

    @Inject
    public CaughtPokemonViewModel(PokemonRepository repository) {
        this.repository = repository;
    }
    public LiveData<List<DetailPokemonEntity>> getCaughtPokemon(){
        return repository.getCaughtPokemon();
    }


}
