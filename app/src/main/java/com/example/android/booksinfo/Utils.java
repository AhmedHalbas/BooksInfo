package com.example.android.booksinfo;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Utils {

    private static String title,author,publisher,date,description,thumbnail;


    public static ArrayList<Book> fetchBooksData(String requestURL) {

        URL url = null;
        try {
            url = createURL(requestURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        String JSONResponse = null;
        try {
             JSONResponse = makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Book> books=null;

        try {
             books = extractFromJson(JSONResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return books;
    }




    public static URL createURL (String stringurl) throws MalformedURLException
    {
        // create a new object from URL
        URL url = null;

        // try and catch exception
        // initialize a new object
        url = new URL(stringurl);

        // return url
        return url;
    }

    public static String makeHttpRequest (URL url) throws IOException
    {
        String jsonresponse = "";

        if (url == null)
        {
            return jsonresponse;
        }

        HttpURLConnection urlConnection = null;

        InputStream inputStream = null;

        // open url connection
        urlConnection = (HttpURLConnection) url.openConnection();

        // set request method
        urlConnection.setRequestMethod("GET");

        // connect to server
        urlConnection.connect();

        // check if the request successful (response code = 200)
        // then read data from stream
        if (urlConnection.getResponseCode() == 200)
        {
            inputStream = urlConnection.getInputStream();
            jsonresponse = readFromStream(inputStream);
        }

        // if urlConnection return with data
        if (urlConnection != null)
        {
            // then disconnect (disconnect don't mean delete data from urlConnection)
            urlConnection.disconnect();
        }
        // if inputStream return with data
        if (inputStream != null)
        {
            // then close (close don't mean delete data from inputStream)
            inputStream.close();
        }
        return  jsonresponse;
    }

    public static String readFromStream (InputStream inputStream) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();

        if (inputStream != null)
        {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream
                    , Charset.defaultCharset());

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();

            while (line != null)
            {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    public static ArrayList<Book> extractFromJson (String jsonresponse) throws JSONException
    {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonresponse) && jsonresponse == null)
        {
            return null;
        }

        ArrayList<Book> booksdata = new ArrayList<>();



            JSONObject baseobject = new JSONObject(jsonresponse);

            JSONArray itemsarray = baseobject.getJSONArray("items");

            for (int i = 0; i < itemsarray.length(); i ++)
            {
                JSONObject item = itemsarray.getJSONObject(i);

                JSONObject info = item.getJSONObject("volumeInfo");

                if(info.has("title"))
                {
                    title=info.getString("title");

                }

                else
                {
                    title="Not Found";

                }


                if(info.has("authors"))
                {
                    author=info.getJSONArray("authors").getString(0);

                }

                else
                {
                    author="Not Found";

                }


                if(info.has("publisher"))
                {
                    publisher=info.getString("publisher");

                }

                else
                {
                    publisher="Not Found";

                }


                if(info.has("publishedDate"))
                {
                    date=info.getString("publishedDate");

                }

                else
                {
                    date="Not Found";

                }


                if(info.has("description"))
                {
                    description=info.getString("description");

                }

                else
                {
                    description="Not Found";

                }


                if(info.has("imageLinks"))
                {
                   JSONObject imageurl = info.getJSONObject("imageLinks");

                   thumbnail=imageurl.getString("thumbnail");

                }

                else
                {
                    thumbnail="";

                }

                booksdata.add(new Book(title,author,publisher,date,description,thumbnail));
            }





        return booksdata;

    }
}
