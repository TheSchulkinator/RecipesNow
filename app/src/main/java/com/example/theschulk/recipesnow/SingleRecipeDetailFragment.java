package com.example.theschulk.recipesnow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.theschulk.recipesnow.Models.RecipeModel;
import com.example.theschulk.recipesnow.RecyclerViewAdapters.RecipeStepListRecyclerViewAdapter;

import java.io.Serializable;

/**
 * A fragment representing a single RecipeDetail detail screen.
 * This fragment is either contained in a {@link RecipeDetailListActivity}
 * in two-pane mode (on tablets) or a {@link SingleRecipeDetailActivity}
 * on handsets.
 */
public class SingleRecipeDetailFragment extends Fragment {

    RecipeModel mCurrentRecipeModel;
    private RecyclerView mStepDescriptionListRecyclerView;
    private RecipeStepListRecyclerViewAdapter mStepDescriptionListAdapter;

    public SingleRecipeDetailFragment() {
    }

    public static SingleRecipeDetailFragment newInstance (Context context, RecipeModel currentRecipe){
        SingleRecipeDetailFragment singleRecipeDetailFragment = new SingleRecipeDetailFragment();
        Bundle recipeBundle = new Bundle();
        recipeBundle.putSerializable(context.getString(R.string.current_recipe_bundle), (Serializable) currentRecipe);
        singleRecipeDetailFragment.setArguments(recipeBundle);
        return singleRecipeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(getString(R.string.current_recipe_bundle))) {
            mCurrentRecipeModel = (RecipeModel) getArguments().getSerializable(getString(R.string.current_recipe_bundle));

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.single_recipe_step_detail_fragment, container, false);

        if (mCurrentRecipeModel != null) {
            mStepDescriptionListAdapter = new RecipeStepListRecyclerViewAdapter();
            mStepDescriptionListAdapter.setCurrentRecipeModel(mCurrentRecipeModel);
            mStepDescriptionListRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe_step_list);
            mStepDescriptionListRecyclerView.setAdapter(mStepDescriptionListAdapter);
        }

        return rootView;
    }
}
