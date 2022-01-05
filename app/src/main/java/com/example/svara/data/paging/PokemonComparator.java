package com.example.svara.data.paging;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.svara.data.remote.response.ResultsItem;

public class PokemonComparator extends DiffUtil.ItemCallback<ResultsItem> {
    @Override
    public boolean areItemsTheSame(@NonNull ResultsItem oldItem, @NonNull ResultsItem newItem) {
        return oldItem.getUrl().equals(newItem.getUrl());
    }

    @Override
    public boolean areContentsTheSame(@NonNull ResultsItem oldItem, @NonNull ResultsItem newItem) {
        return oldItem.getUrl().equals(newItem.getUrl());
    }
}
