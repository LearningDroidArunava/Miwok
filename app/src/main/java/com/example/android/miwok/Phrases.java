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
import android.widget.ListView;
import java.util.ArrayList;

public class Phrases extends Fragment {

    /* Variable to store reference to the ACtivity */
    Activity mCurrentActivity;

    /* Variable storing reference to the ArrayList */
    private ArrayList<Word> mDefaultWords;

    private WordAdapter mAdaptItems;


    /**
     * THe Constructor
     */
    public Phrases(){

    }

    public static Phrases newInstance(int page){

        Bundle args = new Bundle();
        args.putInt("ARGS_PAGE", page);
        Phrases phrases = new Phrases();
        phrases.setArguments(args);
        return phrases;
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
        mDefaultWords.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        mDefaultWords.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        mDefaultWords.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        mDefaultWords.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        mDefaultWords.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        mDefaultWords.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        mDefaultWords.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_im_coming));
        mDefaultWords.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        mDefaultWords.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        mDefaultWords.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        return inflater.inflate(R.layout.activity_phrases, container, false);

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
        ListView phrasesList = (ListView) mCurrentActivity.findViewById(R.id.thePhrasesList);

        //Chaning background color
        phrasesList.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_green_light));

        // Setting the adapter with the {@link ListView}
        phrasesList.setAdapter(mAdaptItems);
    }

    @Override
    public void onStop(){
        super .onStop();

        mAdaptItems.releaseMediaPlayer();
    }
}