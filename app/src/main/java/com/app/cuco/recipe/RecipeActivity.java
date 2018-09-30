package com.app.cuco.recipe;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.cuco.R;
import com.app.cuco.common.base.BaseActivity;
import com.app.cuco.common.pojo.Recipe;
import com.app.cuco.recipe.core.RecipeContract;
import com.app.cuco.recipe.di.DaggerRecipeComponent;
import com.app.cuco.recipe.di.RecipeModule;
import com.app.cuco.recipe.list.RecipeAdapter;
import com.app.cuco.util.Constants;
import com.facebook.shimmer.ShimmerFrameLayout;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;

public class RecipeActivity extends BaseActivity implements RecipeContract.View {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recipe_list) RecyclerView recyclerView;
    @BindView(R.id.recipe_shimmer_content) LinearLayout shimmerContent;
    @BindView(R.id.recipe_shimmer_item_1) ShimmerFrameLayout shimmerItem1;
    @BindView(R.id.recipe_shimmer_item_2) ShimmerFrameLayout shimmerItem2;
    @BindView(R.id.recipe_shimmer_item_3) ShimmerFrameLayout shimmerItem3;
    @BindView(R.id.recipe_connection_info) RelativeLayout connectionLayout;

    @Inject RecipeContract.Presenter moviesPresenter;

    private RecipeAdapter recipeAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        //ButterKnife
        ButterKnife.bind(this);

        //dagger
        DaggerRecipeComponent.builder().recipeModule(new RecipeModule(this)).build().inject(this);

        //action bar
        setSupportActionBar(toolbar);

        initShimmer();
        recipeAdapter = new RecipeAdapter();
        setRecyclerView();

        if (savedInstanceState == null) {
            moviesPresenter.onCreate();
        } else {
            moviesPresenter.onCreateSavedInstance((List<Recipe>)savedInstanceState
                .getSerializable(Constants.RECIPE_SAVED_INSTANCE_LIST_KEY));
        }
    }

    private void initShimmer() {
        recyclerView.setVisibility(View.GONE);
        shimmerContent.setVisibility(View.VISIBLE);
        shimmerItem1.startShimmer();
        shimmerItem2.startShimmer();
        shimmerItem3.startShimmer();
    }

    private void stopShimmer() {
        shimmerItem1.stopShimmer();
        shimmerItem2.stopShimmer();
        shimmerItem3.stopShimmer();
        shimmerContent.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    private void setRecyclerView() {
        //recyclerview
        int gridSpanCount = (isTabletSize()) ? 3 : 1;
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, gridSpanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(recipeAdapter);
    }

    @Override public void showRecipeList(List<Recipe> recipeList) {
        Log.d("Activity","showRecipeList() "+recipeList.size());
        recipeAdapter.addRecipeList(recipeList);
        recipeAdapter.notifyDataSetChanged();
        stopShimmer();
    }

    @Override public Observable<Integer> itemClicks() {
        return recipeAdapter.observeClicks();
    }

    @Override public void goToRecipeDetailsActivity(Recipe recipe, int position) {

    }

    @Override public void hasConnection() {
        connectionLayout.setVisibility(View.GONE);
        moviesPresenter.checkIfNeedRetry();
    }

    @Override public void loseConnection() {
        connectionLayout.setVisibility(View.VISIBLE);
    }

    @Override protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable(Constants.RECIPE_SAVED_INSTANCE_LIST_KEY,
            recipeAdapter.getList());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        moviesPresenter.onDestroy();
    }
}
