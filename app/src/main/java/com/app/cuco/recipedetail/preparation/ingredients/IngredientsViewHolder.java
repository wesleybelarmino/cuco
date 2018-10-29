package com.app.cuco.recipedetail.preparation.ingredients;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.cuco.R;
import com.app.cuco.recipedetail.preparation.ingredients.pojo.Ingredients;

public class IngredientsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ingredients_list_item_text) TextView ingredient;

    public IngredientsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Ingredients ingredients) {
        Log.d("IngredientsViewHolder","bind: "+ingredients.getIngredient());
        ingredient.setText(ingredients.toString());
    }
}
