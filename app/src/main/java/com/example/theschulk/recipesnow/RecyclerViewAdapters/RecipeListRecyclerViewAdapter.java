package com.example.theschulk.recipesnow.RecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theschulk.recipesnow.R;


public class RecipeListRecyclerViewAdapter extends RecyclerView.Adapter<RecipeListRecyclerViewAdapter.RecipeListViewHolder> {

    private String[] mRecipeList;

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
        holder.recipeListTextView.setText(mRecipeList[position]);
    }


    @Override
    public int getItemCount() {
        if(mRecipeList == null) return 0;
        return mRecipeList.length;
    }

    public class RecipeListViewHolder extends RecyclerView.ViewHolder{

        TextView recipeListTextView;

        public RecipeListViewHolder(View view){
            super(view);
            recipeListTextView = (TextView) view.findViewById(R.id.tv_recipe_list);
        }

    }

    public void setRecipeListIntoRecyclerView(String[] recipeList){
        mRecipeList = recipeList;
        notifyDataSetChanged();
    }
}
