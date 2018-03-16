package com.example.theschulk.recipesnow.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gregs on 11/1/2017.
 */

public class IngredientDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ingredient.db";

    public IngredientDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_Ingredient_TABLE = "CREATE TABLE " +
                IngredientContract.IngredientEntry.TABLE_NAME + " (" +
                IngredientContract.IngredientEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IngredientContract.IngredientEntry.COLUMN_NAME_RECIPE + " TEXT NOT NULL, " +
                IngredientContract.IngredientEntry.COLUMN_NAME_INGREDIENT + " TEXT NOT NULL, " +
                IngredientContract.IngredientEntry.COLUMN_NAME_QUANTITY + " TEXT NOT NULL" +
                ");";

        db.execSQL(CREATE_Ingredient_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + IngredientContract.IngredientEntry.TABLE_NAME);
        onCreate(db);
    }

    //CRUD Methods

    //Get records for widget
    public Cursor getIngredients(){
        String selectQuery = "SELECT  * FROM " + IngredientContract.IngredientEntry.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }
}
