package com.app.cuco.recipedetail;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.cuco.R;
import com.app.cuco.common.base.BaseActivity;
import com.app.cuco.common.pojo.Recipe;
import com.app.cuco.recipedetail.preparation.PreparationFragment;
import com.app.cuco.recipedetail.preparation.PreparationVideoFragment;
import com.app.cuco.util.Constants;

public class RecipeDetailActivity extends BaseActivity
    implements PreparationFragment.OnStepSelectedListener {

    @BindView(R.id.detailToolbar) Toolbar toolbar;
    @BindView(R.id.recipe_detail_video) FrameLayout frameLayout;

    private Recipe mRecipe;
    private PreparationFragment preparationFragment;
    private PreparationVideoFragment videoFragment;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        //ButterKnife
        ButterKnife.bind(this);

        //action bar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        mRecipe = (Recipe) getIntent().getExtras().get(Constants.RECIPE_SAVED_INSTANCE);

        preparationFragment = (PreparationFragment) getSupportFragmentManager().findFragmentById(
            R.id.recipe_detail_preparation);
        preparationFragment.setRecipe(mRecipe);

        videoFragment = new PreparationVideoFragment();
        videoFragment.setStepList(mRecipe.getSteps());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.recipe_detail_video, videoFragment);
        transaction.commit();

    }

    @Override public void hasConnection() {

    }

    @Override public void loseConnection() {

    }

    @Override public void onAStepSelected(int step) {
        videoFragment.updateStep(step);
        frameLayout.setVisibility(View.VISIBLE);
    }

    @Override public void onBackPressed() {
        if (frameLayout.getVisibility() == View.VISIBLE && !isTabletSize()) {
            frameLayout.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
