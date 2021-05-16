package com.example.moviereview

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.moviereview.Room.AppDb
import com.example.moviereview.databinding.ActivityMoviesDetailsBinding
import com.example.moviereview.model.MovieModel
import com.example.moviereview.util.keyMovieModel


class MoviesDetails : AppCompatActivity() {

    var layoutBinding: ActivityMoviesDetailsBinding? = null
    private var viewModel: MovieViewModel? = null
    private var movieModel: MovieModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_movies_details)

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        movieModel = intent?.getParcelableExtra(keyMovieModel)

        layoutBinding?.apply {
            lifecycleOwner = this@MoviesDetails
            movieModel = this@MoviesDetails.movieModel
            movieViewModel = viewModel


            /*
            * Set the rating to rating bar
            * */
            if (movieModel?.rating != "")
                ratingBar.rating = movieModel?.rating?.toFloat() ?:0f

            /*
            * Set the click litner on the button to set the rating into the local db
            * */
            addRating.setOnClickListener {
                AppDb.getInstance(this@MoviesDetails).movieDao().apply {
                    updateMovieRatingByMovieId(ratingBar.rating.toString(), movieModel?.id?:0)
                }
            }
        }
    }
}