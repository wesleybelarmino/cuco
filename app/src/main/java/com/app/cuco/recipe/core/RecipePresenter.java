package com.app.cuco.recipe.core;

import com.app.cuco.common.pojo.Recipe;
import com.app.cuco.data.DataManager;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class RecipePresenter implements RecipeContract.Presenter {

    private RecipeContract.View recipeView;
    private DataManager dataManager;
    private CompositeDisposable subscriptions;

    public RecipePresenter(RecipeContract.View view, DataManager dataManager,
        CompositeDisposable subs) {


    }

    @Override public void onDestroy() {

    }

    @Override public void onCreate() {

    }

    @Override public void onCreateSavedInstance(List<Recipe> recipeList) {

    }

    @Override public void checkIfNeedRetry() {

    }
}
