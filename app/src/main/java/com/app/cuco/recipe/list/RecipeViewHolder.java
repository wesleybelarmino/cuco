package com.app.cuco.recipe.list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.cuco.R;
import com.app.cuco.common.pojo.Recipe;
import io.reactivex.subjects.PublishSubject;

public class RecipeViewHolder extends RecyclerView.ViewHolder {

    View view;

    @BindView(R.id.recipe_list_item_photo) ImageView photo;
    @BindView(R.id.recipe_list_item_name) TextView name;

    public RecipeViewHolder(View itemView) {
        super(itemView);
    }

    public RecipeViewHolder(View itemView, final PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                clickSubject.onNext(getAdapterPosition());
            }
        });

    }

    public void bind(Recipe recipe) {
        name.setText(recipe.getName());
    }
}