/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends FragmentActivity {

    /* Required Views Storing referencecs  */
    protected ImageView mBottom1;
    protected ImageView mBottom2;
    protected ImageView mTopCenter;
    protected ImageView mCenterCenter;
    protected ImageView mRightTop;
    protected ImageView mRightCenter;
    protected ImageView mRightBottom;
    protected ImageView mCenterLeft;
    protected ImageView mCenterLeft2;
    protected ImageView mCenterRight;
    protected ImageView mCenterRight2;

    protected Button mStartLearningButton;

    protected RelativeLayout mTheSplashScreen;

    /* Array to store all the Splash Images*/
    ArrayList<ImageView> mSplashImagesList;

    /* Setting variables to store the Fragments related */
    FragmentManager mFragmentManager;

    /* Instance Variable to store the ViewPager */
    protected ViewPager mViewPager;

    /* Getting the fragmentstateadapter */
    private FragmentStatePagerAdapter mFragmentStatePagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the numbers_fragmentent.xml layout file
        setContentView(R.layout.the_splash_screen);

        getReferences();

        /* Setting the button visible or gone */
        toggleTheButton();

        /* Setting onClickListerners on the StartLearnng Button */
        mStartLearningButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //toggleTheButton();
                //toggleTheFrame();

                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
            }
        });
        /* Populating the Splash images theList */
        mSplashImagesList = new ArrayList<>();
        mSplashImagesList.add(mBottom1);
        mSplashImagesList.add(mBottom2);
        mSplashImagesList.add(mCenterCenter);
        mSplashImagesList.add(mRightTop);
        mSplashImagesList.add(mRightBottom);
        mSplashImagesList.add(mRightCenter);
        mSplashImagesList.add(mTopCenter);
        mSplashImagesList.add(mCenterLeft);
        mSplashImagesList.add(mCenterLeft2);
        mSplashImagesList.add(mCenterRight2);
        mSplashImagesList.add(mCenterRight);


        /* Shuffling the List */
        Collections.shuffle(mSplashImagesList);
    }

    /**
     * Method to toggle which view should cover the FrameLayout
     */
    private void toggleTheFrame(){

        if (mTheSplashScreen.getVisibility() == View.VISIBLE) {
            mTheSplashScreen.setVisibility(View.GONE);
            mViewPager.setVisibility(View.VISIBLE);
        } else {

            mTheSplashScreen.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.GONE);
        }
    }

    /**
     * MEthod to set the start learnjing buton visible
     */
    private void toggleTheButton() {

        if (mStartLearningButton.getVisibility() == View.VISIBLE) {
            mStartLearningButton.setVisibility(View.GONE);
        } else {
            mStartLearningButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Overriding the onResume()
     */
    @Override
    public void onResume(){
        super .onResume();

        /* Starting the splash animation */
        startSplashAnim();
    }

    /**
     * Method to get all the required references
     */
    private void getReferences() {

        /* Referencing to the Fragment Manager */
        mFragmentManager = getSupportFragmentManager();

        mBottom1 = (ImageView) findViewById(R.id.Bottom1);
        mBottom2 = (ImageView) findViewById(R.id.Bottom2);
        mTopCenter = (ImageView) findViewById(R.id.TopCenter);
        mCenterCenter = (ImageView) findViewById(R.id.CenterCenter);
        mRightTop = (ImageView) findViewById(R.id.RightTop);
        mRightCenter = (ImageView) findViewById(R.id.RightCenter);
        mRightBottom = (ImageView) findViewById(R.id.RightBottom);
        mStartLearningButton = (Button) findViewById(R.id.startLearning);
        mCenterLeft = (ImageView) findViewById(R.id.CenterLeft);
        mCenterLeft2 = (ImageView) findViewById(R.id.CenterLeft2);
        mCenterRight = (ImageView) findViewById(R.id.CenterRight);
        mCenterRight2 = (ImageView) findViewById(R.id.CenterRight2);

        mViewPager = (ViewPager) findViewById(R.id.theViewPager);

        mTheSplashScreen = (RelativeLayout) findViewById(R.id.theSplashScreen);

        //mFragmentStatePagerAdapter = new MyFragmentStatePagerAdapter(mFragmentManager);


    }

    /**
     * Method to start the splash anim
     */
    private void startSplashAnim() {
        mSplashImagesList.get(0).animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mStartLearningButton.animate().scaleXBy(0.20f).setDuration(500);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mSplashImagesList.get(1).animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mSplashImagesList.get(2).animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                mSplashImagesList.get(3).animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        mSplashImagesList.get(4).animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                            @Override
                                            public void onAnimationEnd(Animator animation) {
                                                super.onAnimationEnd(animation);
                                                mSplashImagesList.get(5).animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                                    @Override
                                                    public void onAnimationEnd(Animator animation) {
                                                        super.onAnimationEnd(animation);
                                                        mSplashImagesList.get(6).animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                                            @Override
                                                            public void onAnimationEnd(Animator animation) {
                                                                super.onAnimationEnd(animation);
                                                                mSplashImagesList.get(7).animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                                                    @Override
                                                                    public void onAnimationEnd(Animator animation) {
                                                                        super.onAnimationEnd(animation);
                                                                        mSplashImagesList.get(8).animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                                                            @Override
                                                                            public void onAnimationEnd(Animator animation) {
                                                                                super.onAnimationEnd(animation);
                                                                                mSplashImagesList.get(9).animate().alpha(1.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                                                                                    @Override
                                                                                    public void onAnimationEnd(Animator animation) {
                                                                                        super.onAnimationEnd(animation);
                                                                                        mSplashImagesList.get(10).animate().alpha(1.0f).setDuration(200);
                                                                                    }
                                                                                });
                                                                            }
                                                                        });
                                                                    }
                                                                });
                                                            }
                                                        });
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }

                });
            }
        });

    }
}