package com.app.cuco.recipe.list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.cuco.R;
import com.app.cuco.common.pojo.Recipe;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter  extends RecyclerView.Adapter<RecipeViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    private ArrayList<Recipe> recipeList = new ArrayList<>();

    public void addRecipeList(List<Recipe> recipes){
        recipeList.clear();
        recipeList.addAll(recipes);
    }

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }


    @Override public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.recipe_list_item, parent, false);
        return new RecipeViewHolder(view ,itemClicks);
    }

    @Override public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);

    }

    @Override public int getItemCount() {
        return recipeList.size();
    }


    public ArrayList<Recipe> getList(){
        return recipeList;
    }
}
