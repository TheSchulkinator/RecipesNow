package com.example.theschulk.recipesnow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.example.theschulk.recipesnow.Fragments.RecipeShortDescriptionListDetailFragment;
import com.example.theschulk.recipesnow.Models.RecipeModel;
import com.example.theschulk.recipesnow.RecyclerViewAdapters.RecipeShortDescriptionListRecyclerViewAdapter;

/**
 * An activity representing a single RecipeDetail detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link RecipeDetailListActivity}.
 */
public class RecipeShortDescriptionListActivity extends AppCompatActivity{

    RecipeModel passedInRecipeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_short_description_list);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //

        Intent intentThatStartedTheActivity = getIntent();

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            if (intentThatStartedTheActivity.hasExtra(getString(R.string.current_recipe_bundle))) {
                passedInRecipeModel = (RecipeModel) intentThatStartedTheActivity.getSerializableExtra(getString(R.string.current_recipe_bundle));

                Fragment RecipeStepFragment = RecipeShortDescriptionListDetailFragment.newInstance(this, passedInRecipeModel);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().
                        add(R.id.fl_step_detail_fragment, RecipeStepFragment, null).
                        commit();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, RecipeDetailListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void SwapFragment(Fragment recipeSingleStepFragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_step_detail_fragment, recipeSingleStepFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
