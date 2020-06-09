package com.dsa.grupo2.CoronavirusGameAndroid;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class RankingPageAdapter extends FragmentPagerAdapter {
    private int tabNumber;

    public RankingPageAdapter(@NonNull FragmentManager fm, int tabNumber) {
        super(fm);
        this.tabNumber= tabNumber;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RankingTabUserLevels();
            case 1:
                return new RankingTabGlobalLevels();
            case 2:
                return new RankingTabUsers();
            default:
                Log.d("","FRAGMENT MANAGER RETURNS NULL!!!! RankingPageAdapter.java (30)");
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabNumber;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
