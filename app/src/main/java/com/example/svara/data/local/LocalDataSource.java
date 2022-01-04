package com.example.svara.data.local;

import androidx.lifecycle.LiveData;

import com.example.svara.data.local.entity.DetailPokemonEntity;
import com.example.svara.data.local.entity.PokemonEntity;
import com.example.svara.data.local.room.PokemonDao;

import java.util.List;

import javax.inject.Inject;

public class LocalDataSource {
    private PokemonDao pokemonDao;

    @Inject
    public LocalDataSource(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }

    public LiveData<List<PokemonEntity>> getCaughtPokemon() {
        return pokemonDao.getCaughtPokemon();
    }

    public void setCaughtPokemon(PokemonEntity pokemonEntity, boolean newState) {
        pokemonEntity.setCaught(newState);
        pokemonDao.updateCourse(pokemonEntity);
    }

    public LiveData<List<PokemonEntity>> getListPokemon() {
        return pokemonDao.getListPokemon();
    }

    public void insertPokemon(List<PokemonEntity> pokemonEntity) {
        pokemonDao.insertPokemon(pokemonEntity);
    }

    public void insertPokemonDetail(List<DetailPokemonEntity> pokemonEntities){
        pokemonDao.insertPokemonDetail(pokemonEntities);
    }

    public LiveData<DetailPokemonEntity> getDetailPokemon(int id){
        return pokemonDao.getDetailPokemon(id);
    }


}
