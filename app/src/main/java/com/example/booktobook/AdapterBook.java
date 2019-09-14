package com.example.booktobook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterBook extends RecyclerView.Adapter<AdapterBook.ViewHolder> {

    private ArrayList<BookData> bookDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView cover;
        public TextView title;
        public TextView author;
        public TextView publisher;
        public TextView haver;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.item_book_imageView_cover);
            title = itemView.findViewById(R.id.item_book_textView_title);
            author = itemView.findViewById(R.id.item_book_textView_author);
            publisher = itemView.findViewById(R.id.item_book_textView_publisher);
            haver = itemView.findViewById(R.id.item_book_textView_haver);

        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterBook(ArrayList<BookData> dataset) {
        bookDataSet = dataset;
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

//       holder.cover.setImageResource(bookDataSet.get(position).book_image);
       holder.title.setText(bookDataSet.get(position).title);
       holder.author.setText(bookDataSet.get(position).author);
       holder.publisher.setText(bookDataSet.get(position).publisher);
       holder.haver.setText(bookDataSet.get(position).haver);
    }

    @Override
    public int getItemCount() {
        return bookDataSet.size();
    }


}


