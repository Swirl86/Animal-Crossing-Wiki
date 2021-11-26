package com.example.acnh_wiki;

import com.example.acnh_wiki.art.ArtEntity;
import com.example.acnh_wiki.villager.VillagerEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//Interface for end points
public interface ApiInterface {

    @GET("villagers")
    Call<List<VillagerEntity>> getVillagers();

    @GET("art")
    Call<List<ArtEntity>> getArts();

    /*@GET("fish")
    Call<List<VillagerEntity>> getFishes();

    @GET("bugs")
    Call<List<VillagerEntity>> getBugs();

    @GET("fossils")
    Call<List<VillagerEntity>> getFossils();

    @GET("songs")
    Call<List<VillagerEntity>> getSongs();

    @GET("houseware")
    Call<List<VillagerEntity>> getHousewares();

    @GET("wallmounted")
    Call<List<VillagerEntity>> getwallmounted();

    @GET("misc")
    Call<List<VillagerEntity>> getMisc();*/

}