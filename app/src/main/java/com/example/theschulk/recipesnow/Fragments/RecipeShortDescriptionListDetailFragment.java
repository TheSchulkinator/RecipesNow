package com.example.theschulk.recipesnow.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.theschulk.recipesnow.Models.RecipeModel;
import com.example.theschulk.recipesnow.Models.StepModel;
import com.example.theschulk.recipesnow.R;
import com.example.theschulk.recipesnow.RecipeDescriptionListActivity;
import com.example.theschulk.recipesnow.RecyclerViewAdapters.RecipeShortDescriptionListRecyclerViewAdapter;

public class RecipeShortDescriptionListDetailFragment extends Fragment implements RecipeShortDescriptionListRecyclerViewAdapter.RecipeDetailClickHandler{

    RecipeModel mCurrentRecipeModel;
    private RecyclerView mStepDescriptionListRecyclerView;
    private RecipeShortDescriptionListRecyclerViewAdapter mStepDescriptionListAdapter;

    public RecipeShortDescriptionListDetailFragment() {
    }

    public static RecipeShortDescriptionListDetailFragment newInstance (Context context, RecipeModel currentRecipe){
        RecipeShortDescriptionListDetailFragment recipeShortDescriptionListDetailFragment = new RecipeShortDescriptionListDetailFragment();
        Bundle recipeBundle = new Bundle();
        recipeBundle.putSerializable(context.getString(R.string.current_recipe_bundle), currentRecipe);
        recipeShortDescriptionListDetailFragment.setArguments(recipeBundle);
        return recipeShortDescriptionListDetailFragment;
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
        View rootView = inflater.inflate(R.layout.single_recipe_short_description_detail_fragment, container, false);

        if (mCurrentRecipeModel != null) {
            mStepDescriptionListAdapter = new RecipeShortDescriptionListRecyclerViewAdapter(this);
            mStepDescriptionListRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe_step_list);
            mStepDescriptionListRecyclerView.setAdapter(mStepDescriptionListAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
            mStepDescriptionListRecyclerView.setLayoutManager(linearLayoutManager);
            mStepDescriptionListAdapter.setCurrentRecipeModel(mCurrentRecipeModel);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mStepDescriptionListRecyclerView.getContext(),
                    linearLayoutManager.getOrientation());
            mStepDescriptionListRecyclerView.addItemDecoration(dividerItemDecoration);
        }

        return rootView;
    }

    @Override
    public void onRecipeDetailClick(StepModel stepModel) {
        Context context = getActivity();
        Fragment recipeSingleStepInstructions = RecipeSingleStepInstructionsFragment.newInstance(context, stepModel);
        RecipeDescriptionListActivity recipeShortDescriptionListActivity = (RecipeDescriptionListActivity) context;
        recipeShortDescriptionListActivity.SwapFragment(recipeSingleStepInstructions);
    }
}
