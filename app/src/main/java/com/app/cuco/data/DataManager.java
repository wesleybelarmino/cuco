package com.app.cuco.data;

import com.app.cuco.common.pojo.Recipe;
import com.app.cuco.data.di.DaggerDataComponent;
import com.app.cuco.data.network.ApiRequest;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public class DataManager {

    @Inject ApiRequest apiRequest;

    public DataManager() {
        DaggerDataComponent.create().inject(this);
    }

    public Observable<List<Recipe>> getAllRecipes() {
        return apiRequest.get().allRecipes();
    }
}
