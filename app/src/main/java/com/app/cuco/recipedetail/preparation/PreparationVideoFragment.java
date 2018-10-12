package com.app.cuco.recipedetail.preparation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.cuco.CucoApp;
import com.app.cuco.R;
import com.app.cuco.recipedetail.RecipeDetailActivity;
import com.app.cuco.recipedetail.preparation.steps.pojo.Steps;
import com.app.cuco.util.Utils;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import java.util.ArrayList;
import java.util.List;

public class PreparationVideoFragment extends Fragment {

    @BindView(R.id.preparation_video_view) PlayerView playerView;
    @BindView(R.id.preparation_video_step) TextView videoStep;

    private List<Steps> stepsList;
    private Integer currentStep;
    private SimpleExoPlayer player;
    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;


    public PreparationVideoFragment(){
        currentStep = 0;
        playWhenReady = false;
        currentWindow = 0;
        playbackPosition = 0;
        currentStep = null;

    }

    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_preparation_video, container, false);

        //ButterKnife
        ButterKnife.bind(this, viewRoot);

        return viewRoot;
    }

    public void setStepList(List<Steps> steps){
        stepsList = new ArrayList<>(steps);
    }

    public void updateStep(int step){
        currentStep = step;
        if(player != null){
            releasePlayer();
        }
        initializePlayer();
    }

    private void initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
            new DefaultRenderersFactory(CucoApp.getContext()),
            new DefaultTrackSelector(), new DefaultLoadControl());

        playerView.setPlayer(player);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);

        String url = (!stepsList.get(currentStep).getVideoURL().equals(""))?stepsList.get
            (currentStep).getVideoURL():stepsList.get(currentStep).getThumbnailURL();
        Uri uri = Uri.parse(url);
        MediaSource mediaSource = buildMediaSource(uri);
        player.prepare(mediaSource, true, false);

        videoStep.setText(stepsList.get(currentStep).getDescription());
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource.Factory(
            new DefaultHttpDataSourceFactory("exoplayer-codelab")).
            createMediaSource(uri);
    }

    public void releasePlayer() {
        if (player != null) {
            player.stop();
            player.clearVideoSurface();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.release();
            player = null;
        }
    }

    @SuppressLint("InlinedApi")
    private void hideSystemUi() {
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
            | View.SYSTEM_UI_FLAG_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override public void onResume() {
        super.onResume();
        if(currentStep != null){
            initializePlayer();
        }
    }

    @Override public void onPause() {
        super.onPause();
        releasePlayer();
    }

    public void showVideoFullscreen(){
        videoStep.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
        params.width=params.MATCH_PARENT;
        params.height=params.MATCH_PARENT;
        playerView.setLayoutParams(params);
    }

    public void restVideoSize(){
        videoStep.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) playerView.getLayoutParams();
        params.width=params.MATCH_PARENT;
        params.height=(int) Utils.pxFromDp(CucoApp.getContext(), 250f);
        playerView.setLayoutParams(params);
    }

    public void disableVideo(){
        restVideoSize();
        releasePlayer();
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(getActivity() != null && ((RecipeDetailActivity) getActivity())
            .getSupportActionBar()!=null && player != null && !((RecipeDetailActivity) getActivity
            ()).isTabletSize()) {

            // Checking the orientation of the screen
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                Utils.hideStatusBar(getActivity());
                ((RecipeDetailActivity) getActivity()).getSupportActionBar().hide();
                showVideoFullscreen();

            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
                Utils.showStatusBar(getActivity());
                ((RecipeDetailActivity) getActivity()).getSupportActionBar().show();
                restVideoSize();

            }
        }
    }

}
