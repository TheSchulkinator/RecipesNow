package com.example.theschulk.recipesnow.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.theschulk.recipesnow.Models.IngredientModel;
import com.example.theschulk.recipesnow.R;

import java.util.List;

public class RecipeIngredientBuilder {

    public static String[] IngredientQuantityBuilder(List<IngredientModel> ingredientModel){
        int numberOfIngredients = ingredientModel.size();
        String completedIngredientList;
        String[] IngredientQuantityArray = new String[numberOfIngredients];
        IngredientModel loopedThroughIngredient;

        for (int i = 0; i < numberOfIngredients; i++){
            loopedThroughIngredient = ingredientModel.get(i);

            Double doubleQuantity = loopedThroughIngredient.getQuantity();

            if((doubleQuantity % 1) == 0){
                int intQuantity = doubleQuantity.intValue();
                completedIngredientList = String.valueOf(intQuantity);
            } else {
                completedIngredientList = String.valueOf(doubleQuantity);
            }

            if(!loopedThroughIngredient.getMeasure().equals("UNIT")){
                completedIngredientList += " " + loopedThroughIngredient.getMeasure();
            }

            IngredientQuantityArray[i] = completedIngredientList;
        }

        return IngredientQuantityArray;
    }

    public static String[] IngredientListBuilder (List<IngredientModel> ingredientModel){
        int numberOfIngredients = ingredientModel.size();
        String[] IngredientQuantityArray = new String[numberOfIngredients];
        String completedIngredientList;
        IngredientModel loopedThroughIngredient;

        for (int i = 0; i < numberOfIngredients; i++){
            loopedThroughIngredient = ingredientModel.get(i);
            completedIngredientList =  loopedThroughIngredient.getIngredient();
            IngredientQuantityArray[i] = completedIngredientList;
        }



        return IngredientQuantityArray;
    }

}
