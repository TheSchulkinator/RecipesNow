package com.example.theschulk.recipesnow.RecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theschulk.recipesnow.Models.IngredientModel;
import com.example.theschulk.recipesnow.R;

import java.util.List;


public class IngredientRecyclerViewAdapter extends  RecyclerView.Adapter<IngredientRecyclerViewAdapter.IngredientViewHolder>{

    List<IngredientModel> mIngredientModel;

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForRecycleView = R.layout.ingredient_fragment_recyclerview;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParent = false;

        View view = inflater.inflate(layoutIdForRecycleView, parent, shouldAttachToParent);
        return new IngredientViewHolder(view);
}

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        IngredientModel currentIngredient;
        Double IngredientQuantity;
        String IngredientUnit;
        String IngredientQuantityWithUnit = "";
        String Ingredient;

        currentIngredient = mIngredientModel.get(position);
        IngredientQuantity = currentIngredient.getQuantity();
        IngredientUnit = currentIngredient.getMeasure();
        Ingredient = currentIngredient.getIngredient();

        if((IngredientQuantity % 1) == 0){
            int intQuantity = IngredientQuantity.intValue();
            IngredientQuantityWithUnit += intQuantity;
        } else {
            IngredientQuantityWithUnit += IngredientQuantity;
        }

        if(!IngredientUnit.equals("UNIT")){
            IngredientQuantityWithUnit += " " + currentIngredient.getMeasure();
        }

        holder.mIngredientTextView.setText(Ingredient);
        holder.mQuantityTextView.setText(IngredientQuantityWithUnit);

    }

    @Override
    public int getItemCount() {
        if (mIngredientModel == null) return  0;
        int currentSize = mIngredientModel.size();
        return currentSize;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder{
        TextView mIngredientTextView;
        TextView mQuantityTextView;

        public IngredientViewHolder(View view){
            super(view);

            mIngredientTextView = (TextView) view.findViewById(R.id.tv_ingredient);
            mQuantityTextView = (TextView) view.findViewById(R.id.tv_ingredient_quantity);
        }

    }

    public void setCurrentIngredientModel(List<IngredientModel> currentIngredient){
        mIngredientModel = currentIngredient;
        notifyDataSetChanged();
    }
}
