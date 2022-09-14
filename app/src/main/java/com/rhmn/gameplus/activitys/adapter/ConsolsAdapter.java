package com.rhmn.gameplus.activitys.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rhmn.gameplus.R;
import com.rhmn.gameplus.database.Consols;
import com.rhmn.gameplus.database.SqlLite;
import com.rhmn.gameplus.databinding.RowConsolsBinding;
import com.rhmn.gameplus.utiles.Constants;
import com.rhmn.gameplus.utiles.TrashId;

import java.util.ArrayList;

public class ConsolsAdapter extends RecyclerView.Adapter<ConsolsAdapter.ViewHolder> {

    private ArrayList<Consols> genres ;

    private Context context ;
    private boolean a ;
    SqlLite sqlLite;

    public ConsolsAdapter(Context context , ArrayList<Consols> genres, boolean a) {
        this.context = context ;
        this.genres = genres ;
        this.a = a;
        Log.d(Constants.TAG, "c: "+a);



    }


    @NonNull
    @Override
    public ConsolsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext() ;
        RowConsolsBinding v = RowConsolsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ConsolsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsolsAdapter.ViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {

        Consols item = genres.get(position);

        holder.binding.name.setText(item.name);
//        holder.binding.image.setImageDrawable(R.drawable.xbox);
        holder.binding.genre.setText(item.genre);
        if (item.genre.equals("PS4")){
            holder.binding.image.setImageResource(R.drawable.ps4);
        }

        if (item.genre.equals("PS5")){
            holder.binding.image.setImageResource(R.drawable.ps5);

        }

        if (item.genre.equals("Xbox")){
            holder.binding.image.setImageResource(R.drawable.xbox);

        }


        sqlLite = new SqlLite(context);

        if (a) {
            holder.binding.relativeClick.setOnClickListener(view -> {



                if (!item.isSelected()) {
                    holder.binding.relativeClick.setBackgroundResource(R.drawable.background_trash);
                    sqlLite.InsertId(item.id);



                } else {
                    holder.binding.relativeClick.setBackgroundResource(R.drawable.backgroind_cancel_button);
                    sqlLite.deleteId(item.id);
                }

                item.setSelected(!item.isSelected());

            });
        }else {
            holder.binding.relativeClick.setBackgroundResource(R.drawable.backgroind_cancel_button);
            sqlLite.deleteId(item.id);


        }




    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RowConsolsBinding binding;

        public ViewHolder(@NonNull RowConsolsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}