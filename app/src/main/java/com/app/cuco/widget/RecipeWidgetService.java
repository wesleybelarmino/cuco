package com.app.cuco.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;
import com.app.cuco.widget.list.RecipeViewsFactory;

public class RecipeWidgetService extends RemoteViewsService {

    @Override public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RecipeViewsFactory();
    }
}
