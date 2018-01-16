package com.example.theschulk.recipesnow.RecyclerViewAdapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.theschulk.recipesnow.R;

/**
 * Created by gregs on 1/15/2018.
 */

public class WidgetCursorAdapter extends CursorAdapter {

    public WidgetCursorAdapter (Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.widget_text_views, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView mIngredient = (TextView) view.findViewById(R.id.widget_tv_ingredient);
        TextView mQuantity = (TextView) view.findViewById(R.id.widget_tv_ingredient_quantity);

        String ingredient = cursor.getString(cursor.getColumnIndexOrThrow("ingredient"));
        String quantity = cursor.getString(cursor.getColumnIndexOrThrow("quantity"));

        mIngredient.setText(ingredient);
        mQuantity.setText(quantity);
    }
}
