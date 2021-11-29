package com.example.acnh_wiki.song;

import com.example.acnh_wiki.ObjectNames;
import com.example.acnh_wiki.art.ArtAdapter;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SongEntity implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("file-name")
    private String fileName;
    @SerializedName("name")
    private ObjectNames name;
    @SerializedName("buy-price")
    private String byePrice;
    @SerializedName("sell-price")
    private String sellPrice;
    @SerializedName("isOrderable")
    private String isOrderable;
    @SerializedName("music_uri")
    private String musicUri;
    @SerializedName("image_uri")
    private String imageUri;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public ObjectNames getName() {
        return name;
    }

    public void setName(ObjectNames name) {
        this.name = name;
    }

    public String getByePrice() {
        return byePrice;
    }

    public void setByePrice(String byePrice) {
        this.byePrice = byePrice;
    }

    public String getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(String sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getIsOrderable() {
        return isOrderable;
    }

    public void setIsOrderable(String isOrderable) {
        this.isOrderable = isOrderable;
    }

    public String getMusicUri() {
        return musicUri;
    }

    public void setMusicUri(String musicUri) {
        this.musicUri = musicUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
