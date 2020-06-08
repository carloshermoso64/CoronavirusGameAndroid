package com.dsa.grupo2.CoronavirusGameAndroid;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class RankingPageAdapter extends FragmentPagerAdapter {
    private int tabNumber;
    private Context context;

    public RankingPageAdapter(@NonNull FragmentManager fm, int tabNumber, Context context) {
        super(fm);
        this.tabNumber= tabNumber;
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new RankingTabUserLevels();
            case 1:
                return new RankingTabGlobalLevels();
            case 3:
                return new RankingTabUsers();
            default:
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

    public Context getContext(){return this.context;};
}
