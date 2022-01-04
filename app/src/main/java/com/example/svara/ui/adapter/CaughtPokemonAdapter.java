package com.example.svara.ui.adapter;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.svara.data.local.entity.DetailPokemonEntity;
import com.example.svara.data.local.entity.PokemonEntity;
import com.example.svara.databinding.PokemonListBinding;
import com.example.svara.ui.caughtpokemon.CaughtPokemonFragment;
import com.example.svara.utils.Helper;

import java.util.ArrayList;
import java.util.List;

public class CaughtPokemonAdapter extends RecyclerView.Adapter<CaughtPokemonAdapter.ViewHolder> {
    private ArrayList<DetailPokemonEntity> listPokemon = new ArrayList<>();
    public void setListPokemon(List<DetailPokemonEntity> listPokemon){
        Log.d(TAG, "setListPokemon: " + String.valueOf(listPokemon));
        if (listPokemon == null) return;
        this.listPokemon.clear();
        this.listPokemon.addAll(listPokemon);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PokemonListBinding binding = PokemonListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listPokemon.get(position));

    }

    @Override
    public int getItemCount() {
        return listPokemon.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        PokemonListBinding binding;
        public ViewHolder(@NonNull PokemonListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        private void bind(DetailPokemonEntity pokemon){

            binding.tvPokemonName.setText(pokemon.getName());
            Glide.with(itemView)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getId()+".png")
                    .into(binding.ivPokemon);
        }
    }
}
