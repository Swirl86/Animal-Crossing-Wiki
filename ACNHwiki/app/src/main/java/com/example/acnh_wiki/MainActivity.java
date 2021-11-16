package com.example.acnh_wiki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.acnh_wiki.villager.ApiInterface;
import com.example.acnh_wiki.villager.VillagerActivity;
import com.example.acnh_wiki.villager.VillagerAdapter;
import com.example.acnh_wiki.villager.VillagerEntity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private List<VillagerEntity> villagerList;
    private VillagerAdapter.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        apiInterface.getVillagers().enqueue(new Callback<List<VillagerEntity>>() {
            @Override
            public void onResponse(Call<List<VillagerEntity>> call, Response<List<VillagerEntity>> response) {

                if(!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, response.code(), Toast.LENGTH_LONG).show();
                }

                villagerList = response.body();
                VillagerAdapter adapter = new VillagerAdapter(villagerList, MainActivity.this, listener);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<VillagerEntity>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void init() {
        setOnClickListener();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
    }

    private void setOnClickListener() {
        listener = new VillagerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), VillagerActivity.class);
                intent.putExtra("villager", villagerList.get(position));
                startActivity(intent);
            }
        };
    }
}