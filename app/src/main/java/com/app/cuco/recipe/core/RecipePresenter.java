package com.app.cuco.recipe.core;

import android.util.Log;
import com.app.cuco.common.pojo.Recipe;
import com.app.cuco.data.DataManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecipePresenter implements RecipeContract.Presenter {

    private RecipeContract.View recipeView;
    private DataManager dataManager;
    private CompositeDisposable subscriptions;
    private List<Recipe> recipeList;
    private boolean notLoadContentByNetProblem = false;

    public RecipePresenter(RecipeContract.View view, DataManager dataManager,
        CompositeDisposable subs) {
        this.recipeView = view;
        this.dataManager = dataManager;
        this.subscriptions = subs;
        this.recipeList = new ArrayList<>();
    }

    @Override public void onCreate() {
        this.subscriptions.add(respondToClick());
        Observable<List<Recipe>> recipeResultObservable;
        recipeResultObservable = dataManager.getAllRecipes();
        recipeResultObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<List<Recipe>>() {
                @Override public void onSubscribe(Disposable d) {
                    Log.d("presenter", "onSubscribe");
                }

                @Override public void onNext(List<Recipe> recipes) {
                    Log.d("presenter", "onNext");
                    recipeList.addAll(recipes);
                    Log.d("presenter", "sizer: " + recipeList.size());

                }

                @Override public void onError(Throwable e) {
                    Log.d("presenter", "onError: " + e.toString());
                    if (e instanceof IOException) {
                        notLoadContentByNetProblem = true;
                    }
                }

                @Override public void onComplete() {
                    Log.d("presenter", "onComplete");
                    recipeView.showRecipeList(recipeList);
                    notLoadContentByNetProblem = false;
                }
            });
    }

    @Override public void onCreateSavedInstance(List<Recipe> list) {
        this.subscriptions.add(respondToClick());
        recipeList.addAll(list);
        notLoadContentByNetProblem = false;
        recipeView.showRecipeList(recipeList);
    }

    @Override public void checkIfNeedRetry() {
        if(notLoadContentByNetProblem){
            onCreate();
        }
    }

    private Disposable respondToClick() {
        return recipeView.itemClicks().subscribe(new Consumer<Integer>() {
            @Override public void accept(Integer integer) throws Exception {
                Log.d("presenter", "Item: " + integer);
                recipeView.goToRecipeDetailsActivity(recipeList.get(integer), integer);
            }
        });
    }

    @Override public void onDestroy() {
        subscriptions.clear();
    }

}
