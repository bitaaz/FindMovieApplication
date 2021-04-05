package com.example.findmovieapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.findmovieapplication.R;
import com.example.findmovieapplication.model.MovieModel;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnMovieListener onMovieListener;
    private List<MovieModel> movies;

    public MovieRecyclerViewAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    public void setMovies(List<MovieModel> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);

        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MovieViewHolder)holder).title.setText(movies.get(position).getTitle());
        ((MovieViewHolder)holder).language.setText(movies.get(position).getOriginal_language());
        ((MovieViewHolder)holder).releaseDate.setText(movies.get(position).getRelease_date());
        ((MovieViewHolder)holder).ratingBar.setRating((movies.get(position).getVote_average())/2);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + movies.get(position).getPoster_path())
                .into(((MovieViewHolder) holder).imageView);


    }

    @Override
    public int getItemCount() {
        if (movies == null){

            return 0;

        }

        return movies.size();
    }

    public MovieModel getSelectedMovie(int position){

        if (movies != null){

            if (movies.size() > 0){

                return movies.get(position);

            }

        }
        return null;
    }
}
