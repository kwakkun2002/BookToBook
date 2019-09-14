package com.example.booktobook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class ShelfFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textView_shelf;
    private ArrayList<MyBookData> dataArrayList;
    private String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shelf,container,false);

        recyclerView  = view.findViewById(R.id.recycler_view_shelf);
        textView_shelf = view.findViewById(R.id.fragment_shelf_myShelf_textView);


        //get id from sharedPreference
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        id = pref.getString("ID", "");

        textView_shelf.setText(id+"님의 책장");




        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        dataArrayList = new ArrayList<>();
        adapter = new AdapterShelfFragment(dataArrayList);
        recyclerView.setAdapter(adapter);


        dataArrayList.add(new MyBookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사"));
        dataArrayList.add(new MyBookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사"));
        dataArrayList.add(new MyBookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사"));
        dataArrayList.add(new MyBookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사"));
        dataArrayList.add(new MyBookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사"));
        dataArrayList.add(new MyBookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사"));



        return view;
    }
}
