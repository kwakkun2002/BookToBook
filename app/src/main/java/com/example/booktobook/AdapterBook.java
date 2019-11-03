package com.example.booktobook;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class AdapterBook extends RecyclerView.Adapter<AdapterBook.ViewHolder> {

    private ArrayList<BookData> bookDataSet;
    private static String id;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView cover;
        public TextView title;
        public TextView author;
        public TextView publisher;
        public TextView haver;
        public TextView place;
        public TextView time;
        public Button borrow;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.item_book_imageView_cover);
            title = itemView.findViewById(R.id.item_book_textView_title);
            author = itemView.findViewById(R.id.item_book_textView_author);
            publisher = itemView.findViewById(R.id.item_book_textView_publisher);
            haver = itemView.findViewById(R.id.item_book_textView_haver);
            place = itemView.findViewById(R.id.item_book_textView_place);
            time = itemView.findViewById(R.id.item_book_textView_time);
            borrow = itemView.findViewById(R.id.item_book_button_borrow);



            borrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    DocumentReference documentReference = db.collection("Users")
                            .document(haver.getText().toString().substring(3));
                    documentReference.update("alert", FieldValue.arrayUnion(
                            new Alert(
                                    place.getText().toString().substring(3),
                                    time.getText().toString().substring(3),
                                    "빌려줌",
                                    title.getText().toString().substring(3),
                                    id.toString()
                            )
                    ));
                    documentReference = db.collection("Users")
                            .document(id.toString());
                    documentReference.update("alert",FieldValue.arrayUnion(
                            new Alert(
                                    place.getText().toString().substring(3),
                                    time.getText().toString().substring(3),
                                    "빌림",
                                    title.getText().toString().substring(3),
                                    haver.getText().toString().substring(3)
                            )
                    ));
                }
            });


        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterBook(ArrayList<BookData> dataset,String id) {
        bookDataSet = dataset;
        AdapterBook.id = id;
    }

    // Create new views
    @NonNull
    @Override
    public AdapterBook.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book,parent,false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }




    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull AdapterBook.ViewHolder holder, int position) {

//        holder.cover.setImageResource(bookDataSet.get(position).book_image);

        Glide.with(holder.itemView.getContext())
                .load(bookDataSet.get(position).getBook_image())
                .into(holder.cover);
        holder.title.setText(bookDataSet.get(position).title);
        holder.author.setText(bookDataSet.get(position).author);
        holder.publisher.setText(bookDataSet.get(position).publisher);
        holder.haver.setText(bookDataSet.get(position).haver);
        holder.place.setText(bookDataSet.get(position).place);
        holder.time.setText(bookDataSet.get(position).time);


        Log.d("AdapterBook-test-title",bookDataSet.get(position).title);
    }

    @Override
    public int getItemCount() {
        return bookDataSet.size();
    }
}




