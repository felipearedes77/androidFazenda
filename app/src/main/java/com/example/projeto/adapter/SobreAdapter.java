package com.example.projeto.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projeto.databinding.SobrelayoutBinding;
import com.example.projeto.model.ItensSobre;

import java.util.ArrayList;

public class SobreAdapter extends RecyclerView.Adapter<SobreAdapter.SobreViewHolder> {
    private ArrayList<ItensSobre> sobreList;
    private Context context;

    public SobreAdapter(ArrayList<ItensSobre> sobreList, Context context) {
        this.sobreList = sobreList;
        this.context = context;
    }

    @NonNull
    @Override
    public SobreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SobrelayoutBinding binding;
        binding = SobrelayoutBinding.inflate(LayoutInflater.from(context),parent,false);
        return new SobreViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SobreViewHolder holder, int position) {
    holder.binding.textviewSobre.setText(sobreList.get(position).getTextviewSobre());
    holder.binding.textviewButtom.setText(sobreList.get(position).getTextviewButtom());
    }

    @Override
    public int getItemCount() {
        return sobreList.size();
    }

    public static class SobreViewHolder extends RecyclerView.ViewHolder{
        SobrelayoutBinding binding;
        public SobreViewHolder(SobrelayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
