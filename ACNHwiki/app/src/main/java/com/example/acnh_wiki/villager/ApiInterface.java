package com.example.acnh_wiki.villager;

import com.example.acnh_wiki.villager.VillagerEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Interface for end points
public interface ApiInterface {

    @GET("villagers")
    Call<List<VillagerEntity>> getVillagers();
}