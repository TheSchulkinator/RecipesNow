package com.example.theschulk.recipesnow;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;
import com.example.theschulk.recipesnow.Utilities.RecipeWidgetService;

public class RecipeIngredientListWidget extends AppWidgetProvider {

    static private RemoteViews updateWidgetListView(Context context, int appWidgetId, AppWidgetManager appWidgetManager) {
        RemoteViews remoteViews;
        /*PendingIntent nutellaButtonPendingIntent = setPendingIntent(context.getString(R.string.nutella_action_button), context, appWidgetId);
        PendingIntent brownieButtonPendingIntent = setPendingIntent(context.getString(R.string.brownie_action_button), context, appWidgetId);
        PendingIntent yellowCakeButtonPendingIntent = setPendingIntent(context.getString(R.string.yellow_cake_action_button), context, appWidgetId);
        PendingIntent cheesecakeButtonPendingIntent = setPendingIntent(context.getString(R.string.cheesecake_action_button), context, appWidgetId);

        //set up shared preference to store current view
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.widget_view_key),Context.MODE_PRIVATE);

        //retrieve sharedpreference key stored value in order to determine what view to use
        String widgetView = sharedPreferences.getString(context.getString(R.string.widget_view_key), "");

        //TODO: add pending intent for each button and handle on receive
        //read shared preference to see what display should be displayed
        if(widgetView.equals("") || widgetView.equals(context.getString(R.string.button_widget_key))){

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_recipe_button_layout);
            remoteViews.setOnClickPendingIntent(R.id.nutella_button, nutellaButtonPendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.brownie_button, brownieButtonPendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.yellow_cake_button, yellowCakeButtonPendingIntent);
            remoteViews.setOnClickPendingIntent(R.id.cheesecake_button, cheesecakeButtonPendingIntent);
        } else {
            PendingIntent widgetBackButtonPendingIntent = setPendingIntent(context.getString(R.string.widget_back_action_button), context, appWidgetId);

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.recipe_ingredient_list_widget);

            Intent svcIntent = new Intent(context, RecipeWidgetService.class);
            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
            remoteViews.setRemoteAdapter(R.id.widget_list, svcIntent);
            //appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
            remoteViews.setOnClickPendingIntent(R.id.widget_back_button, widgetBackButtonPendingIntent);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
        }*/

        PendingIntent widgetBackButtonPendingIntent = setPendingIntent(context.getString(R.string.widget_back_action_button), context, appWidgetId);

         RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.recipe_ingredient_list_widget);

        Intent svcIntent = new Intent(context, RecipeWidgetService.class);
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        remoteViews.setRemoteAdapter(R.id.widget_list, svcIntent);
        remoteViews.setOnClickPendingIntent(R.id.widget_back_button, widgetBackButtonPendingIntent);
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);

        return remoteViews;
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
        RemoteViews views = updateWidgetListView(context,
                appWidgetId, appWidgetManager);
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        super.onReceive(context, intent);

        //Set up shared preferences
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.widget_view_key),Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String widgetView = sharedPreferences.getString(context.getString(R.string.widget_view_key), context.getString(R.string.nutella_value));

        //Get parameters for onupdate
        AppWidgetManager mAppWidgetManager = AppWidgetManager.getInstance(context);
        int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, 0);
        final int[] appWidgetIds = mAppWidgetManager.getAppWidgetIds(new ComponentName(context,
                this.getClass()));



        if(context.getString(R.string.widget_back_action_button).equals(intent.getAction())) {

            if(widgetView.equals(context.getString(R.string.nutella_value))){
                editor.putString(context.getString(R.string.widget_view_key), context.getString(R.string.brownie_value));
                editor.commit();
            } else if(widgetView.equals(context.getString(R.string.brownie_value))){
                editor.putString(context.getString(R.string.widget_view_key), context.getString(R.string.yellow_cake_value));
                editor.commit();
            }else if(widgetView.equals(context.getString(R.string.yellow_cake_value))){
                editor.putString(context.getString(R.string.widget_view_key), context.getString(R.string.cheesecake_value));
                editor.commit();
            } else if(widgetView.equals(context.getString(R.string.cheesecake_value))){
                editor.putString(context.getString(R.string.widget_view_key), context.getString(R.string.nutella_value));
                editor.commit();
            }

            mAppWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
            onUpdate(context, mAppWidgetManager, appWidgetIds);
        }
            // updateAppWidget(context, mAppWidgetManager, appWidgetId);
        //mAppWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);

        /*if(context.getString(R.string.nutella_action_button).equals(intent.getAction())){
            editor.putString(context.getString(R.string.widget_view_key), context.getString(R.string.nutella_value));
            editor.commit();
            mAppWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
            //updateAppWidget(context, mAppWidgetManager, appWidgetId);
            onUpdate(context, mAppWidgetManager, appWidgetIds);
        } else if(context.getString(R.string.brownie_action_button).equals(intent.getAction())){
            editor.putString(context.getString(R.string.widget_view_key), context.getString(R.string.brownie_value));
            editor.commit();
            mAppWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
           // updateAppWidget(context, mAppWidgetManager, appWidgetId);
            onUpdate(context, mAppWidgetManager, appWidgetIds);
        }else if(context.getString(R.string.yellow_cake_action_button).equals(intent.getAction())){
            editor.putString(context.getString(R.string.widget_view_key), context.getString(R.string.yellow_cake_value));
            editor.commit();
            mAppWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
           // updateAppWidget(context, mAppWidgetManager, appWidgetId);
            onUpdate(context, mAppWidgetManager, appWidgetIds);
        } else if(context.getString(R.string.cheesecake_action_button).equals(intent.getAction())){
            editor.putString(context.getString(R.string.widget_view_key), context.getString(R.string.cheesecake_value));
            editor.commit();
            mAppWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
            onUpdate(context, mAppWidgetManager, appWidgetIds);
           // updateAppWidget(context, mAppWidgetManager, appWidgetId);
        } else if(context.getString(R.string.widget_back_action_button).equals(intent.getAction())){
            editor.putString(context.getString(R.string.widget_view_key), context.getString(R.string.button_widget_key));
            editor.commit();
            mAppWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
            onUpdate(context, mAppWidgetManager, appWidgetIds);
           // updateAppWidget(context, mAppWidgetManager, appWidgetId);
        } else {
            super.onReceive(context, intent);
        }*/

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widget_list);
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static private PendingIntent setPendingIntent(String actionString, Context context, int appWidgetId){
        Intent intent = new Intent(actionString);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return  pendingIntent;
    }
}

