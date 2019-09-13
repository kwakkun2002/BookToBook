package com.example.booktobook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterShelfFragment extends RecyclerView.Adapter<AdapterShelfFragment.ViewHolder> {

    private ArrayList<MyBookData> bookDataSet;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView cover;
        public TextView title;
        public TextView author;
        public TextView publisher;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.item_mybook_imageView_cover);
            title = itemView.findViewById(R.id.item_mybook_textView_title);
            author = itemView.findViewById(R.id.item_mybook_textView_author);
            publisher = itemView.findViewById(R.id.item_mybook_textView_publisher);

        }
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterShelfFragment(ArrayList<MyBookData> dataset) {
        bookDataSet = dataset;
    }

    // Create new views
    @NonNull
    @Override
    public AdapterShelfFragment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mybook,parent,false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull AdapterShelfFragment.ViewHolder holder, int position) {

       holder.cover.setImageResource(bookDataSet.get(position).book_image);
       holder.title.setText(bookDataSet.get(position).title);
       holder.author.setText(bookDataSet.get(position).author);
       holder.publisher.setText(bookDataSet.get(position).publisher);
    }

    @Override
    public int getItemCount() {
        return bookDataSet.size();
    }
}


