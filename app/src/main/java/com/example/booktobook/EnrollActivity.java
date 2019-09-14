package com.example.booktobook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EnrollActivity extends AppCompatActivity {

    private ImageView bookImageView;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView publisherTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_from_cam);

        bookImageView = findViewById(R.id.activity_enroll_ImageView_bookImg);
        titleTextView = findViewById(R.id.activity_enroll_textView_title);
        authorTextView = findViewById(R.id.activity_enroll_textView_author);
        publisherTextView = findViewById(R.id.activity_enroll_textView_publisher);

        Intent intent = getIntent();

        Bitmap bookimg = intent.getParcelableExtra("img");
        String title = intent.getExtras().getString("title");
        String publisher = intent.getExtras().getString("publisher");
        String author = intent.getExtras().getString("auth");


        bookImageView.setImageBitmap(bookimg);
        titleTextView.setText(title);
        authorTextView.setText(author);
        publisherTextView.setText(publisher);

    }
}
