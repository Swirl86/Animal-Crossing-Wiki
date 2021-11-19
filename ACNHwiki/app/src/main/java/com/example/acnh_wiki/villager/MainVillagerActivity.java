package com.example.acnh_wiki.villager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.acnh_wiki.MainActivity;
import com.example.acnh_wiki.R;
import com.example.acnh_wiki.RetrofitInstance;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainVillagerActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private List<VillagerEntity> villagerList;

    private VillagerAdapter adapter;
    private VillagerAdapter.RecyclerViewClickListener listener;

    private ProgressBar progressBar;
    private FloatingActionButton scrollUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_villager);


        init();

        apiInterface.getVillagers().enqueue(new Callback<List<VillagerEntity>>() {
            @Override
            public void onResponse(Call<List<VillagerEntity>> call, Response<List<VillagerEntity>> response) {

                if(!response.isSuccessful()) {
                    Toast.makeText(MainVillagerActivity.this, response.code(), Toast.LENGTH_LONG).show();
                }

                villagerList = response.body();
                adapter = new VillagerAdapter(villagerList, MainVillagerActivity.this, listener);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<VillagerEntity>> call, Throwable t) {
                Toast.makeText(MainVillagerActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int x, int y) {
                // y is the change in the vertical scroll position
                if (y < 0) {
                    //scroll up
                    scrollUp.setVisibility(View.GONE);
                } else if (y > 0) {
                    //scroll down
                    scrollUp.setVisibility(View.VISIBLE);
                }
            }
        });

        scrollUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Position 0 scroll to the beginning of recyclerView
                recyclerView.smoothScrollToPosition(0);
                scrollUp.setVisibility(View.GONE);
            }
        });

}

    private void init() {
        setOnClickListener();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);

        progressBar = findViewById(R.id.progress_bar_villager);

        scrollUp = findViewById(R.id.scroll_Up_villager);
        scrollUp.setVisibility(View.GONE);
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

    // Search filter handling
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}