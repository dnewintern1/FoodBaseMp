package com.base.foodbasemp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.base.foodbasemp.Listeners.RecipeClickListener;
import com.base.foodbasemp.adapter.RandomRecipeAdapter;
import com.base.foodbasemp.Listeners.RandomRecipeResponseListener;
import com.base.foodbasemp.model.RandomRecipeApiResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;
    RequestManager manager;
    RandomRecipeAdapter randomRecipeAdapter;
    RecyclerView recyclerView;

    Spinner spinner;
    List<String> tags =   new ArrayList<>();

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog = new ProgressDialog(this);
                dialog.setTitle("Loading...");


                searchView = findViewById(R.id.searchView_home);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        tags.clear();
                        tags.add(query);
                        manager.getRandomRecipes(randomRecipeResponseListner, tags);
                        dialog.show();
                        return true;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        return false;
                    }
                });

                spinner = findViewById(R.id.spinner_Tags);
                ArrayAdapter arrayAdapter=  ArrayAdapter.createFromResource(
                        this,
                        R.array.tags,
                        R.layout.spinner_text
                );
                arrayAdapter.setDropDownViewResource((R.layout.spinner_innertext));
                spinner.setAdapter(arrayAdapter);
                spinner.setOnItemSelectedListener(spinnerSelectedListner);


        manager= new RequestManager(this);
      //  manager.getRandomRecipes(randomRecipeResponseListner);
      // dialog.show();
    }

    private final RandomRecipeResponseListener randomRecipeResponseListner = new RandomRecipeResponseListener() {
        @Override
        public void didFetch(RandomRecipeApiResponse response, String message) {
            dialog.dismiss();
            recyclerView = findViewById(R.id.recycler_random);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1 ));
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes,recipeClickListener);
            recyclerView.setAdapter(randomRecipeAdapter);
     //       randomRecipeAdapter.notifyDataSetChanged();


        }

        @Override
        public void didError(String message) {

            Toast.makeText(MainActivity.this, "Error feteching data", Toast.LENGTH_SHORT).show();

        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListner = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            tags.clear();
            tags.add(adapterView.getSelectedItem().toString());
            manager.getRandomRecipes(randomRecipeResponseListner,tags);
            dialog.show();


        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {

           // Toast.makeText(MainActivity.this, id, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, RecipeDetailsActivity.class)
                    .putExtra("id",id));


        }
    };
}