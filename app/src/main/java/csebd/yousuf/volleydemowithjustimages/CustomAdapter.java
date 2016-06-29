package csebd.yousuf.volleydemowithjustimages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by Yousuf on 07-06-16.
 */
public class CustomAdapter extends ArrayAdapter<Movie> {
    private Context context;
    ImageLoader imageLoader;

    public CustomAdapter(Context context, List<Movie> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_layout, parent, false);
        }
        NetworkImageView thumbnail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvRating = (TextView) convertView.findViewById(R.id.tvRating);
        TextView tvGenre = (TextView) convertView.findViewById(R.id.tvGenre);
        TextView tvYear = (TextView) convertView.findViewById(R.id.tvYear);
        // imageloader if condition // now not need
        imageLoader = AppController.getInstance().getImageLoader();
        thumbnail.setImageUrl(movie.getThumbnailUrl(), imageLoader);
        tvTitle.setText(movie.getTitle());
        tvRating.setText("Rating : "+  movie.getRating());
        tvYear.setText("Release Year : " + movie.getYear());
        String genreStr = "";
        for (String s : movie.getGenre()) {
            genreStr += s + "; ";
        }
        tvGenre.setText(genreStr);

        return convertView;
    }
}
