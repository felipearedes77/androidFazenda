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

import com.example.projeto.FormCadastro;
import com.example.projeto.FormLogin;
import com.example.projeto.R;
import com.example.projeto.adapter.ItensPHAdapter;
import com.example.projeto.arrays.ConstrantsHomes;
import com.example.projeto.arrays.ConstrantsPerfil;
import com.example.projeto.databinding.FragmentHomeBinding;
import com.example.projeto.databinding.FragmentPerfilBinding;
import com.example.projeto.databinding.FragmentProdutosBinding;
import com.example.projeto.model.ItensParaHome;
import com.example.projeto.model.RecyclerItemClickListener;
import com.example.projeto.tela_principal;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FragmentPerfilBinding binding;
    private ItensPHAdapter itensPHAdapter;
    private ArrayList<ItensParaHome> perfiList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        binding = FragmentPerfilBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view ,savedInstanceState);

        perfiList = ConstrantsPerfil.getPerfilData();
        RecyclerView recyclerViewPerfil = binding.RecyclerViewPerfil;
        recyclerViewPerfil.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewPerfil.setHasFixedSize(true);
        itensPHAdapter = new ItensPHAdapter(perfiList ,getContext());
        recyclerViewPerfil.setAdapter(itensPHAdapter);
        recyclerViewPerfil.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerViewPerfil, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("rapid", "onItemClick position: " + position);
                        if(position==0){

                        }
                        else{
                            if (position==1)
                                callPerfil();
                            else {
                                if (position==2){
                                callcadastro();
                                }
                            }
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
    private void callcadastro() {
        Intent intent = new Intent(getActivity(), FormCadastro.class);
        startActivity(intent);
    }
    private void callPerfil() {
        Intent intent = new Intent(getActivity(), FormLogin.class);
        startActivity(intent);
    }
}