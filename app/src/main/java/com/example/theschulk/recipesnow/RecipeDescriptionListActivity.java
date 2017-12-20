package com.example.theschulk.recipesnow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.theschulk.recipesnow.Fragments.RecipeShortDescriptionListDetailFragment;
import com.example.theschulk.recipesnow.Fragments.RecipeSingleStepInstructionsFragment;
import com.example.theschulk.recipesnow.Models.IngredientModel;
import com.example.theschulk.recipesnow.Models.RecipeModel;
import com.example.theschulk.recipesnow.Models.StepModel;
import com.example.theschulk.recipesnow.RecyclerViewAdapters.RecipeShortDescriptionListRecyclerViewAdapter;
import com.example.theschulk.recipesnow.Utilities.RecipeIngredientBuilder;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.example.theschulk.recipesnow.Fragments.IngredientFragment.*;

public class
RecipeDescriptionListActivity extends AppCompatActivity implements RecipeShortDescriptionListRecyclerViewAdapter.RecipeDetailClickHandler{

    RecipeModel passedInRecipeModel;
    Boolean mTwoPane;
    private RecyclerView mStepDescriptionListRecyclerView;
    private RecipeShortDescriptionListRecyclerViewAdapter mStepDescriptionListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_short_description_list);

        Intent intentThatStartedTheActivity = getIntent();

        if (intentThatStartedTheActivity.hasExtra(getString(R.string.current_recipe_bundle))) {
            passedInRecipeModel = (RecipeModel) intentThatStartedTheActivity.getSerializableExtra(getString(R.string.current_recipe_bundle));
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.fl_detail_instructions) != null)
        {
            mTwoPane = true;
        }else{
            mTwoPane = false;
        }



        if (savedInstanceState == null) {
            if(!mTwoPane) {
                Fragment RecipeStepFragment = RecipeShortDescriptionListDetailFragment.newInstance(this, passedInRecipeModel);
                Fragment IngredientFragment = IngredientNewInstance(this, passedInRecipeModel);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().
                        add(R.id.fl_recipe_ingredient, IngredientFragment, null).
                        add(R.id.fl_step_detail_fragment, RecipeStepFragment, null).
                        commit();
            } else {

                if (passedInRecipeModel != null) {
                    mStepDescriptionListAdapter = new RecipeShortDescriptionListRecyclerViewAdapter(this);
                    mStepDescriptionListRecyclerView = (RecyclerView) findViewById(R.id.rv_recipe_step_short_description);
                    mStepDescriptionListRecyclerView.setAdapter(mStepDescriptionListAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    mStepDescriptionListRecyclerView.setLayoutManager(linearLayoutManager);
                    mStepDescriptionListAdapter.setCurrentRecipeModel(passedInRecipeModel);

                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mStepDescriptionListRecyclerView.getContext(),
                            linearLayoutManager.getOrientation());
                    mStepDescriptionListRecyclerView.addItemDecoration(dividerItemDecoration);
                }

                List<StepModel> startingRecipeStepModel = passedInRecipeModel.getSteps();
                StepModel firstRecipeStep = startingRecipeStepModel.get(0);

                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment recipeStepInstructionFragment = RecipeSingleStepInstructionsFragment.newInstance(this, firstRecipeStep);
                fragmentManager.beginTransaction().
                        add(R.id.fl_detail_instructions, recipeStepInstructionFragment).
                        commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, RecipeListActivity.class));
            return true;
        } else if (id == R.menu.main){
        }
        return super.onOptionsItemSelected(item);
    }

    public void SwapFragment(Fragment recipeSingleStepFragment){
        if(!mTwoPane){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_step_detail_fragment, recipeSingleStepFragment);
        transaction.addToBackStack(null);
        transaction.commit();
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_detail_instructions, recipeSingleStepFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }

    @Override
    public void onRecipeDetailClick(StepModel stepModel) {
        Fragment recipeSingleStepInstructions = RecipeSingleStepInstructionsFragment.newInstance(this, stepModel);
        SwapFragment(recipeSingleStepInstructions);
    }


}
