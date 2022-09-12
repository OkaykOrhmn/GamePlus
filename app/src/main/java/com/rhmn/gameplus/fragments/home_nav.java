package com.rhmn.gameplus.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.rhmn.gameplus.R;
import com.rhmn.gameplus.activitys.adapter.ConsolsAdapter;
import com.rhmn.gameplus.activitys.adapter.Trash;
import com.rhmn.gameplus.database.Consols;
import com.rhmn.gameplus.database.SqlLite;
import com.rhmn.gameplus.databinding.FragmentHomeNavBinding;
import com.rhmn.gameplus.utiles.Constants;
import com.rhmn.gameplus.utiles.ViewAnimation;

import java.util.ArrayList;
import java.util.Objects;

public class home_nav extends Fragment {
    private FragmentHomeNavBinding binding;

    private boolean rotate = false;
    private boolean show_out = false;
    private SqlLite sqlLite;
    private ConsolsAdapter adapter;
    private ArrayList<Consols> consolsArrayList;
    private boolean isClicked = false;



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
        Recycl();




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
                RadioGroup radioGroup = dialog.findViewById(R.id.groupradio);
                EditText name = dialog.findViewById(R.id.name_consol);
                Button cancel = dialog.findViewById(R.id.cancel_dialog);
                Button accept = dialog.findViewById(R.id.accept_dialog);
                dialog.show();



                cancel.setOnClickListener(view12 -> {
                    dialog.dismiss();
                });

                accept.setOnClickListener(view1 -> {
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    RadioButton radio = (RadioButton) radioGroup.findViewById(selectedId);
                    if (name.getText().length() != 0 &&
                            radioGroup.getCheckedRadioButtonId() != -1){

                        if (sqlLite.getByName(name.getText().toString(),
                                radio.getText().toString()) ){
//                            Snackbar.make(view1, "کنسول موجود است", Toast.LENGTH_SHORT).show();
                            MyToast("کنسول موجود است");
                        }else {
                            Log.d(Constants.TAG, "onClick: " + selectedId);
                            sqlLite = new SqlLite(getContext());
                            sqlLite.Insert(name.getText().toString(), radio.getText().toString());
                            dialog.dismiss();
//                            Snackbar.make(getView(), "کنسول با موفقیت اضافه شد D:", Toast.LENGTH_SHORT).show();
                            MyToast("کنسول با موفقیت اضافه شد D:");
                            Recycl();
                        }

                    }else{
//                        Toast.makeText(getContext(), "لطفا فرم را تکمیل کنید ):", Toast.LENGTH_SHORT).show();
                        MyToast("لطفا فرم را تکمیل کنید ):");
                    }
                });

            }
        });

        ViewAnimation.initShowOut(fab_creat);
        ViewAnimation.initShowOut(fab_delete);


        binding.fabAdd.setOnClickListener(view -> {

            if (show_out){

                show_out = false;
                ViewAnimation.showIn(fab_creat);
                isClicked = false;
                Recycl();
            }else {

                rotate = ViewAnimation.rotateFab(view , !rotate);
                if (rotate) {
                    ViewAnimation.showIn(fab_creat);
                    ViewAnimation.showIn(fab_delete);
                } else {
                    ViewAnimation.showOut(fab_creat);
                    ViewAnimation.showOut(fab_delete);
                }
            }


            });

        binding.fabTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!show_out){
                    ViewAnimation.showOut(fab_creat);
                    isClicked = true;
                    show_out = true;
                    Recycl();
                }else {



                }
            }
        });

    }

    public void Recycl(){

        sqlLite = new SqlLite(getContext());
        consolsArrayList = sqlLite.getDataConsols();
        adapter = new ConsolsAdapter(getContext(), consolsArrayList, isClicked);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recycler.setLayoutManager(linearLayoutManager);
        binding.recycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void MyToast(String s){

        View layout = getLayoutInflater().inflate(R.layout.toast_custom,
                (ViewGroup) getView().findViewById(R.id.custom_toast_layout_id));
        TextView text = (TextView) layout.findViewById(R.id.text_toast);
        text.setTextColor(Color.WHITE);
        text.setText(s);

        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }
}