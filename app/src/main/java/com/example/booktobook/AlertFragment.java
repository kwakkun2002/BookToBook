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

public class AlertFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<AlertData> dataArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_alert,container,false);

        recyclerView = view.findViewById(R.id.recycler_view_alert);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        //set adapter
        dataArrayList = new ArrayList<>();
        adapter = new AdapterAlertFragment(dataArrayList);
        recyclerView.setAdapter(adapter);


        dataArrayList.add(new AlertData(
           "빌림",
           2019,
           9,
           12,
           "나미야 잡화점의 기적",
           "곽건",
           "2학년 6반"
        ));

        dataArrayList.add(new AlertData(
                "빌림",
                2019,
                9,
                12,
                "나미야 잡화점의 기적",
                "곽건",
                "2학년 6반"
        ));


        dataArrayList.add(new AlertData(
                "빌림",
                2019,
                9,
                12,
                "나미야 잡화점의 기적",
                "곽건",
                "2학년 6반"
        ));


        dataArrayList.add(new AlertData(
                "빌림",
                2019,
                9,
                12,
                "나미야 잡화점의 기적",
                "곽건",
                "2학년 6반"
        ));


        dataArrayList.add(new AlertData(
                "빌림",
                2019,
                9,
                12,
                "나미야 잡화점의 기적",
                "곽건",
                "2학년 6반"
        ));


        return view;
    }
}
