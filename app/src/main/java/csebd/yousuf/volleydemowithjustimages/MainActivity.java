package csebd.yousuf.volleydemowithjustimages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private CustomAdapter adapter;
    private List<Movie> movieList = new ArrayList<>();
    private static final String url = "http://api.androidhive.info/json/movies.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomAdapter(this, movieList);
        Log.d("www.d.com", "Size : " + movieList.size());
        listView.setAdapter(adapter);

        // json req
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("www.d.com", response.toString());
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Log.d("www.d.com", i + " Times");
                        Movie movie = new Movie();
                        movie.setThumbnailUrl(obj.getString("image"));
                        movie.setTitle(obj.getString("title"));
                        movie.setRating(obj.getDouble("rating"));
                        movie.setYear((Integer) obj.get("releaseYear"));

                        JSONArray genreArray = obj.getJSONArray("genre");
                        ArrayList<String> genre = new ArrayList<>();
                        for (int x = 0 ; x < genreArray.length(); x++) {
                            genre.add((String) genreArray.get(x));
                        }
                        movie.setGenre(genre);

                        movieList.add(movie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("www.d.com", "ERROr !!");

            }
        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);


    Log.d("www.d.com", "Last ");
    Log.d("www.d.com", "Last size : "+ movieList.size());
    }
}
