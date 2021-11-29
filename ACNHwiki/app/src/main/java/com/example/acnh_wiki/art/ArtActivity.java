package com.example.acnh_wiki.art;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acnh_wiki.R;
import com.example.acnh_wiki.Utils;
import com.squareup.picasso.Picasso;

public class ArtActivity extends AppCompatActivity {
    TextView name , sellPrice , buyPrice , fake, description;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art);

        init();

        Intent intent = getIntent();
        ArtEntity art = (ArtEntity) intent.getExtras().getSerializable("art");
        Picasso.with(this).load(art.getImageUri()).into(imageView);

        name.setText(Utils.capitalizeString(art.getName().getNameEUen()));

        String text = art.getSellPrice() + " \uD83D\uDCB0";
        sellPrice.setText(text);

        text = art.getByePrice() + " \uD83D\uDCB0";
        buyPrice.setText(text);

        text = art.getHasFake().equals("true") ?
                "\u2757 This artwork has a fake \u2757" : "This artwork is always genuine";
        fake.setText(text);

        text = "\"" + art.getMuseumDesc() +"\"";
        description.setText(text);
    }

    private void init() {
        imageView = findViewById(R.id.art_detail_image);
        name = findViewById(R.id.art_detail_name);
        sellPrice = findViewById(R.id.art_detail_sell_price);
        buyPrice = findViewById(R.id.art_detail_buy_price);
        fake = findViewById(R.id.art_detail_fake);
        description = findViewById(R.id.art_detail_description);
    }
}