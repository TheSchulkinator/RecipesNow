package com.example.theschulk.recipesnow.RecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theschulk.recipesnow.Models.RecipeModel;
import com.example.theschulk.recipesnow.R;

import java.util.List;


public class RecipeListRecyclerViewAdapter extends RecyclerView.Adapter<RecipeListRecyclerViewAdapter.RecipeListViewHolder> {

    private List<RecipeModel> mRecipeList;

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.recipe_list_view;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new RecipeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeListViewHolder holder, int position) {

        RecipeModel currentRecipe = mRecipeList.get(position);
        String currentRecipeItemInList = currentRecipe.getName();
        holder.recipeListTextView.setText(currentRecipeItemInList);
    }


    @Override
    public int getItemCount() {
        if(mRecipeList == null) return 0;
        return mRecipeList.size();
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder{

        TextView recipeListTextView;

        public RecipeListViewHolder(View view){
            super(view);
            recipeListTextView = (TextView) view.findViewById(R.id.tv_recipe_list);
        }

    }

    public void setRecipeListIntoRecyclerView(List<RecipeModel> recipeList){
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }
}
