package com.example.acnh_wiki;

import com.example.acnh_wiki.art.ArtEntity;
import com.example.acnh_wiki.fossils.FossilEntity;
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

    @GET("fossils")
    Call<List<FossilEntity>> getFossils();


    // TODO implement
    /*@GET("fish")
    Call<List<FishEntity>> getFishes();

    @GET("bugs")
    Call<List<BugEntity>> getBugs();

    @GET("songs")
    Call<List<SongEntity>> getSongs();

    @GET("houseware")
    Call<List<HousewareEntity>> getHousewares();

    @GET("wallmounted")
    Call<List<WallmountedEntity>> getwallmounted();

    @GET("misc")
    Call<List<MiscrEntity>> getMisc();*/

}