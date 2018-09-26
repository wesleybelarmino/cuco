package com.app.cuco.recipe.core;

import com.app.cuco.data.DataManager;
import io.reactivex.disposables.CompositeDisposable;

public class RecipePresenter implements RecipeContract.Presenter {

    private RecipeContract.View recipeView;
    private DataManager dataManager;
    private CompositeDisposable subscriptions;

    public RecipePresenter(RecipeContract.View view, DataManager dataManager,
        CompositeDisposable subs) {


    }

    @Override public void onDestroy() {

    }
}
