package com.example.lab5solution

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lab5solution.data.Movie
import java.util.*

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var movieList: List<Movie> = ArrayList<Movie>()
    var context: Context? = null

    constructor(myMovieList: List<Movie?>, mainMenu: MainMenu) : this() {
        movieList = myMovieList as List<Movie>
        this.context = mainMenu
    }

    class MovieViewHolder : ViewHolder {
        var idTextView: TextView? = null
        var durationTextView: TextView? = null
        var movieItemView: View?= null

        constructor(@NonNull itemView: View) : super(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        val itemView: View = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_view,
            parent,
            false
        )
        val myHolder = MovieViewHolder(itemView)

        myHolder.idTextView = itemView.findViewById(R.id.id)
        myHolder.durationTextView = itemView.findViewById(R.id.duration)
        myHolder.movieItemView = itemView

        return myHolder
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.idTextView?.text = movieList.get(position).movie_id
        holder.durationTextView?.text = movieList.get(position).duration
        holder.movieItemView?.setBackgroundColor(Color.parseColor("#FAFAFA"))

    }

    override fun getItemCount(): Int {
        return  movieList.size
    }
}