package com.example.android.miwok;

import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Main2Activity extends AppCompatActivity {

    protected ViewPager mViewPager;
    protected FragmentPagerAdapter mFragmentStatePagerAdapter;
    protected FragmentManager mFragmentManager;
    protected TabLayout mTabLayout;
    protected  ImageView mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mFragmentManager = getSupportFragmentManager();
        mViewPager = (ViewPager) findViewById(R.id.theViewPager);

        mFragmentStatePagerAdapter = new MyFragmentStatePagerAdapter(mFragmentManager);
        /* Setting the apdapter on the pager */
        mViewPager.setAdapter(mFragmentStatePagerAdapter);

        /*Getting the tab layout */
        mTabLayout = (TabLayout) findViewById(R.id.theTabLayout);
        mTabLayout.setupWithViewPager(mViewPager);


    }

    public class MyFragmentStatePagerAdapter extends FragmentPagerAdapter {

        private final int PAGE_COUNT = 4;
        private String TAB_TITLES[] = new String[] {"Numbers", "Colors", "Family", "Phrases"};

        public MyFragmentStatePagerAdapter(FragmentManager fragmentManager){

            super(fragmentManager);
        }

        @Override
        public int getCount(){

            return 4;
        }

        @Override
        public Fragment getItem(int position) {


            if (position == 0) {
                return new NumbersFragment();
            } else if (position == 1){
                return new Colors();
            } else if (position == 2) {
                return new FamilyMembers();
            } else {
                return new Phrases();
            }

            /*switch (position) {

                case 0:
                    return new NumbersFragment();

                case 1:
                    return new Colors();

                case 2:
                    return new FamilyMembers();

                case 3:
                    return new Phrases();

                default:
                    return new NumbersFragment();
            }*/
        }

        @Override
        public CharSequence getPageTitle(int position){
            return TAB_TITLES[position];
        }
    }
}
