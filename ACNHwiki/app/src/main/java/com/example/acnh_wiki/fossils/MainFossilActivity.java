package com.example.acnh_wiki.fossils;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.acnh_wiki.ApiInterface;
import com.example.acnh_wiki.R;
import com.example.acnh_wiki.RetrofitInstance;
import com.example.acnh_wiki.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFossilActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private RecyclerView recyclerView;
    private List<FossilEntity> fossilsList;

    private FossilAdapter adapter;
    private FossilAdapter.RecyclerViewClickListener listener;

    private ProgressBar progressBar;
    private FloatingActionButton scrollUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fossil);

        init();

        apiInterface.getFossils().enqueue(new Callback<List<FossilEntity>>() {

            @Override
            public void onResponse(Call<List<FossilEntity>> call, Response<List<FossilEntity>> response) {

                if(!response.isSuccessful()) {
                    Toast.makeText(MainFossilActivity.this, response.code(), Toast.LENGTH_LONG).show();
                }

                fossilsList = response.body();
                adapter = new FossilAdapter(fossilsList, MainFossilActivity.this, listener);
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<FossilEntity>> call, Throwable t) {
                Toast.makeText(MainFossilActivity.this, t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int x, int y) {
                if (y < 0) {
                    scrollUp.setVisibility(View.GONE);
                } else if (y > 0) {
                    scrollUp.setVisibility(View.VISIBLE);
                }
            }
        });

        scrollUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(0);
                scrollUp.setVisibility(View.GONE);
            }
        });
    }
    private void init() {
        setOnClickListener();
        recyclerView = findViewById(R.id.fossil_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainFossilActivity.this));

        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);

        progressBar = findViewById(R.id.progress_bar_fossil);

        scrollUp = findViewById(R.id.scroll_Up_fossil);
        scrollUp.setVisibility(View.GONE);
    }

    private void setOnClickListener() {
        listener = new FossilAdapter.RecyclerViewClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), FossilActivity.class);
                Bundle extras = new Bundle();

                FossilEntity fossil = fossilsList.get(position);

                extras.putSerializable("fossil", fossil);

                long nrOfParts = fossilsList
                        .stream()
                        .filter(c -> c.getPartOf().equals(fossil.getPartOf()))
                        .count();

                String name = fossil.getName().getNameEUen();
                String relatedParts = "This is a multi-part fossil with " + nrOfParts + " parts\n" + "Search for " +
                        getFossilNameForPrintOut(name) +
                        " to see related parts";
                extras.putString("relatedParts", relatedParts);

                intent.putExtras(extras);
                startActivity(intent);
            }
        };
    }

    private String getFossilNameForPrintOut(String name) {
        String[] splitName = name.split(" ");
        return splitName.length > 2 ?
                Utils.capitalizeString(splitName[1]):
                Utils.capitalizeString(splitName[0]);
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