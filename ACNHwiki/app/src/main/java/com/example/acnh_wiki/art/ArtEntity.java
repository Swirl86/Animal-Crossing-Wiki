package com.example.acnh_wiki.art;

import com.example.acnh_wiki.ObjectNames;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ArtEntity implements Serializable {
    @SerializedName("id")
    private int id;
    @SerializedName("file-name")
    private String fileName;
    @SerializedName("name")
    private ObjectNames name;
    @SerializedName("hasFake")
    private String hasFake;
    @SerializedName("buy-price")
    private String byePrice;
    @SerializedName("sell-price")
    private String sellPrice;
    @SerializedName("image_uri")
    private String imageUri;
    @SerializedName("museum-desc")
    private String museumDesc;

    public ArtEntity(int id, String fileName, ObjectNames name, String hasFake,
                     String byePrice, String sellPrice, String imageUri, String museumDesc) {
        this.id = id;
        this.fileName = fileName;
        this.name = name;
        this.hasFake = hasFake;
        this.byePrice = byePrice;
        this.sellPrice = sellPrice;
        this.imageUri = imageUri;
        this.museumDesc = museumDesc;
    }

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

    public String getHasFake() {
        return hasFake;
    }

    public void setHasFake(String hasFake) {
        this.hasFake = hasFake;
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

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getMuseumDesc() {
        return museumDesc;
    }

    public void setMuseumDesc(String museumDesc) {
        this.museumDesc = museumDesc;
    }
}
