package com.app.cuco;

import android.app.Application;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import com.app.cuco.recipedetail.preparation.ingredients.pojo.Ingredients;
import com.app.cuco.widget.RecipeWidgetProvider;
import java.util.ArrayList;
import java.util.List;

public class CucoApp extends Application {

    private static CucoApp instance;
    private static List<Ingredients> ingredientsList;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ingredientsList = new ArrayList<>();
    }

    public static CucoApp getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public static void saveIngredientsList(List<Ingredients> ingredients){
        Log.d("remoteView","ingredients: "+ingredients.size());
        ingredientsList.addAll(ingredients);
        updateWidget();
    }

    public static void clearIngredientsList(){
        Log.d("remoteView","clearIngredientsList");
        ingredientsList.removeAll(ingredientsList);
        updateWidget();
    }

    private static void updateWidget(){
        Log.d("remoteView","updateWidget");
        Log.d("remoteView","size: "+CucoApp.getIngredientsList().size());
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getContext());
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getContext(),
            RecipeWidgetProvider.class));
        Log.d("remoteView","appWidgetIds: "+appWidgetIds.length);
        RecipeWidgetProvider.updateWidgetIngredients(getContext(), appWidgetManager, appWidgetIds);

    }

    public static List<Ingredients> getIngredientsList(){
        return  ingredientsList;
    }
}
