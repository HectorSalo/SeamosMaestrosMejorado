package com.google.firebase.example.seamosmejoresmaestros.Adaptadores;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.firebase.example.seamosmejoresmaestros.Fragments.EventosFragment;
import com.google.firebase.example.seamosmejoresmaestros.Fragments.TemporizadorFragment;

public class InicioAdapter extends FragmentStatePagerAdapter {
    public InicioAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                EventosFragment eventosFragment = new EventosFragment();
                return eventosFragment;

            case 1:
                TemporizadorFragment temporizadorFragment = new TemporizadorFragment();
                return temporizadorFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
