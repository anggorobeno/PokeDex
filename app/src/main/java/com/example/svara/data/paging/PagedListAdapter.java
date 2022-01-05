package com.example.svara.data.paging;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.svara.databinding.PokemonListBinding;

import com.example.svara.data.remote.response.ResultsItem;
import com.example.svara.utils.Helper;

public class PagedListAdapter extends PagingDataAdapter<ResultsItem, PagedListAdapter.ViewHolder> {
    // Define Loading ViewType
    public static final int LOADING_ITEM = 0;
    // Define Movie ViewType
    public static final int MOVIE_ITEM = 1;

    public PagedListAdapter(@NonNull DiffUtil.ItemCallback<ResultsItem> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(PokemonListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultsItem pokemon = getItem(position);
        if (pokemon != null){
            holder.bind(pokemon);
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ? MOVIE_ITEM : LOADING_ITEM;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        PokemonListBinding binding;

        public ViewHolder(@NonNull PokemonListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ResultsItem pokemon) {
            String id = Helper.getIdFromUrl(pokemon.getUrl());
            binding.tvPokemonName.setText(pokemon.getName());
            Glide.with(itemView)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png")
                    .into(binding.ivPokemon);
        }
    }
}
