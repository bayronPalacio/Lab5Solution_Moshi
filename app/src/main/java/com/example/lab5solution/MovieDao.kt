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
    fun deleteMovie(student: Movie)

    @Query("SELECT * FROM movies")
    fun getAllStudents():List<Movie>

//    @Query("SELECT * FROM movies WHERE movie_id ==:inputMovieId")
//    fun getStudentByEmail(inputEmail: String):Movie
}