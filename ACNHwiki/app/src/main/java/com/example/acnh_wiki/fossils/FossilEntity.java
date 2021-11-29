package com.example.acnh_wiki.fossils;

import com.example.acnh_wiki.ObjectNames;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FossilEntity implements Serializable  {
    @SerializedName("id")
    private int id;
    @SerializedName("file-name")
    private String fileName;
    @SerializedName("name")
    private ObjectNames name;
    @SerializedName("price")
    private String price;
    @SerializedName("museum-phrase")
    private String museumPhrase;
    @SerializedName("image_uri")
    private String imageUri;
    @SerializedName("part-of")
    private String partOf;

    public FossilEntity(int id, String fileName, ObjectNames name, String price, String museumPhrase, String imageUri, String partOf) {
        this.id = id;
        this.fileName = fileName;
        this.name = name;
        this.price = price;
        this.museumPhrase = museumPhrase;
        this.imageUri = imageUri;
        this.partOf = partOf;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMuseumPhrase() {
        return museumPhrase;
    }

    public void setMuseumPhrase(String museumPhrase) {
        this.museumPhrase = museumPhrase;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getPartOf() {
        return partOf;
    }

    public void setPartOf(String partOf) {
        this.partOf = partOf;
    }
}
