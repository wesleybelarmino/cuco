package com.app.cuco.recipedetail.preparation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.cuco.CucoApp;
import com.app.cuco.R;
import com.app.cuco.common.pojo.Recipe;
import com.app.cuco.recipedetail.preparation.ingredients.IngredientsAdapter;
import com.app.cuco.recipedetail.preparation.steps.OnItemClickListener;
import com.app.cuco.recipedetail.preparation.steps.StepsAdapter;

public class PreparationFragment extends Fragment {

    @BindView(R.id.preparation_ingredient_list) RecyclerView recyclerViewIngredients;
    @BindView(R.id.preparation_steps_list) RecyclerView recyclerViewSteps;

    private Recipe mRecipe;
    private IngredientsAdapter ingredientsAdapter;
    private StepsAdapter stepsAdapter;
    private OnStepSelectedListener mCallback;

    public PreparationFragment() {

    }

    public void setRecipe(Recipe recipe) {
        this.mRecipe = recipe;
        Log.d("preparation", "ingredients: " + mRecipe.getIngredients().size());
        Log.d("preparation", "steps: " + mRecipe.getSteps().size());
        ingredientsAdapter.addIngredientsList(mRecipe.getIngredients());
        ingredientsAdapter.notifyDataSetChanged();

        stepsAdapter.addStepList(mRecipe.getSteps(), new OnItemClickListener() {
            @Override public void onItemClick(int step) {
                mCallback.onAStepSelected(step);
            }
        });
        stepsAdapter.notifyDataSetChanged();

        Log.d("remoteView","saveIngredientsList");
        CucoApp.saveIngredientsList(mRecipe.getIngredients());
    }

    @Override public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnStepSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                context.toString() + " must implement OnStepSelectedListener");
        }
    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_preparation, container, false);

        //ButterKnife
        ButterKnife.bind(this, viewRoot);

        return viewRoot;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        inflateIngredientsList();
        inflateStepsList();
    }

    private void inflateIngredientsList() {
        ingredientsAdapter = new IngredientsAdapter();
        LinearLayoutManager linearLayoutManager =
            new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewIngredients.setLayoutManager(linearLayoutManager);
        recyclerViewIngredients.setNestedScrollingEnabled(false);
        recyclerViewIngredients.setAdapter(ingredientsAdapter);
    }

    private void inflateStepsList() {
        stepsAdapter = new StepsAdapter();
        LinearLayoutManager linearLayoutManager =
            new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewSteps.setLayoutManager(linearLayoutManager);
        recyclerViewSteps.setNestedScrollingEnabled(false);
        recyclerViewSteps.setAdapter(stepsAdapter);
    }

    public interface OnStepSelectedListener {
        void onAStepSelected(int step);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        CucoApp.clearIngredientsList();
    }
}
