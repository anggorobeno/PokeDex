package com.example.svara.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "detail_pokemon_table")
public class DetailPokemonEntity {

    @PrimaryKey
    @NonNull
    private String id;

    private String name;

    private int height;

    private int weight;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public DetailPokemonEntity(String id, String name, int height, int weight, String url) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.url = url;
    }
}
