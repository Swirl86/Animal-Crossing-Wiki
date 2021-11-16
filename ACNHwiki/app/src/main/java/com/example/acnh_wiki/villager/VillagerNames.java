package com.example.acnh_wiki.villager;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VillagerNames implements Serializable {
    @SerializedName("name-EUen")
    private String nameEUen;

    public VillagerNames(String nameEUen) {
        this.nameEUen = nameEUen;
    }

    public String getNameEUen() {
        return nameEUen;
    }

    public void setNameEUen(String nameEUen) {
        this.nameEUen = nameEUen;
    }
}
