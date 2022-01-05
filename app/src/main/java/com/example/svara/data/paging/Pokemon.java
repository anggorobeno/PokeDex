package com.example.svara.data.paging;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class Pokemon {


    public int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    private String name;

    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pokemon(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
