package com.app.cuco.widget.list;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.app.cuco.CucoApp;
import com.app.cuco.R;
import com.app.cuco.widget.RecipeWidgetProvider;

public class RecipeViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    @Override public void onCreate() {

    }

    @Override public void onDataSetChanged() {

    }

    @Override public void onDestroy() {

    }

    @Override public int getCount() {
        return CucoApp.getIngredientsList().size();
    }

    @Override public RemoteViews getViewAt(int position) {
        RemoteViews row=new RemoteViews(CucoApp.getInstance().getPackageName(),
            R.layout.recipe_widget_provider_item);
        row.setTextViewText(R.id.widget_item, CucoApp.getIngredientsList().get(position).toString());
        return(row);
    }

    @Override public RemoteViews getLoadingView() {
        return null;
    }

    @Override public int getViewTypeCount() {
        return 1;
    }

    @Override public long getItemId(int i) {
        return i;
    }

    @Override public boolean hasStableIds() {
        return true;
    }


}
