package com.example.theschulk.recipesnow.Utilities;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.theschulk.recipesnow.Models.IngredientModel;
import com.example.theschulk.recipesnow.R;

import java.util.List;

public class RecipeIngredientBuilder {
    public static String IngredientListQuantityBuilder(List<IngredientModel> ingredientModel){
        int numberOfIngredients = ingredientModel.size();
        String completedIngredientList = "";
        IngredientModel loopedThroughIngredient;

        for (int i = 0; i < numberOfIngredients; i++){
            loopedThroughIngredient = ingredientModel.get(i);

            Double doubleQuantity = loopedThroughIngredient.getQuantity();

            if((doubleQuantity % 1) == 0){
                int intQuantity = doubleQuantity.intValue();
                completedIngredientList += intQuantity;
            } else {
                completedIngredientList += doubleQuantity;
            }

            if(!loopedThroughIngredient.getMeasure().equals("UNIT")){
                completedIngredientList += " " + loopedThroughIngredient.getMeasure();
            }
            completedIngredientList += System.getProperty("line.separator");
            completedIngredientList += System.getProperty("line.separator");
        }

        return completedIngredientList;
    }

    public static String IngredientListIngredientBuilder(List<IngredientModel> ingredientModel){
        int numberOfIngredients = ingredientModel.size();
        String completedIngredientList = "";
        IngredientModel loopedThroughIngredient;

        for (int i = 0; i < numberOfIngredients; i++){
            loopedThroughIngredient = ingredientModel.get(i);
            completedIngredientList +=  loopedThroughIngredient.getIngredient() + System.getProperty("line.separator");
            completedIngredientList += System.getProperty("line.separator");
        }

        return completedIngredientList;
    }

}
