package com.example.android.booksinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookDetails extends AppCompatActivity {

    ImageView image;
    TextView bookName,authorName,publishedBy,publishDate,bookDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        image=findViewById(R.id.detailed_book_image);
        bookName=findViewById(R.id.detailed_book_title);
        authorName=findViewById(R.id.detailed_book_author);
        publishedBy=findViewById(R.id.detailed_published_by);
        publishDate=findViewById(R.id.detailed_published_date);
        bookDesc=findViewById(R.id.detailed_book_desc);

        Intent intent = getIntent();

        if(intent.getStringExtra("imageLink").length() !=0)
        {
            Glide.with(getApplicationContext())
                    .load(intent.getStringExtra("imageLink"))
                    .placeholder(R.drawable.reading)
                    .into(image);
        }

        else
        {
            image.setImageResource(R.drawable.reading);
        }


        bookName.setText(intent.getStringExtra("bookName"));
        authorName.setText(intent.getStringExtra("authorName"));
        publishedBy.setText("Published By : "+intent.getStringExtra("publishedBy"));
        publishDate.setText("Published Date : "+intent.getStringExtra("publishedDate"));
        bookDesc.setText("Description : "+intent.getStringExtra("bookDesc"));





    }
}
