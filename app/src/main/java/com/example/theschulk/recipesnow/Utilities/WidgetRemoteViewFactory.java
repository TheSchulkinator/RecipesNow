package com.example.theschulk.recipesnow.Utilities;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.theschulk.recipesnow.Database.IngredientContract;
import com.example.theschulk.recipesnow.Database.IngredientDbHelper;
import com.example.theschulk.recipesnow.R;

/**
 * Created by gregs on 1/16/2018.
 */

public class WidgetRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context mContext = null;
    private int mAppWidgetId;
    private Cursor mCursor;


    public WidgetRemoteViewFactory(Context context, Intent intent){
        this.mContext = context;
        mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = mContext.getContentResolver().query(IngredientDataProvider.CONTENT_URI, null, null,
                null, null);
    }

    @Override
    public void onDestroy() {
        if (mCursor != null) {
            mCursor.close();
        }
    }

    @Override
    public int getCount() {
        return mCursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        //Get strings to display in layout
        String recipeTitle = "";
        String ingredient ="";
        String quantity = "";

        if(mCursor.moveToPosition(position)){
            final int ingredientColumnIndex = mCursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_NAME_INGREDIENT);
            final int quantityColumnIndex = mCursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_NAME_QUANTITY);
            final int titleColumnIndex = mCursor.getColumnIndex(IngredientContract.IngredientEntry.COLUMN_NAME_RECIPE);

            ingredient = mCursor.getString(ingredientColumnIndex);
            quantity = mCursor.getString(quantityColumnIndex);
            recipeTitle = mCursor.getString(titleColumnIndex);
        }

            RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_text_views);

            views.setTextViewText(R.id.widget_tv_ingredient, ingredient);
            views.setTextViewText(R.id.widget_tv_ingredient_quantity, quantity);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
