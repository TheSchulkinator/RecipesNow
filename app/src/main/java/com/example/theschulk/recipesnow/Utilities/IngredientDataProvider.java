package com.example.theschulk.recipesnow.Utilities;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.theschulk.recipesnow.Database.IngredientContract;
import com.example.theschulk.recipesnow.Database.IngredientDbHelper;

public class IngredientDataProvider extends ContentProvider {

    private IngredientDbHelper mDBHelper;
    private SQLiteDatabase db;

    public static final Uri CONTENT_URI =
            Uri.parse("content://com.example.theschulk.ingredientlistwidget.provider");

    @Override
    public boolean onCreate() {
        mDBHelper = new IngredientDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String selection, @Nullable String[] widgetSelectionArgs, @Nullable String s1) {
        String selectQuery = "SELECT  * FROM " + IngredientContract.IngredientEntry.TABLE_NAME;

        db = mDBHelper.getWritableDatabase();

        Cursor cursor = db.query(
                IngredientContract.IngredientEntry.TABLE_NAME,
                null,
                IngredientContract.IngredientEntry.COLUMN_NAME_RECIPE + "=?",
                widgetSelectionArgs,
                null,
                null,
                null
        );
        //Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
