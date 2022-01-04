package com.example.svara.data;

import androidx.lifecycle.LiveData;

import com.example.svara.data.local.entity.DetailPokemonEntity;
import com.example.svara.data.local.entity.PokemonEntity;
import com.example.svara.data.remote.response.DetailPokemonResponse;
import com.example.svara.utils.Resource;

import java.util.List;

public interface IPokemonRepository {
    public LiveData<Resource<List<PokemonEntity>>> getPokemonList();
    public LiveData<Resource<DetailPokemonEntity>> getDetailPokemon(int id);
    public LiveData<List<PokemonEntity>> getCaughtPokemon();
    public void setCaughtPokemon(PokemonEntity pokemonEntity, boolean state);

}
