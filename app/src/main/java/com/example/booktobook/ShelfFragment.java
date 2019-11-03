package com.example.booktobook;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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


        FirebaseFirestore db = FirebaseFirestore.getInstance();

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


        DocumentReference documentReference = db.collection("Users").document(id);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if(documentSnapshot.exists()) {
                        if (documentSnapshot.getData().get("myBook") != null) {
                            List list = (List) documentSnapshot.getData().get("myBook");
                            for (int i = 0; i < list.size(); i++) {
                                HashMap map = (HashMap) list.get(i);
                                dataArrayList.add(new MyBookData(
                                        map.get("book_image").toString(),
                                        map.get("title").toString(),
                                        "저자:" + map.get("author").toString(),
                                        "출판사:" + map.get("publisher").toString()));


                                adapter.notifyDataSetChanged();

                            }
                        }
                    }
                    else{
                        Log.d("TAG", "No such document");
                    }
                }
                else {
                    Log.d("TAG", "get failed with ", task.getException());
                }
            }
        });






        return view;
    }
}
