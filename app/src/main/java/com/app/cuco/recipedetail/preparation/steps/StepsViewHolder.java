package com.app.cuco.recipedetail.preparation.steps;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.cuco.R;
import com.app.cuco.recipedetail.preparation.steps.pojo.Steps;
import io.reactivex.subjects.PublishSubject;

public class StepsViewHolder extends RecyclerView.ViewHolder {

    View view;

    @BindView(R.id.steps_list_item_description) TextView description;

    public StepsViewHolder(View itemView) {
        super(itemView);
    }

    public StepsViewHolder(View itemView, final PublishSubject<Integer> clickSubject) {
        super(itemView);
        this.view = itemView;
        ButterKnife.bind(this, view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                clickSubject.onNext(getAdapterPosition());
            }
        });
    }

    public void bind(Steps steps) {
        description.setText(steps.getDescription());
    }
}
