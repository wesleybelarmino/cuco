package com.app.cuco.recipedetail.preparation.steps;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.cuco.R;
import com.app.cuco.recipedetail.preparation.steps.pojo.Steps;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsViewHolder> {

    private final PublishSubject<Integer> itemClicks = PublishSubject.create();
    private ArrayList<Steps> stepsList = new ArrayList<>();
    private OnItemClickListener listener;

    public void addStepList(List<Steps> steps, OnItemClickListener listener) {
        stepsList.clear();
        stepsList.addAll(steps);
        this.listener = listener;
    }

    public Observable<Integer> observeClicks() {
        return itemClicks;
    }

    @Override public StepsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.steps_list_item, parent, false);
        return new StepsViewHolder(view);
    }

    @Override public void onBindViewHolder(StepsViewHolder holder, int position) {
        Steps steps = stepsList.get(position);
        holder.bind(steps, listener, position);
    }

    @Override public int getItemCount() {
        return stepsList.size();
    }
}
