package com.example.lab5solution

import androidx.room.*
import com.example.lab5solution.data.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Update
    fun updateMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movies")
    fun getAllMovies():List<Movie>

    @Query("SELECT * FROM movies WHERE movie_id ==:inputId")
    fun getMovieById(inputId: String):Movie

    @Query("SELECT * FROM movies WHERE release_date ==:inputDate")
    fun searchByReleaseDate(inputDate: String):Movie
}