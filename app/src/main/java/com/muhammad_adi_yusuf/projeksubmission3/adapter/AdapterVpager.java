package com.muhammad_adi_yusuf.projeksubmission3.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterVpager extends FragmentPagerAdapter {
    private final List<Fragment> listFragment = new ArrayList<>();
    private final List<String> listTitle = new ArrayList<>();

    public AdapterVpager(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int poSition) {
        return listFragment.get(poSition);
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int poSition) {
        return listTitle.get(poSition);
    }

    public void AddFragment(Fragment fraGment, String tiTle) {
        listFragment.add(fraGment);
        listTitle.add(tiTle);

    }
}
