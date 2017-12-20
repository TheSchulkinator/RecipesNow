package com.example.theschulk.recipesnow.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theschulk.recipesnow.Models.IngredientModel;
import com.example.theschulk.recipesnow.Models.RecipeModel;
import com.example.theschulk.recipesnow.R;
import com.example.theschulk.recipesnow.RecyclerViewAdapters.IngredientRecyclerViewAdapter;

import java.util.List;

public class IngredientFragment extends Fragment{

    private RecipeModel mCurrentRecipeModel;
    private RecyclerView ingredientRecyclerView;
    private IngredientRecyclerViewAdapter ingredientAdapter;
    List<IngredientModel> CurrentIngredientModel;

    public IngredientFragment(){}

    public static IngredientFragment IngredientNewInstance (Context context, RecipeModel recipeModel){
        IngredientFragment ingredientFragment = new IngredientFragment();
        Bundle recipeBundle = new Bundle();
        recipeBundle.putSerializable(context.getString(R.string.current_recipe_bundle), recipeModel);
        ingredientFragment.setArguments(recipeBundle);
        return ingredientFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(getString(R.string.current_recipe_bundle))) {
            mCurrentRecipeModel = (RecipeModel) getArguments().getSerializable(getString(R.string.current_recipe_bundle));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredient_fragment, container, false);

        if(mCurrentRecipeModel != null){
            CurrentIngredientModel = mCurrentRecipeModel.getIngredients();

            ingredientAdapter = new IngredientRecyclerViewAdapter();
            ingredientRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_ingredient_fragment);
            ingredientRecyclerView.setAdapter(ingredientAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
            ingredientRecyclerView.setLayoutManager(linearLayoutManager);
            ingredientAdapter.setCurrentIngredientModel(CurrentIngredientModel);

        }

        return rootView;
    }
}
