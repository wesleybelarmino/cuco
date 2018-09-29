package com.app.cuco.recipe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.app.cuco.R;
import com.app.cuco.common.pojo.Recipe;
import com.app.cuco.recipe.core.RecipeContract;
import io.reactivex.Observable;
import java.util.List;

public class RecipeActivity extends AppCompatActivity implements RecipeContract.View {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
    }

    @Override public void showRecipeList(List<Recipe> recipeList) {

    }

    @Override public Observable<Integer> itemClicks() {
        return null;
    }

    @Override public void goToRecipeDetailsActivity(Recipe recipe, int position) {

    }
}
