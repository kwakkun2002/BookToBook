package com.example.booktobook;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<BookData> dataArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_from_search);

        recyclerView = findViewById(R.id.recycler_view_search);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
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


    }


}
