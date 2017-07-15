package com.example.theschulk.recipesnow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.theschulk.recipesnow.Data.RecipeEndpointInterface;
import com.example.theschulk.recipesnow.Data.RetrofitUtils;
import com.example.theschulk.recipesnow.Models.RecipeModel;
import com.example.theschulk.recipesnow.RecyclerViewAdapters.RecipeListRecyclerViewAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends AppCompatActivity {

    List<RecipeModel> currentRecipes;
    private RecipeListRecyclerViewAdapter mRecipeViewAdapter;
    private RecyclerView mRecipeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mRecipeRecyclerView = (RecyclerView) findViewById(R.id.rv_recipe_list);
        mRecipeViewAdapter = new RecipeListRecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecipeRecyclerView.setAdapter(mRecipeViewAdapter);
        mRecipeRecyclerView.setLayoutManager(linearLayoutManager);

        loadRecipes();
    }

    public void loadRecipes(){
        RecipeEndpointInterface recipeEndpointInterface = RetrofitUtils.getRecipeEndpointInterface();
        recipeEndpointInterface.getRecipes().enqueue(new Callback<List<RecipeModel>>() {
            @Override
            public void onResponse(Call<List<RecipeModel>> call, Response<List<RecipeModel>> response) {
                currentRecipes = response.body();
                mRecipeViewAdapter.setRecipeListIntoRecyclerView(currentRecipes);
            }

            @Override
            public void onFailure(Call<List<RecipeModel>> call, Throwable t) {

            }
        });
    }


}
