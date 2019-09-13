package com.example.booktobook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShelfFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<MyBookData> dataArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shelf,container,false);

        recyclerView  = view.findViewById(R.id.recycler_view_shelf);

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
