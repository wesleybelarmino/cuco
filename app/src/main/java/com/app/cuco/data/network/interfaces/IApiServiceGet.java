package com.app.cuco.data.network.interfaces;

import com.app.cuco.common.pojo.RecipeResults;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface IApiServiceGet {

    @GET("topher/2017/May/59121517_baking/baking.json") Observable<RecipeResults> allRecipes();
}
