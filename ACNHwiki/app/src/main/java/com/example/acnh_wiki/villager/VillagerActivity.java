package com.example.acnh_wiki.villager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.acnh_wiki.R;
import com.example.acnh_wiki.Utils;
import com.squareup.picasso.Picasso;

public class VillagerActivity extends AppCompatActivity {
    TextView name , species , personality , saying, birthdayString;
    TextView gender, hobby, catchPhrase, chatBubble;
    ImageView imageView;
    CardView villagerCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_villager);

        init();

        Intent intent = getIntent();
        VillagerEntity villager = (VillagerEntity) intent.getExtras().getSerializable("villager");

        Picasso.with(this).load(villager.getImageUri()).into(imageView);
        villagerCard.setCardBackgroundColor(Color.parseColor(villager.getBubbleColor()));

        int textColor = Color.parseColor(villager.getTextColor());

        name.setText(villager.getName().getNameEUen());
        name.setTextColor(textColor);

        personality.setText(villager.getPersonality());
        personality.setTextColor(textColor);

        gender.setText(villager.getGender());
        gender.setTextColor(textColor);

        species.setText(villager.getSpecies());
        species.setTextColor(textColor);

        birthdayString.setText(villager.getBirthdayString());
        birthdayString.setTextColor(textColor);

        hobby.setText(villager.getHobby());
        hobby.setTextColor(textColor);
        String phrase = Utils.capitalizeString(villager.getCatchPhrase());
        catchPhrase.setText(phrase);
        catchPhrase.setTextColor(textColor);

        chatBubble.setText(phrase);

        String sayingQuotes = "\"" + villager.getSaying() + "\"";
        saying.setText(sayingQuotes);
        saying.setTextColor(textColor);


        int darkerColor = darkenColor(textColor);
        TextView title = findViewById(R.id.villager_detail_personality_title);
        title.setTextColor(darkerColor);
        title = findViewById(R.id.villager_detail_gender_title);
        title.setTextColor(darkerColor);
        title = findViewById(R.id.villager_detail_species_title);
        title.setTextColor(darkerColor);
        title = findViewById(R.id.villager_detail_birthdaystr_title);
        title.setTextColor(darkerColor);
        title = findViewById(R.id.villager_detail_hobby_title);
        title.setTextColor(darkerColor);
        title = findViewById(R.id.villager_detail_catchPhrase_title);
        title.setTextColor(darkerColor);
        title = findViewById(R.id.villager_detail_saying_title);
        title.setTextColor(darkerColor);
    }

    private void init() {
        imageView = findViewById(R.id.villager_detail_image);
        name = findViewById(R.id.villager_detail_name);
        villagerCard = findViewById(R.id.villager_detail_card);

        personality = findViewById(R.id.villager_detail_personality);
        gender = findViewById(R.id.villager_detail_gender);
        species = findViewById(R.id.villager_detail_species);
        birthdayString = findViewById(R.id.villager_detail_birthdaystr);
        hobby = findViewById(R.id.villager_detail_hobby);
        catchPhrase = findViewById(R.id.villager_detail_catchPhrase);
        saying = findViewById(R.id.villager_detail_saying);
        chatBubble = findViewById(R.id.chat_bubble_text);
    }

    @ColorInt
    int darkenColor(@ColorInt int color) {
        float ratio = 1.0f;
        int a = (color >> 24) & 0xFF;
        int r = (int) (((color >> 16) & 0xFF) * ratio);
        int g = (int) (((color >> 8) & 0xFF) * ratio);
        int b = (int) ((color & 0xFF) * ratio);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
