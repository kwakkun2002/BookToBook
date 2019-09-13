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

public class ShowFragment extends Fragment{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<BookData> dataArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_show,container,false);

        recyclerView = view.findViewById(R.id.recycler_view_show);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        //set adapter
        dataArrayList = new ArrayList<>();
        adapter = new AdapterBook(dataArrayList);
        recyclerView.setAdapter(adapter);



        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));
        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));
        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));
        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));
        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));
        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));
        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));
        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));
        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));
        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));
        dataArrayList.add(new BookData(R.drawable.example_book_img,"엘저넌에게 꽃을","저자 : 대니얼키스","출판사 : 미루출판사","보유자 : 곽건"));



        return view;
    }






}
