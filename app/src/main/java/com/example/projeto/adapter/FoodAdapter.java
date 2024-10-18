package com.example.projeto.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto.databinding.FoodslistasBinding;
import com.example.projeto.model.Food;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private ArrayList<Food> foodList;
    private final Context context;


    public FoodAdapter(ArrayList<Food> foodList, Context context ) {
        this.foodList= foodList;
        this.context = context;

    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodslistasBinding binding;
        binding = FoodslistasBinding.inflate(LayoutInflater.from(context),parent,false);
        return new FoodViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {

    holder.binding.imgbanana.setBackgroundResource(foodList.get(position).getImgFood());
    holder.binding.tiltlebanana.setText(foodList.get(position).getNameFood());
    holder.binding.descricaobanana.setText(foodList.get(position).getDescriscaoFood());
    holder.binding.txtpreco.setText(foodList.get(position).getPreco());
    }


    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder{

        FoodslistasBinding binding;


        public FoodViewHolder(FoodslistasBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
