package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemClickListener;
import android.media.MediaPlayer;
import android.widget.Toast;

public class NumbersFragment extends Fragment {

    /* Variable to store reference to the ACtivity */
    Activity mCurrentActivity;

    /* Variable storing reference to the ArrayList */
    private ArrayList<Word> mDefaultWords;

    private WordAdapter mAdaptItems;

    /**
     * THe Constructor
        */
    public NumbersFragment(){

    }

    public static NumbersFragment newInstance(int page){

        Bundle args = new Bundle();
        args.putInt("ARGS_PAGE", page);
        NumbersFragment numbers = new NumbersFragment();
        numbers.setArguments(args);
        return numbers;
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
        mDefaultWords.add(new Word("One", "lutti", R.drawable.number_one, R.raw.number_one));
        mDefaultWords.add(new Word("Two", "otiiko", R.drawable.number_two, R.raw.number_two));
        mDefaultWords.add(new Word("Three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        mDefaultWords.add(new Word("Four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        mDefaultWords.add(new Word("Five", "massokka", R.drawable.number_five, R.raw.number_five));
        mDefaultWords.add(new Word("Six", "temmokka", R.drawable.number_six, R.raw.number_six));
        mDefaultWords.add(new Word("Seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        mDefaultWords.add(new Word("Eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        mDefaultWords.add(new Word("Nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        mDefaultWords.add(new Word("Ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        /* Getting reference to the button play */
        ImageView playButton = (ImageView) mCurrentActivity.findViewById(R.id.imageButton);


        return inflater.inflate(R.layout.activity_others, container, false);

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
        ListView numbersList = (ListView) mCurrentActivity.findViewById(R.id.theList);

        // Setting the adapter with the {@link ListView}
        numbersList.setAdapter(mAdaptItems);
    }

    @Override
    public void onStop(){
        super .onStop();

        mAdaptItems.releaseMediaPlayer();
    }
}