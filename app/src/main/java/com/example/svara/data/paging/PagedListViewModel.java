package com.example.svara.data.paging;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.example.svara.data.remote.response.ResultsItem;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

@HiltViewModel
public class PagedListViewModel extends ViewModel {
    public Flowable<PagingData<ResultsItem>> pagingDataFlow;
    private PokemonPagingSource pokemonPagingSource;

    @Inject
    public PagedListViewModel(PokemonPagingSource pokemonPagingSource) {
        this.pokemonPagingSource = pokemonPagingSource;
        init();
    }

    private void init() {
        Pager<Integer, ResultsItem> pager = new Pager(
                // Create new paging config
                new PagingConfig(20, // pageSize - Count of items in one page
                        20, // prefetchDistance - Number of items to prefetch
                        false, // enablePlaceholders - Enable placeholders for data which is not yet loaded
                        20, // initialLoadSize - Count of items to be loaded initially
                        20 * 499),// maxSize - Count of total items to be shown in recyclerview
                () -> pokemonPagingSource); // set paging source

        // init Flowable
        pagingDataFlow = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(pagingDataFlow, coroutineScope);
    }
}
