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

public class FamilyMembers extends Fragment {

    /* Variable to store reference to the ACtivity */
    Activity mCurrentActivity;

    /* Variable storing reference to the ArrayList */
    private ArrayList<Word> mDefaultWords;

    private WordAdapter mAdaptItems;

    /**
     * THe Constructor
     */
    public FamilyMembers(){

  }

    public static FamilyMembers newInstance(int page){

        Bundle args = new Bundle();
        args.putInt("ARGS_PAGE", page);
        FamilyMembers family = new FamilyMembers();
        family.setArguments(args);
        return family;
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
        mDefaultWords.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        mDefaultWords.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        mDefaultWords.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        mDefaultWords.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        mDefaultWords.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        mDefaultWords.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        mDefaultWords.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        mDefaultWords.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        mDefaultWords.add(new Word("grandmother", "ama", R.drawable.family_grandfather, R.raw.family_grandmother));
        mDefaultWords.add(new Word("grandfather", "paapa", R.drawable.family_grandmother, R.raw.family_grandfather));

        return inflater.inflate(R.layout.activity_family, container, false);


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
        WordAdapter mAdaptItems = new WordAdapter(mCurrentActivity, mDefaultWords);

        // Getting the id of the ListView in numberActivity.xml
        ListView familyList = (ListView) mCurrentActivity.findViewById(R.id.theFamilyList);

        //Chaning background color
        familyList.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.holo_purple));

        // Setting the adapter with the {@link ListView}
        familyList.setAdapter(mAdaptItems);
    }

    @Override
    public void onStop(){
        super .onStop();

        mAdaptItems.releaseMediaPlayer();
    }
}


