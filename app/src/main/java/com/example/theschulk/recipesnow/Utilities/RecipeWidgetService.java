package com.example.theschulk.recipesnow.Utilities;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class RecipeWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent){
        return new WidgetRemoteViewFactory(this.getApplicationContext(), intent);
    }
}
