package com.app.cuco.recipedetail.preparation.ingredients;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.cuco.R;
import com.app.cuco.recipedetail.preparation.ingredients.pojo.Ingredients;
import com.app.cuco.recipedetail.preparation.steps.pojo.Steps;
import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {

    private ArrayList<Ingredients> ingredientsList = new ArrayList<>();

    public void addIngredientsList(List<Ingredients> ingredients) {
        ingredientsList.clear();
        ingredientsList.addAll(ingredients);
    }

    @Override public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.ingredients_list_item, parent, false);
        return new IngredientsViewHolder(view);
    }

    @Override public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        Ingredients ingredients = ingredientsList.get(position);
        holder.bind(ingredients);
    }

    @Override public int getItemCount() {
        return ingredientsList.size();
    }
}
