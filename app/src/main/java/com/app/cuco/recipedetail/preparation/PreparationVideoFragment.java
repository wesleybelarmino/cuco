package com.app.cuco.recipedetail.preparation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.app.cuco.R;
import com.app.cuco.recipedetail.preparation.steps.pojo.Steps;
import java.util.List;

public class PreparationVideoFragment extends Fragment {

    public PreparationVideoFragment(){

    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preparation_video, container, false);
    }

    public void setStepList(List<Steps> steps){

    }

    public void updateStep(int step){

    }
}
