package com.example.svara.data.paging;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.svara.R;
import com.example.svara.data.local.entity.PokemonEntity;
import com.example.svara.databinding.FragmentPagedBinding;
import com.example.svara.databinding.FragmentPokemonBinding;
import com.example.svara.ui.adapter.PokemonAdapter;
import com.example.svara.ui.listpokemon.PokemonViewModel;
import com.example.svara.utils.Constant;
import com.example.svara.utils.Helper;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PagedFragment extends Fragment {
    private PagedListViewModel viewModel;
    private FragmentPagedBinding binding;
    private PagedListAdapter adapter = new PagedListAdapter(new PokemonComparator());

    public PagedFragment() {
        //empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(PagedListViewModel.class);
        binding = FragmentPagedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPagedList();
    }

    private void getPagedList() {
        viewModel.pagingDataFlow.subscribe(pokemonPaging -> {
            // submit new data to recyclerview adapter
            adapter.submitData(getViewLifecycleOwner().getLifecycle(), pokemonPaging);
            showRv();
        });
    }

    private void showRv() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.addItemDecoration(new GridSpace(2, 20, true));
        binding.recyclerView.setAdapter(
                adapter.withLoadStateFooter(
                        new PokemonLoadStateAdapter(view -> {
                            adapter.retry();
                        })
                ));
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // If progress will be shown then span size will be 1 otherwise it will be 2
                return adapter.getItemViewType(position) == PagedListAdapter.LOADING_ITEM ? 1 : 2;
            }
        });
    }
}
