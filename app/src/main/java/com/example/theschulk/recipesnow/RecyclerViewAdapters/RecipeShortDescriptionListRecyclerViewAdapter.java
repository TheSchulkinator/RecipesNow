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
    StepModel mCurrentRecipeStepStepModel;
    private RecipeDetailClickHandler mRecipeDetailClickHandler;

    public RecipeShortDescriptionListRecyclerViewAdapter(RecipeDetailClickHandler recipeDetailClickHandler){
        mRecipeDetailClickHandler = recipeDetailClickHandler;
    }

    public interface RecipeDetailClickHandler{
        void onRecipeDetailClick(StepModel stepModel);
    }

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
        mCurrentRecipeStepStepModel = currentRecipeSteps.get(position);

        stepListViewHolder.mRecipeStepList.setText(mCurrentRecipeStepStepModel.getShortDescription());
    }

    @Override
    public int getItemCount() {
        if (mCurrentRecipeModel == null) return  0;
        List<StepModel> recipeStepLength = mCurrentRecipeModel.getSteps();
        return recipeStepLength.size();
    }

    public class RecipeStepListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mRecipeStepList;

        public RecipeStepListViewHolder(View view){
            super(view);
            mRecipeStepList = (TextView) view.findViewById(R.id.tv_recipe_step_list);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            List<StepModel> currentRecipeSteps = mCurrentRecipeModel.getSteps();
            mCurrentRecipeStepStepModel = currentRecipeSteps.get(position);
            mRecipeDetailClickHandler.onRecipeDetailClick(mCurrentRecipeStepStepModel);
        }
    }

    public void setCurrentRecipeModel(RecipeModel currentRecipe){
        mCurrentRecipeModel = currentRecipe;
        notifyDataSetChanged();
    }
}
