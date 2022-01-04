package com.example.svara.ui.listpokemon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.svara.data.PokemonRepository;
import com.example.svara.data.local.entity.PokemonEntity;
import com.example.svara.utils.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

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
