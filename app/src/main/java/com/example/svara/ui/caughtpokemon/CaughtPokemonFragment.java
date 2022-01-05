package com.example.svara.ui.caughtpokemon;

import static com.example.svara.utils.Status.ERROR;
import static com.example.svara.utils.Status.LOADING;
import static com.example.svara.utils.Status.SUCCESS;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.svara.R;
import com.example.svara.data.local.entity.DetailPokemonEntity;
import com.example.svara.data.local.entity.PokemonEntity;
import com.example.svara.databinding.FragmentCaughtPokemonBinding;
import com.example.svara.databinding.FragmentPokemonBinding;
import com.example.svara.ui.adapter.CaughtPokemonAdapter;
import com.example.svara.ui.adapter.PokemonAdapter;
import com.example.svara.ui.listpokemon.PokemonViewModel;
import com.example.svara.utils.Constant;
import com.example.svara.utils.Helper;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CaughtPokemonFragment extends Fragment {
    private CaughtPokemonViewModel viewModel;
    private FragmentCaughtPokemonBinding binding;
    private CaughtPokemonAdapter adapter = new CaughtPokemonAdapter();

    public CaughtPokemonFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(CaughtPokemonViewModel.class);
        binding = FragmentCaughtPokemonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCaughtPokemonList();
    }

    private void getCaughtPokemonList() {
        viewModel.getCaughtPokemon().observe(getViewLifecycleOwner(), caughtPokemon -> {
            if (caughtPokemon != null) {
                adapter.setListPokemon(caughtPokemon);
                adapter.notifyDataSetChanged();
                showRv();
            }

        });

    }

    private void showRv() {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickCallback(new CaughtPokemonAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(DetailPokemonEntity data) {
                Bundle bundle = new Bundle();
                int id = Integer.parseInt(data.getId());
                bundle.putInt(Constant.EXTRA_POKEMON_ID, id);
                Navigation.findNavController(requireView()).navigate(R.id.detailPokemonFragment, bundle);
            }
        });
    }
}