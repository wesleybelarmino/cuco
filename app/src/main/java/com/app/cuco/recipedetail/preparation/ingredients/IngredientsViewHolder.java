package com.app.cuco.recipedetail.preparation.ingredients;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.app.cuco.R;
import com.app.cuco.recipedetail.preparation.ingredients.pojo.Ingredients;

public class IngredientsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ingredients_list_item_text) TextView ingredient;

    public IngredientsViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(Ingredients ingredients) {
        StringBuilder stringBuilder = new StringBuilder(ingredients.getQuantity() + "");
        stringBuilder.append(" ");
        stringBuilder.append(ingredients.getMeasure());
        stringBuilder.append(" ");
        stringBuilder.append(ingredients.getIngredient());
        ingredient.setText(stringBuilder.toString());
    }
}
