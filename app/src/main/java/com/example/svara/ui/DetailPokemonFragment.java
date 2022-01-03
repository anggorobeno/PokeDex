package com.example.svara.ui;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.svara.R;
import com.example.svara.databinding.FragmentDetailPokemonBinding;
import com.example.svara.databinding.FragmentPokemonBinding;
import com.example.svara.utils.Constant;
import com.example.svara.utils.Helper;
import com.example.svara.viewmodel.DetailPokemonViewModel;
import com.example.svara.viewmodel.PokemonViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailPokemonFragment extends Fragment {

    private FragmentDetailPokemonBinding binding;
    private DetailPokemonViewModel viewModel;

    public DetailPokemonFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(DetailPokemonViewModel.class);
        binding = FragmentDetailPokemonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int id = requireArguments().getInt(Constant.EXTRA_POKEMON_ID);
        Log.d(TAG, "onViewCreated: "+ id);
        showDetailPokemon(id);
        binding.icBack.setOnClickListener(view1 -> {
            Navigation.findNavController(requireView()).navigateUp();
        });
    }

    private void showDetailPokemon(int id) {
        viewModel.setIdPokemon(id);
        viewModel.detailPokemon().observe(getViewLifecycleOwner(),detailPokemonResponse -> {
            if (detailPokemonResponse != null){
                Glide.with(requireContext())
                        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png")
                        .into(binding.ivPokemon);
                binding.tvPokemonName.setText(detailPokemonResponse.getName());
                binding.tvPokemonHeight.setText(Helper.getHeight(detailPokemonResponse.getHeight()));
                binding.tvPokemonWeight.setText(Helper.getWeight(detailPokemonResponse.getWeight()));
                binding.tvPokemonId.setText(Helper.idConverter(id));
            }
        });




    }
}