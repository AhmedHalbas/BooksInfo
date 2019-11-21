package com.example.android.booksinfo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText bookSearch;
    private Button searchButton;
    private ImageView imageStart;
    private ProgressBar progressBar;
    private ListView listView;
    private Adapter adapter;
    private String bookTitle, GOOGLE_BOOKS_API;
    private ArrayList<Book> books;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bookSearch = findViewById(R.id.book_search_et);
        searchButton = findViewById(R.id.search_btn);
        imageStart = findViewById(R.id.image_start);
        progressBar = findViewById(R.id.progressbar);
        listView = findViewById(R.id.listview);

        progressBar.setVisibility(View.INVISIBLE);

        adapter = new Adapter(getApplicationContext(), new ArrayList<Book>());

        listView.setAdapter(adapter);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bookTitle = bookSearch.getText().toString();

                if (bookTitle.length() == 0) {
                    Toast.makeText(MainActivity.this, "Please Enter Book Name", Toast.LENGTH_SHORT).show();
                } else {

                    imageStart.setVisibility(View.GONE);

                    GOOGLE_BOOKS_API = "https://www.googleapis.com/books/v1/volumes?q=" + bookTitle;

                    BooksDataAsyncTask booksData = new BooksDataAsyncTask();

                    booksData.execute(GOOGLE_BOOKS_API);

                    bookSearch.setText("");


                }


            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(MainActivity.this, BookDetails.class);


                intent.putExtra("imageLink",books.get(i).getImageURL() );
                intent.putExtra("bookName",books.get(i).getTitle() );
                intent.putExtra("authorName",books.get(i).getAuthor() );
                intent.putExtra("publishedBy",books.get(i).getPublisher() );
                intent.putExtra("publishedDate",books.get(i).getDate() );
                intent.putExtra("bookDesc",books.get(i).getDescription() );

                startActivity(intent);

            }
        });


    }


    public class BooksDataAsyncTask extends AsyncTask<String, Void, ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(String... strings) {

            if (strings.length < 1 || strings[0] == null) {
                return null;
            }

            books = Utils.fetchBooksData(strings[0]);

            return books;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }


        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            super.onPostExecute(books);

            adapter.clear();

            if (books != null && !books.isEmpty()) {
                progressBar.setVisibility(View.INVISIBLE);
                adapter.addAll(books);
            }


        }
    }
}
