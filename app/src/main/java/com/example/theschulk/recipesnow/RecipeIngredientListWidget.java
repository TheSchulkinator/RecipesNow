package com.example.theschulk.recipesnow;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.RemoteViews;

import com.example.theschulk.recipesnow.Database.IngredientDbHelper;
import com.example.theschulk.recipesnow.RecyclerViewAdapters.WidgetCursorAdapter;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeIngredientListWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //setup database connection
        IngredientDbHelper handler = new IngredientDbHelper(context);
        SQLiteDatabase db = handler.getWritableDatabase();
        Cursor widgetCursor = db.rawQuery("SELECT  * FROM ingredients", null);

        ListView lView = (ListView)  findViewById()
        WidgetCursorAdapter widgetAdapter = new WidgetCursorAdapter(context, widgetCursor);

        //SharedPreferences sharedPref = context.getPreferences(context);
        //String ingredientListText = PreferenceManager.getDefaultSharedPreferences(context).
               // getString((context.getString(R.string.widget_ingredient_key)), "Select Recipe In Application!");
                // sharedPref.getString(getString(R.string.widget_ingredient_key), "Select Recipe In Application!")

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_ingredient_list_widget);
        
        //views.setTextViewText(R.id.appwidget_text, ingredientListText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

