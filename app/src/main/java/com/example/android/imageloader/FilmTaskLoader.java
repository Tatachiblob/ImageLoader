package com.example.android.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.AsyncTaskLoader;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by inoue on 24/03/2018.
 */

public class FilmTaskLoader extends AsyncTaskLoader<ArrayList<Movie>> {

    /** Tag for log messages */
    private static final String LOG_TAG = FilmTaskLoader.class.getName();

    /** Query URL */
    private String query;

    public FilmTaskLoader(Context context, String query){
        super(context);
        this.query = "Star Wars";
    }


    @Override
    public ArrayList<Movie> loadInBackground() {
        ArrayList<Movie> movies = new ArrayList<>();
        String filmSearchJson = NetworkUtils.httpGetRequestToAny("Resident Evil");

        if(!TextUtils.isEmpty(filmSearchJson)){
            try{
                JSONObject baseObject = new JSONObject(filmSearchJson);
                if(baseObject.getInt("total_results") == 0)
                    throw new JSONException("Empty Result");
                JSONArray jsonArr = baseObject.getJSONArray("results");

                for(int i = 0; i < jsonArr.length(); i++) {
                    JSONObject curObj = jsonArr.getJSONObject(i);
                    String id = "" + curObj.getInt("id");
                    String title = curObj.getString("title");
                    String releaseDate = curObj.optString("release_date");
                    String genre = "Action Thriller";
                    String imageSource = "";
                    URL imageUrl = null;
                    Bitmap imageBmp = null;
                    if(curObj.optString("poster_path") != null){
                        imageSource = "https://image.tmdb.org/t/p/w300" + curObj.getString("poster_path");
                        imageUrl = new URL(imageSource);
                        imageBmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
                    }
                    Movie m = new Movie(title, genre, releaseDate, imageBmp);
                    movies.add(m);
                }

            }catch(JSONException e){
                e.printStackTrace();
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        return movies;
    }

    @Override
    protected void onStartLoading(){forceLoad();}

}