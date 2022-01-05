package com.example.svara.data.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingState;
import androidx.paging.rxjava3.RxPagingSource;

import com.example.svara.data.local.entity.PokemonEntity;
import com.example.svara.data.remote.network.ApiService;
import com.example.svara.data.remote.response.PokemonResponse;
import com.example.svara.data.remote.response.ResultsItem;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonPagingSource extends RxPagingSource<Integer, ResultsItem> {

    private ApiService apiService;

    @Inject
    public PokemonPagingSource(ApiService apiService) {
        this.apiService = apiService;
    }

    @NonNull
    @Override
    public Single<LoadResult<Integer, ResultsItem>> loadSingle(@NonNull LoadParams<Integer> loadParams) {
        try {
            // If page number is already there then init page variable with it otherwise we are loading fist page
            int page = loadParams.getKey() != null ? loadParams.getKey() : 10;
            // Send request to server with page number
            return apiService.
                    getPagedList(page,20)
                    // Subscribe the result
                    .subscribeOn(Schedulers.io())
                    // Map result top List of movies
                    .map(PokemonResponse::getResults)
                    // Map result to LoadResult Object
                    .map(pokemon -> toLoadResult(pokemon, page))
                    // when error is there return error
                    .onErrorReturn(LoadResult.Error::new);
        } catch (Exception e) {
            // Request ran into error return error
            return Single.just(new LoadResult.Error(e));
        }
    }

    @Nullable
    @Override
    public Integer getRefreshKey(@NonNull PagingState<Integer, ResultsItem> pagingState) {
        return null;
    }
    // Method to map Movies to LoadResult object
    private LoadResult<Integer, ResultsItem> toLoadResult(List<ResultsItem> pokemon, int page) {
        return new LoadResult.Page(pokemon, page == 1 ? null : page - 1, page + 1);
    }
}
