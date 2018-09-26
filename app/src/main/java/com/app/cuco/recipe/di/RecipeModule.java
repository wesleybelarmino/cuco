package com.app.cuco.recipe.di;

import com.app.cuco.data.DataManager;
import com.app.cuco.recipe.core.RecipeContract;
import com.app.cuco.recipe.core.RecipePresenter;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module public class RecipeModule {

    private RecipeContract.View recipeView;

    public RecipeModule(RecipeContract.View view) {
        this.recipeView = view;
    }

    @Provides RecipeContract.Presenter providesRecipePresenter(){
        return new RecipePresenter(this.recipeView, new DataManager(), new CompositeDisposable());
    }
}
