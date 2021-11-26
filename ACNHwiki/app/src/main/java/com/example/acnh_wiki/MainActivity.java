package com.example.acnh_wiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.acnh_wiki.art.MainArtActivity;
import com.example.acnh_wiki.villager.MainVillagerActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton villagerButton = findViewById(R.id.go_to_villager_list);
        ImageButton artButton = findViewById(R.id.go_to_art_list);

        villagerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainVillagerActivity.class);
                startActivity(intent);
            }
        });

        artButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainArtActivity.class);
                startActivity(intent);
            }
        });
    }

}