package com.app.cuco.recipe.core;

import com.app.cuco.common.base.BasePresenter;
import com.app.cuco.common.pojo.Recipe;
import io.reactivex.Observable;
import java.util.List;

public interface RecipeContract {

    interface View {
        void showRecipeList(List<Recipe> recipeList);
        Observable<Integer> itemClicks();
        void goToRecipeDetailsActivity(Recipe recipe, int position);

    }

    interface Presenter extends BasePresenter {
        void onCreate();
        void onCreateSavedInstance(List<Recipe> recipeList);
        void checkIfNeedRetry();
    }
}
