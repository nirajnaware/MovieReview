package com.example.moviereview.Room

import androidx.room.Dao
import androidx.room.Query
import com.example.moviereview.model.MovieModel

@Dao
abstract class MovieDao : BaseDao<MovieModel>{

    @Query("SELECT * FROM MovieModel")
    abstract fun getMovieList(): List<MovieModel>

    @Query("SELECT * FROM MovieModel WHERE id = :movieId")
    abstract fun getMovie(movieId: Int): MovieModel

    @Query("DELETE FROM MovieModel")
    abstract fun deleteAllMovieList()

    @Query("UPDATE MovieModel SET rating = :rating WHERE id = :movieId")
    abstract fun updateMovieRatingByMovieId(
        rating: String,
        movieId: Int
    )

}