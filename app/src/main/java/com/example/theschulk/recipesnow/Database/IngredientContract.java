package com.example.theschulk.recipesnow.Database;

import android.provider.BaseColumns;

/**
 * Created by gregs on 11/1/2017.
 */

public final class IngredientContract {
    private IngredientContract(){}

    public static class IngredientEntry implements BaseColumns{
            public static final String TABLE_NAME = "ingredients";
            public static final String COLUMN_NAME_INGREDIENT = "ingredient";
            public static final String COLUMN_NAME_QUANTITY = "quantity";
            public static final String COLUMN_NAME_RECIPE = "recipe";
    }
}
