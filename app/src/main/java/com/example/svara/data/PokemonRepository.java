package com.example.svara.data;

import androidx.lifecycle.LiveData;

import com.example.svara.data.local.LocalDataSource;
import com.example.svara.data.local.entity.DetailPokemonEntity;
import com.example.svara.data.local.entity.PokemonEntity;
import com.example.svara.data.remote.RemoteDataSource;
import com.example.svara.data.remote.response.ApiResponse;
import com.example.svara.data.remote.response.DetailPokemonResponse;
import com.example.svara.data.remote.response.PokemonResponse;
import com.example.svara.data.remote.response.ResultsItem;
import com.example.svara.utils.AppExecutors;
import com.example.svara.utils.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PokemonRepository implements IPokemonRepository {
    private RemoteDataSource remoteDataSource;
    private LocalDataSource localDataSource;
    private AppExecutors appExecutors;

    @Inject
    public PokemonRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }


    @Override
    public LiveData<Resource<List<PokemonEntity>>> getPokemonList() {
        return new NetworkBoundResource<List<PokemonEntity>, PokemonResponse>(appExecutors) {
            @Override
            protected LiveData<List<PokemonEntity>> loadFromDB() {
                return localDataSource.getListPokemon();
            }

            @Override
            protected Boolean shouldFetch(List<PokemonEntity> data) {
                return (data == null) || (data.size() == 0);

            }

            @Override
            protected LiveData<ApiResponse<PokemonResponse>> createCall() {
                return remoteDataSource.getListPokemon();
            }

            @Override
            protected void saveCallResult(PokemonResponse data) {
                ArrayList<PokemonEntity> pokemonList = new ArrayList<>();
                for (ResultsItem response : data.getResults()) {
                    PokemonEntity pokemon = new PokemonEntity(
                            0,
                            false,
                            response.getName(),
                            response.getUrl());

                    pokemonList.add(pokemon);
                }

                localDataSource.insertPokemon(pokemonList);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<DetailPokemonEntity>> getDetailPokemon(int id) {
        return new NetworkBoundResource<DetailPokemonEntity, DetailPokemonResponse>(appExecutors) {
            @Override
            protected LiveData<DetailPokemonEntity> loadFromDB() {
                return localDataSource.getDetailPokemon(id);
            }

            @Override
            protected Boolean shouldFetch(DetailPokemonEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<DetailPokemonResponse>> createCall() {
                return remoteDataSource.getDetailPokemon(id);
            }

            @Override
            protected void saveCallResult(DetailPokemonResponse data) {
                List<DetailPokemonEntity> pokemonEntities = new ArrayList<>();
                DetailPokemonEntity pokemon = new DetailPokemonEntity(
                        data.getId(),
                        data.getName(),
                        data.getHeight(),
                        data.getWeight(),
                        data.getUrl()
                );
                pokemonEntities.add(pokemon);
                localDataSource.insertPokemonDetail(pokemonEntities);

            }
        }.asLiveData();
    }

    @Override
    public LiveData<List<PokemonEntity>> getCaughtPokemon() {
        return null;
    }

    @Override
    public void setCaughtPokemon(PokemonEntity pokemonEntity, boolean state) {

    }
}
