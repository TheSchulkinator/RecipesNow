package com.example.theschulk.recipesnow;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RemoteViews;

import com.example.theschulk.recipesnow.Data.RecipeEndpointInterface;
import com.example.theschulk.recipesnow.Data.RetrofitUtils;
import com.example.theschulk.recipesnow.Models.IngredientModel;
import com.example.theschulk.recipesnow.Models.RecipeModel;
import com.example.theschulk.recipesnow.RecyclerViewAdapters.RecipeListRecyclerViewAdapter;
import com.example.theschulk.recipesnow.Utilities.RecipeIngredientBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeListActivity extends AppCompatActivity implements RecipeListRecyclerViewAdapter.RecipeListClickHandler {

    List<RecipeModel> currentRecipes;
    List<IngredientModel> ingredientModelList;
    private RecipeListRecyclerViewAdapter mRecipeViewAdapter;
    private RecyclerView mRecipeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        mRecipeRecyclerView = (RecyclerView) findViewById(R.id.rv_recipe_list);
        mRecipeViewAdapter = new RecipeListRecyclerViewAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecipeRecyclerView.setAdapter(mRecipeViewAdapter);
        mRecipeRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecipeRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        mRecipeRecyclerView.addItemDecoration(dividerItemDecoration);

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

    @Override
    public void onClick(RecipeModel selectedRecipeModel) {
        Context context = this;
        ingredientModelList = selectedRecipeModel.getIngredients();
        String ingredientList = RecipeIngredientBuilder.IngredientListIngredientBuilder(ingredientModelList);
        String ingredientQuantity = RecipeIngredientBuilder.IngredientListQuantityBuilder(ingredientModelList);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.widget_ingredient_key), ingredientList);
        editor.putString(getString(R.string.widget_quantity_key), ingredientQuantity);
        editor.commit();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.recipe_ingredient_list_widget);
        ComponentName thisWidget = new ComponentName(context, RecipeIngredientListWidget.class);
        remoteViews.setTextViewText(R.id.appwidget_text, ingredientList);
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);

        Class destinationClass = RecipeDescriptionListActivity.class;
        Intent passedInIntent = new Intent(context, destinationClass);
        passedInIntent.putExtra("Selected Recipe", selectedRecipeModel);
        startActivity(passedInIntent);
    }
}
