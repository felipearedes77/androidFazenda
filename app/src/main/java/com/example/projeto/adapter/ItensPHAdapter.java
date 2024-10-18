package com.example.projeto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto.databinding.TelahomelistasBinding;
import com.example.projeto.model.ItensParaHome;

import java.util.ArrayList;

public class ItensPHAdapter extends RecyclerView.Adapter<ItensPHAdapter.ItensPHViewHolder> {
    private ArrayList<ItensParaHome> homeList;
    private final Context context;

    public ItensPHAdapter(ArrayList<ItensParaHome> homeList, Context context) {
        this.homeList = homeList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItensPHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TelahomelistasBinding binding;
        binding = TelahomelistasBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ItensPHViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItensPHViewHolder holder, int position) {
        holder.binding.title2.setText(homeList.get(position).getTitle2());
    }

    @Override
    public int getItemCount() {
        return homeList.size();
    }

    public static class ItensPHViewHolder extends RecyclerView.ViewHolder{
        TelahomelistasBinding binding;
        public ItensPHViewHolder(TelahomelistasBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
