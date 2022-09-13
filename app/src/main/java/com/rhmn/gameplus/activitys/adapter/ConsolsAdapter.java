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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rhmn.gameplus.R;
import com.rhmn.gameplus.database.Consols;
import com.rhmn.gameplus.database.SqlLite;
import com.rhmn.gameplus.databinding.RowConsolsBinding;
import com.rhmn.gameplus.utiles.Constants;

import java.util.ArrayList;

public class ConsolsAdapter extends RecyclerView.Adapter<ConsolsAdapter.ViewHolder> {

    private ArrayList<Consols> genres ;
    private Context context ;



    public ConsolsAdapter(Context context , ArrayList<Consols> genres) {
        this.context = context ;
        this.genres = genres ;


    }


    @NonNull
    @Override
    public ConsolsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext() ;
        RowConsolsBinding v = RowConsolsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ConsolsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsolsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.name.setText(genres.get(position).name);
//        holder.binding.image.setImageDrawable(R.drawable.xbox);
        holder.binding.genre.setText(genres.get(position).genre);
        if (genres.get(position).genre.equals("PS4")){
            holder.binding.image.setImageResource(R.drawable.ps4);
        }

        if (genres.get(position).genre.equals("PS5")){
            holder.binding.image.setImageResource(R.drawable.ps5);

        }

        if (genres.get(position).genre.equals("Xbox")){
            holder.binding.image.setImageResource(R.drawable.xbox);

        }


            holder.binding.relativeClick.setOnClickListener(view -> {


                if (!genres.get(position).isSelected()) {
                    holder.binding.relativeClick.setBackgroundResource(R.drawable.background_trash);
                }else {
                    holder.binding.relativeClick.setBackgroundResource(R.drawable.backgroind_cancel_button);
                }
                    genres.get(position).setSelected(!genres.get(position).isSelected());

            });



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