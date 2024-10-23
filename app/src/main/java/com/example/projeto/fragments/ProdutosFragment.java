package com.example.projeto.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projeto.FormLogin;
import com.example.projeto.adapter.ItensPHAdapter;
import com.example.projeto.arrays.ConstrantsProdutos;
import com.example.projeto.databinding.FragmentProdutosBinding;
import com.example.projeto.model.Food;
import com.example.projeto.model.ItensParaHome;
import com.example.projeto.model.RecyclerItemClickListener;
import com.example.projeto.tela_principal;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProdutosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProdutosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentProdutosBinding binding;
    private ItensPHAdapter itensPHAdapter;
    private ArrayList<ItensParaHome> produtosList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProdutosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProdutosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProdutosFragment newInstance(String param1, String param2) {
        ProdutosFragment fragment = new ProdutosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProdutosBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view ,savedInstanceState);

        produtosList = ConstrantsProdutos.getProdutostData();
        RecyclerView recyclerViewProdutos = binding.RecyclerViewProdutos;
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProdutos.setHasFixedSize(true);
        itensPHAdapter = new ItensPHAdapter(produtosList,getContext());
        recyclerViewProdutos.setAdapter(itensPHAdapter);
        recyclerViewProdutos.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerViewProdutos, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("rapid", "onItemClick position: " + position);
                        if(position==0){
                            callFrutas();
                        }
                        else{
                            if (position==2)
                                callDoa();
                        }

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Log.d("rapid", "onItemLongClick pos = " + position);
                    }
                })
        );
    }
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Prevent memory leaks by nulling the binding
    }
    private void callFrutas() {
        Intent intent = new Intent(getActivity(), tela_principal.class);
        startActivity(intent);
    }
    private void callDoa() {
        Intent intent = new Intent(getActivity(), FormLogin.class);
        startActivity(intent);
    }
}