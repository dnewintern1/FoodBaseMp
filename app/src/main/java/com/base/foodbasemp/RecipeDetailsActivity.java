package com.base.foodbasemp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.foodbasemp.Listeners.InstructionsListener;
import com.base.foodbasemp.Listeners.RecipeClickListener;
import com.base.foodbasemp.Listeners.RecipeDetailListener;
import com.base.foodbasemp.Listeners.SimilarRecipesListener;
import com.base.foodbasemp.adapter.IngredientsAdapter;
import com.base.foodbasemp.adapter.InstructionsAdapter;
import com.base.foodbasemp.adapter.SimilarRecipeAdapter;
import com.base.foodbasemp.model.InstructionsResponse;
import com.base.foodbasemp.model.RecipeDetailsResponse;
import com.base.foodbasemp.model.RecipeDetailsResponse;
import com.base.foodbasemp.model.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Response;

public class RecipeDetailsActivity extends AppCompatActivity {

    int id =0 ;
    TextView textView_meal_name , textView_meal_source , textView_meal_summary;
    ImageView   imageView_meal_image ;
    RecyclerView recycler_meal_ingredient , recycler_meal_similar, recycler_meal_instructions;

    RequestManager manager;

    ProgressDialog dialog;

    IngredientsAdapter ingredientsAdapter;

    SimilarRecipeAdapter similarRecipeAdapter;

    InstructionsAdapter instructionsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        findViews();



        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailListener, id);
        manager.getSimilarRecipes(similarRecipesListener, id);
        manager.getInstructions(instructionsListener , id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading details");
       dialog.show();
    }

    private void findViews() {
        textView_meal_name = findViewById(R.id.textView_meal_name);
        textView_meal_source = findViewById(R.id.textView_meal_source);
        textView_meal_summary = findViewById(R.id.textView_meal_summary);
        imageView_meal_image = findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredient= findViewById(R.id.recycler_meal_ingredient);
        recycler_meal_similar = findViewById(R.id.recycler_meal_similar);
        recycler_meal_instructions = findViewById(R.id.recycler_meal_instructions);
    }

    private final RecipeDetailListener recipeDetailListener = new RecipeDetailListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {

            dialog.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            textView_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);
            recycler_meal_ingredient.setHasFixedSize(true);
            recycler_meal_ingredient.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL));
            ingredientsAdapter= new IngredientsAdapter(RecipeDetailsActivity.this, response.extendedIngredients);
            recycler_meal_ingredient.setAdapter(ingredientsAdapter);

        }

        @Override
        public void didError(String message) {

            Toast.makeText(RecipeDetailsActivity.this, "Error Fetching Data", Toast.LENGTH_SHORT).show();

        }
    };

    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {

            recycler_meal_similar.setHasFixedSize(true);
            recycler_meal_similar.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this, LinearLayoutManager.HORIZONTAL, false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailsActivity.this,response,recipeClickListener);
            recycler_meal_similar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {

            Toast.makeText(RecipeDetailsActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private final RecipeClickListener recipeClickListener= new RecipeClickListener() {
        @Override
        public void onRecipeClicked(String id) {

            startActivity(new Intent(RecipeDetailsActivity.this, RecipeDetailsActivity.class)

                    .putExtra("id", id));




        }
    };

    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {

            recycler_meal_instructions.setHasFixedSize(true);
            recycler_meal_instructions.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
           instructionsAdapter = new InstructionsAdapter(RecipeDetailsActivity.this , response);
            recycler_meal_instructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this, "ERROOOTT", Toast.LENGTH_SHORT).show();

        }
    };
}