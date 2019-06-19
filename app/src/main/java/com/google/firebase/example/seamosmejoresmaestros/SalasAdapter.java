package com.google.firebase.example.seamosmejoresmaestros;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SalasAdapter extends FragmentStatePagerAdapter {
    public SalasAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                Sala1Fragment sala1Fragment = new Sala1Fragment();
                return sala1Fragment;

            case 1:
                Sala2Fragment sala2Fragment = new Sala2Fragment();
                return sala2Fragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
