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
        this.view = itemView;
        ButterKnife.bind(this, view);
    }

    public void bind(final Steps steps, final OnItemClickListener listener, final int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                listener.onItemClick(position);
            }
        });

        description.setText(steps.getDescription());
    }
}
