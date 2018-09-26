package com.app.cuco.data;

import com.app.cuco.common.pojo.RecipeResults;
import com.app.cuco.data.di.DaggerDataComponent;
import com.app.cuco.data.network.ApiRequest;

import io.reactivex.Observable;
import javax.inject.Inject;

public class DataManager {

    @Inject ApiRequest apiRequest;

    public DataManager() {
        DaggerDataComponent.create().inject(this);
    }

    public Observable<RecipeResults> getAllRecipes(){
        return apiRequest.get().allRecipes();
    }
}
