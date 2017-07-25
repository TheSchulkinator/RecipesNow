package com.example.theschulk.recipesnow.RecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theschulk.recipesnow.Models.RecipeModel;
import com.example.theschulk.recipesnow.Models.StepModel;
import com.example.theschulk.recipesnow.R;

import java.util.List;

public class RecipeShortDescriptionListRecyclerViewAdapter extends RecyclerView.Adapter<RecipeShortDescriptionListRecyclerViewAdapter.RecipeStepListViewHolder> {

    RecipeModel mCurrentRecipeModel;

    @Override
    public RecipeStepListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForRecycleView = R.layout.recipe_short_description_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutIdForRecycleView, parent, shouldAttachToParent);
        return new RecipeStepListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeStepListViewHolder stepListViewHolder, int position) {
        List<StepModel> currentRecipeSteps = mCurrentRecipeModel.getSteps();
        StepModel currentRecipeStepStepModel = currentRecipeSteps.get(position);

        stepListViewHolder.mRecipeStepList.setText(currentRecipeStepStepModel.getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (mCurrentRecipeModel == null) return  0;
        List<StepModel> recipeStepLength = mCurrentRecipeModel.getSteps();
        return recipeStepLength.size();
    }

    public class RecipeStepListViewHolder extends RecyclerView.ViewHolder{
        TextView mRecipeStepList;

        public RecipeStepListViewHolder(View view){
            super(view);
            mRecipeStepList = (TextView) view.findViewById(R.id.tv_recipe_step_list);
        }
    }

    public void setCurrentRecipeModel(RecipeModel currentRecipe){
        mCurrentRecipeModel = currentRecipe;
        notifyDataSetChanged();
    }
}
