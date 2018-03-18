package com.example.theschulk.recipesnow.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theschulk.recipesnow.Models.StepModel;
import com.example.theschulk.recipesnow.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


public class RecipeSingleStepInstructionsFragment extends Fragment {

    StepModel mCurrentStepModel;
    TextView mLongDescriptionTextView;
    SimpleExoPlayerView mSimpleExoPlayerView;
    SimpleExoPlayer mSimpleExoPlayer;
    long mCurrentExoPlayerPosition;
    boolean mIsExoPlayerPlaying;
    private String recipeVideoUrl;
    private String recipeThumbnailUrl;

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        mCurrentExoPlayerPosition = mSimpleExoPlayer.getCurrentPosition();
        mIsExoPlayerPlaying = mSimpleExoPlayer.getPlayWhenReady();

        savedInstanceState.putLong(getString(R.string.saveInstanceStateExoPlayerPosition), mCurrentExoPlayerPosition);
        savedInstanceState.putBoolean(getString(R.string.saveInstanceStateExoPlayerPlaying), mIsExoPlayerPlaying);
    }

    public static RecipeSingleStepInstructionsFragment newInstance (Context context, StepModel currentStep){
        RecipeSingleStepInstructionsFragment recipeSingleStepInstructionsFragment = new RecipeSingleStepInstructionsFragment();
        Bundle recipeBundle = new Bundle();
        recipeBundle.putSerializable(context.getString(R.string.current_step_bundle), currentStep);
        recipeSingleStepInstructionsFragment.setArguments(recipeBundle);
        return recipeSingleStepInstructionsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments().containsKey(getString(R.string.current_step_bundle))){
            mCurrentStepModel = (StepModel) getArguments().getSerializable(getString(R.string.current_step_bundle));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(savedInstanceState != null){
            mCurrentExoPlayerPosition = savedInstanceState.getLong(getString(R.string.saveInstanceStateExoPlayerPosition), 0);
            mIsExoPlayerPlaying = savedInstanceState.getBoolean(getString(R.string.saveInstanceStateExoPlayerPlaying), true);
        }
        View rootView;
        recipeVideoUrl = mCurrentStepModel.getVideoURL();
        recipeThumbnailUrl = mCurrentStepModel.getThumbnailURL();


        if(recipeVideoUrl != null && recipeVideoUrl!= "" || recipeThumbnailUrl != null && recipeThumbnailUrl!= "") {
            rootView = inflater.inflate(R.layout.recipe_single_step_instructions_fragment, container, false);
            mLongDescriptionTextView = (TextView) rootView.findViewById(R.id.tv_long_description);
            mSimpleExoPlayerView = (SimpleExoPlayerView) rootView.findViewById(R.id.exo_recipe_video);
            InitializeExoPlayer();
            mLongDescriptionTextView.setText(mCurrentStepModel.getDescription());
        } else {
            rootView = inflater.inflate(R.layout.recipe_single_step_instructions_no_video, container, false);
            mLongDescriptionTextView = (TextView) rootView.findViewById(R.id.tv_no_video_long_description);
            mLongDescriptionTextView.setText(mCurrentStepModel.getDescription());
        }

        return rootView;
    }

    private void InitializeExoPlayer(){
        Context context = getActivity();

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        mSimpleExoPlayerView.setPlayer(mSimpleExoPlayer);

        Uri recipeVideoUri;

        if(recipeVideoUrl != null && recipeVideoUrl!= ""){
            recipeVideoUri = Uri.parse(recipeVideoUrl).buildUpon().build();
        } else {
            recipeVideoUri = Uri.parse(recipeThumbnailUrl).buildUpon().build();
        }

        MediaSource mediaSource = new ExtractorMediaSource(recipeVideoUri, new DefaultDataSourceFactory(context, Util.getUserAgent(context, "RecipesNow")),
                new DefaultExtractorsFactory(), null, null);
        mSimpleExoPlayer.prepare(mediaSource);
        mSimpleExoPlayer.seekTo(mCurrentExoPlayerPosition);
        mSimpleExoPlayer.setPlayWhenReady(mIsExoPlayerPlaying);

    }

    @Override
    public void onStop() {
        super.onStop();
     if( mSimpleExoPlayer!= null) {
         mSimpleExoPlayer.stop();
         mSimpleExoPlayer.release();
         mSimpleExoPlayer = null;
     }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if( mSimpleExoPlayer!= null) {
            mSimpleExoPlayer.stop();
            mSimpleExoPlayer.release();
            mSimpleExoPlayer = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if( mSimpleExoPlayer!= null) {
            mSimpleExoPlayer.stop();
            mSimpleExoPlayer.release();
            mSimpleExoPlayer = null;
        }
    }
}
