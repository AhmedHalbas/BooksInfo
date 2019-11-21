package com.example.android.booksinfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Book> {

    private ImageView image;
    private TextView bookTitle,authorName;


    public Adapter(@NonNull Context context, ArrayList<Book> books) {
        super(context,0, books);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if(view==null)
        {
            view= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        image=view.findViewById(R.id.image_list_item);
        bookTitle=view.findViewById(R.id.book_title_list_item);
        authorName=view.findViewById(R.id.book_author_list_item);

        Book book = getItem(position);

        if(book.getImageURL().length() !=0)
        {
            Glide.with(getContext())
                    .load(book.getImageURL())
                    .placeholder(R.drawable.reading)
                    .into(image);
        }

        else
        {
            image.setImageResource(R.drawable.reading);
        }



        bookTitle.setText(book.getTitle());
        authorName.setText(book.getAuthor());



        return view;
    }
}
