package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by arunava on 19/12/16.
 */

/**
 * {@link WordAdapter} is an extension of the {@link ArrayAdapter}
 * this provides the layout for each List
 *
 * based on the data source which is a {@link ArrayList} of {@link Word} objects
 */
public class WordAdapter extends ArrayAdapter<Word> {


    private static AudioManager mAudioManager;

    /**
     * Creating a {@link MediaPlayer} to play the respective pronounciations
     * for the Miwok words
     */
    private static MediaPlayer mMedia;

    private  ImageView mPlayButton;

    private static Context mContext;

    private static ImageView mPlayButtonView;

    /**
     * A custom created Constructor
     * this is used to inflate a custom created layout file
     *
     * @param context       The current context used to inflate
     * @param wordArrayList the {@link ArrayList} of the {@link Word} object
     */
    public WordAdapter(Activity context, ArrayList<Word> wordArrayList) {

        // Here we use the {@link ArrayAdapter} super class constructor
        // for the context and theList
        // here we are using a custom adapter
        // so we are not passing the second parameter
        // which is the Resource id
        // so we are using 0 in its place
        super(context, 0, wordArrayList);
        this.mContext = context;

    }

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    public static AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                mMedia.pause();
                mMedia.seekTo(0);

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                mMedia.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }

        }
    };

    public static void releaseMediaPlayer() {

        if (mMedia != null) {
            mPlayButtonView.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_action_play));
            mMedia.release();
            mMedia = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


    //public void setPlayButtonListener(onClick)

    /**
     * Since we need to display a custom {@link Word} object for each view
     * so we need to make a custom adapter
     * and to to so we need to override the getView() method from its super class
     *
     * @param position    the position of the theList view data that should be displayed
     * @param convertView The recycled view to populate
     * @param parent      The parent {@link View} that is used for inflation
     * @return The view for position in the AdapterView
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        /**
         * Setting the convertible view {@param convertView} to a variable
         */
        View currentView = convertView;

        /** Checking if there is a recyclable view
         * if not then we are creating a customView from scratch
         * */
        if (currentView == null)
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.miwokenglishlist, parent, false);

        /** Getting the current WordObject to be Inflated*/
        final Word currentWordObject = getItem(position);


        /**Setting the {@id englishID} of the miwokenglishlist.xml
         * to the the desired english value
         */

        //Getting the ID
        TextView defalutTextView = (TextView) currentView.findViewById(R.id.englishID);

        //Changing the text of the view
        defalutTextView.setText(currentWordObject.getEnglishWord());

        /**
         * Setting the {@id miwokID} of the miwokenglishlist.xml
         * to the current miwok word
         */

        // Getting the ID
        TextView miwokTextView = (TextView) currentView.findViewById(R.id.miwokID);

        // Changing the text to the current miwok word
        miwokTextView.setText(currentWordObject.getMiwokWord());

        /**
         * Checking whether current Word object has a image associated with it or not
         */
        // Getting the image id from the current word object
        int currImageID = currentWordObject.getImageResourceID();

        // Getting the ID for the image in miwokenglishlist.xml
        ImageView imageViewID = (ImageView) currentView.findViewById(R.id.imageID);

        // if image is present for the current object
        if (currImageID != -1) {

            // Setting the image on the layout
            imageViewID.setImageResource(currImageID);
        }

        // if image is not present for the current object
        else {

            imageViewID.setVisibility(View.GONE);
        }

        mPlayButton = (ImageView) currentView.findViewById(R.id.imageButton);

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                // If there are already underlying resources
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mPlayButtonView = (ImageView) view;
                    if(mMedia != null){releaseMediaPlayer();}
                    mMedia = MediaPlayer.create(getContext(), currentWordObject.getPronunciation());
                    mMedia.start();
                    mMedia.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            ((ImageView)view).setImageDrawable(ContextCompat.getDrawable(mContext,
                                    R.drawable.ic_action_pause));

                        }
                    });

                    mMedia.setOnCompletionListener(mCompletionListener);
                }
            }
        });

        // Returning the modified inflated View
        return currentView;
    }
}
