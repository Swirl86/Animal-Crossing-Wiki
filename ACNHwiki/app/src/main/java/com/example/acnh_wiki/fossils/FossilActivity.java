package com.example.acnh_wiki.fossils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acnh_wiki.R;
import com.example.acnh_wiki.Utils;
import com.example.acnh_wiki.art.ArtEntity;
import com.squareup.picasso.Picasso;

public class FossilActivity extends AppCompatActivity {

    TextView name , sellPrice, partOf, partOfDescription, description;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fossil);

        init();

        Intent intent = getIntent();
        FossilEntity fossil = (FossilEntity) intent.getExtras().getSerializable("fossil");
        String relatedParts = intent.getExtras().getString("relatedParts");

        Picasso.with(this).load(fossil.getImageUri()).into(imageView);
        String fossilName = Utils.capitalizeString(fossil.getName().getNameEUen());
        name.setText(fossilName);

        String text = fossil.getPrice() + " \uD83D\uDCB0";
        sellPrice.setText(text);

        text = Utils.capitalizeString(fossil.getPartOf());

        if(text.equals(fossilName)) { // If the fossil partOf is the same as it's name then there is only one part
            partOf.setText("None");
            text = "This is a stand-alone fossil";
            partOfDescription.setText(text);
        } else {
            partOf.setText(text);
            partOfDescription.setText(relatedParts);
        }

        text = "\"" + fossil.getMuseumPhrase() +"\"";
        description.setText(text);
    }

    private void init() {
        imageView = findViewById(R.id.fossil_detail_image);
        name = findViewById(R.id.fossil_detail_name);
        sellPrice = findViewById(R.id.fossil_detail_sell_price);
        partOf = findViewById(R.id.fossil_detail_part_of);
        partOfDescription = findViewById(R.id.fossil_detail_part_of_description);
        description = findViewById(R.id.fossil_detail_description);
    }

}