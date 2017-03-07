package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class Colors extends Fragment {

    /* Variable to store reference to the ACtivity */
    Activity mCurrentActivity;

    /* Variable storing reference to the ArrayList */
    private ArrayList<Word> mDefaultWords;

    WordAdapter mAdaptItems;
    /**
     * THe Constructor
     */
    public Colors(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

        /** Getting reference to the Activity */
        mCurrentActivity = getActivity();

        /**
         * Creating {@link ArrayList} with {@link Word} data type
         */
        mDefaultWords = new ArrayList<>();

        // Adding the English Words
        mDefaultWords.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        mDefaultWords.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        mDefaultWords.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        mDefaultWords.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        mDefaultWords.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        mDefaultWords.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        mDefaultWords.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        mDefaultWords.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        return inflater.inflate(R.layout.activity_others2, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedStateInstance){

        super .onActivityCreated(savedStateInstance);

        /**
         * Creating {@link ArrayAdapter} to link the {@link String}
         * from {@link ArrayList} to create a
         * {@link ListView}
         * @param to {@link ArrayAdapter} ->
         *                               {@param Context} passing this
         *                               {@param
         */
        mAdaptItems = new WordAdapter(mCurrentActivity, mDefaultWords);

        // Getting the id of the ListView in numberActivity.xml
        final ListView colorsList = (ListView) mCurrentActivity.findViewById(R.id.theList2);
        
        //Chaning background color
        colorsList.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.background_light));

        // Setting the adapter with the {@link ListView}
        colorsList.setAdapter(mAdaptItems);
    }

    @Override
    public void onStop(){
        super .onStop();

        mAdaptItems.releaseMediaPlayer();
    }
}
