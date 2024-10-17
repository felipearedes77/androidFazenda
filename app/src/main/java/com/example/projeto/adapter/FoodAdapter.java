package com.example.projeto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto.databinding.FoodslistasBinding;
import com.example.projeto.model.Food;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private final ArrayList<Food> foodlist;
    private final Context context;

    public FoodAdapter(ArrayList<Food> foodlist, Context context) {
        this.foodlist = foodlist;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FoodslistasBinding listitem;
        listitem = FoodslistasBinding.inflate(LayoutInflater.from(context),parent,false);
        return new FoodViewHolder(listitem);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
    holder.binding.imgbanana.setBackgroundResource(foodlist.get(position).getImgFood());
    holder.binding.tiltlebanana.setText(foodlist.get(position).getNameFood());
    holder.binding.descricaobanana.setText(foodlist.get(position).getDescriscaoFood());
    holder.binding.txtpreco.setText(foodlist.get(position).getPreco());
    }

    @Override
    public int getItemCount() {
        return foodlist.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder{

        FoodslistasBinding binding;

        public FoodViewHolder(FoodslistasBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
