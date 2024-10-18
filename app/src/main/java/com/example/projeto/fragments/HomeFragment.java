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
import com.example.projeto.R;
import com.example.projeto.adapter.ItensPHAdapter;
import com.example.projeto.arrays.ConstrantsHomes;
import com.example.projeto.databinding.FragmentHomeBinding;
import com.example.projeto.model.ItensParaHome;
import com.example.projeto.model.RecyclerItemClickListener;
import com.example.projeto.tela_principal;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentHomeBinding binding;
    private ItensPHAdapter itensPHAdapter;
    private ArrayList<ItensParaHome> homeList;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onViewCreated(view ,savedInstanceState);

        homeList = ConstrantsHomes.getHomeData();
        RecyclerView recyclerViewHome = binding.RecyclerViewHome;
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewHome.setHasFixedSize(true);
        itensPHAdapter = new ItensPHAdapter(homeList,getContext());
        recyclerViewHome.setAdapter(itensPHAdapter);
        recyclerViewHome.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerViewHome, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.d("rapid", "onItemClick position: " + position);
                        if(position==1){
                            callProdutos();
                        }
                        else{
                            if (position==2)
                                callPerfil();
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
    private void callProdutos() {
        Intent intent = new Intent(getActivity(), tela_principal.class);
        startActivity(intent);
    }
    private void callPerfil() {
        Intent intent = new Intent(getActivity(), FormLogin.class);
        startActivity(intent);
    }
}