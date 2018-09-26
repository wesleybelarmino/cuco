package com.app.cuco.recipe.di;

import com.app.cuco.recipe.RecipeActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { RecipeModule.class })
public interface RecipeComponent {
    void inject(RecipeActivity recipeActivity);
}
