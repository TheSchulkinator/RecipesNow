package com.example.theschulk.recipesnow.RecyclerViewAdapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theschulk.recipesnow.Models.RecipeModel;
import com.example.theschulk.recipesnow.R;

/**
 * Created by gregs on 7/19/2017.
 */

public class RecipeSingleStepRecyclerViewAdapter extends RecyclerView.Adapter<RecipeSingleStepRecyclerViewAdapter.RecipeStepViewHolder> {

    @Override
    public RecipeStepViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecipeStepViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecipeStepViewHolder extends RecyclerView.ViewHolder{

        TextView singleRecipeStep;

        public RecipeStepViewHolder(View view){
            super(view);
        }
    }
}
