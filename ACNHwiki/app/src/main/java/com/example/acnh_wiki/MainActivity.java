package com.example.acnh_wiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.acnh_wiki.villager.ApiInterface;
import com.example.acnh_wiki.villager.MainVillagerActivity;
import com.example.acnh_wiki.villager.VillagerActivity;
import com.example.acnh_wiki.villager.VillagerAdapter;
import com.example.acnh_wiki.villager.VillagerEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.go_to_villager_list);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainVillagerActivity.class);
                startActivity(intent);
            }
        });
    }

}