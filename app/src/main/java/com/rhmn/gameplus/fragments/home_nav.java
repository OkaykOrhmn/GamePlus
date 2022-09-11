package com.rhmn.gameplus.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rhmn.gameplus.R;
import com.rhmn.gameplus.databinding.FragmentHomeNavBinding;
import com.rhmn.gameplus.utiles.ViewAnimation;

public class home_nav extends Fragment {
    private FragmentHomeNavBinding binding;

    private boolean rotate = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeNavBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingButton();




        return root;
    }

    public void FloatingButton(){
        final FloatingActionButton fab_creat = binding.creatFab;
        final FloatingActionButton fab_delete = binding.fabTrash;

        fab_creat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_creat);
                dialog.show();
            }
        });

        ViewAnimation.initShowOut(fab_creat);
        ViewAnimation.initShowOut(fab_delete);
        binding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rotate = ViewAnimation.rotateFab(view, !rotate);
                if (rotate) {
                    ViewAnimation.showIn(fab_creat);
                    ViewAnimation.showIn(fab_delete);
                } else {
                    ViewAnimation.showOut(fab_creat);
                    ViewAnimation.showOut(fab_delete);
                }

            }
        });
    }
}