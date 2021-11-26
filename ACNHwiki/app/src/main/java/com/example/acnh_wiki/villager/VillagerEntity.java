package com.example.acnh_wiki.villager;

import com.example.acnh_wiki.ObjectNames;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VillagerEntity implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("file-name")
    private String fileName;
    @SerializedName("name")
    private ObjectNames name;
    @SerializedName("personality")
    private String personality;
    @SerializedName("birthday-string")
    private String birthdayString;
    @SerializedName("birthday")
    private String birthday;
    @SerializedName("species")
    private String species;
    @SerializedName("gender")
    private String gender;
    @SerializedName("hobby")
    private String hobby;
    @SerializedName("catch-phrase")
    private String catchPhrase;
    @SerializedName("icon_uri")
    private String iconUri;
    @SerializedName("image_uri")
    private String imageUri;
    @SerializedName("bubble-color")
    private String bubbleColor;
    @SerializedName("text-color")
    private String textColor;
    @SerializedName("saying")
    private String saying;

    public VillagerEntity(int id, String fileName, ObjectNames name, String personality,
                          String birthdayString, String birthday, String species, String gender,
                          String hobby, String catchPhrase, String iconUri, String imageUri,
                          String bubbleColor, String textColor, String saying) {
        this.id = id;
        this.fileName = fileName;
        this.name = name;
        this.personality = personality;
        this.birthdayString = birthdayString;
        this.birthday = birthday;
        this.species = species;
        this.gender = gender;
        this.hobby = hobby;
        this.catchPhrase = catchPhrase;
        this.iconUri = iconUri;
        this.imageUri = imageUri;
        this.bubbleColor = bubbleColor;
        this.textColor = textColor;
        this.saying = saying;
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

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getBirthdayString() {
        return birthdayString;
    }

    public void setBirthdayString(String birthdayString) {
        this.birthdayString = birthdayString;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getIconUri() {
        return iconUri;
    }

    public void setIconUri(String iconUri) {
        this.iconUri = iconUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getBubbleColor() {
        return bubbleColor;
    }

    public void setBubbleColor(String bubbleColor) {
        this.bubbleColor = bubbleColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }
}
