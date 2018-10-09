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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
        int drawableId = R.drawable.ic_launcher;

        try {
            drawableId = getImage(recipe.getName().toLowerCase().replace(" ", ""));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Glide.with(view.getContext())
            .load(drawableId)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(photo);
        name.setText(recipe.getName());

    }

    private int getImage(String imageName) throws NoSuchFieldException, IllegalAccessException {
        int drawableResourceId = R.drawable.class.getField(imageName).getInt(null);
        return drawableResourceId;
    }
}