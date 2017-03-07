package com.example.android.miwok;

/**
 * Created by arunava on 18/12/16.
 */

public class Word {

    /**
     *  Variable to store the Default Translation
     */
    private String defaultTranslation;

    /**
     *  Variable to store the Miwok Translation
     */
    private String miwokTranslation;

    /**
     * Variable To store the Image Resource ID
     */
    private int imageResourceID = IMAGE_RESOURCE_CHECK;

    /**
     * Variable to store the boolean value whether current {@link Word}
     * object has imageResourceID or not.
     *  -1 indicates that the current {@link Word} object dosen't have a imageResourceID.
     *   Any other value indicates that {@link Word} object has a imageResourceID
     */
    private static int IMAGE_RESOURCE_CHECK = -1;

    /* Variable to store the respective sound */
    private int mPronunciation;

    /**
     * Public Constructor class to create {@link Word} object with an image associated
     *
     * @param defaultWord the word in English
     * @param miwokWord the translation for the English word
     * @param imageID the ID of the image for the current {@link Word} object
     */
    public Word (String defaultWord, String miwokWord, int imageID, int pronunciation) {

        defaultTranslation = defaultWord;
        miwokTranslation = miwokWord;
        imageResourceID = imageID;
        mPronunciation = pronunciation;
    }

    /**
     * Public Constructor class to create {@link Word} object without image associated
     *
     * @param defaultWord the default English word
     * @param miwokWord the miwok translation for the word
     */
    public Word (String defaultWord, String miwokWord, int pronunciation) {

        defaultTranslation = defaultWord;
        miwokTranslation = miwokWord;
        mPronunciation = pronunciation;
    }


    /**
     * Getter Method to return the {@param defaultTranslation}
     * the English word
     *
     * @return {@param defaultTranslation} the var containing the English word
     */

    public String getEnglishWord () {

        return defaultTranslation;
    }

    /**
     * Getter method to get the miwok translation
     *
     * @return {@param miwokTranslation} the var containing the miwok Translation
     */
    public String getMiwokWord () {

        return miwokTranslation;
    }

    /**
     * Getter method to get the image
     *
     * @return {@param imageResourceID} the int var containing the image ID
     */
    public int getImageResourceID () {

        return imageResourceID;
    }

    /**
     * Method to get the word to be pronounced
     */
    public int getPronunciation() {

        return mPronunciation;
    }
}


