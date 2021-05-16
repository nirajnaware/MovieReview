package com.example.moviereview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereview.Room.AppDb
import com.example.moviereview.databinding.ActivityMovieListBinding
import com.example.moviereview.model.MovieModel
import com.example.moviereview.util.keyMovieModel

/*
* How the list of movies
* */
class MovieList : AppCompatActivity() {

    var layoutBinding: ActivityMovieListBinding? = null
    private var viewModel: MovieViewModel? = null
    var mAdapter: MovieListAdapter? = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_list)

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        layoutBinding?.apply {
            lifecycleOwner = this@MovieList
            movieViewModel = viewModel
        }
        setObservers()
    }

    private fun setObservers() {
        viewModel?.apply {

            getMovieListFromDB(this@MovieList)

            /*
            * Observe the list and call set adaper
            * */
            moviesModelListLiveData.observe(this@MovieList, Observer {
                it?.let {
                    setAdapter(it)
                }
            })

            /*
            * Observe , when click on card of movie to go details page
            * */
            onClickCardLiveData.observe(this@MovieList, Observer {
                it?.let {
                    val intent = Intent(this@MovieList, MoviesDetails::class.java)

                    var movieModel: MovieModel? = null
                    AppDb.getInstance(this@MovieList).movieDao().apply {
                        movieModel = getMovie(it?.id?:0)
                    }

                    intent.putExtra(keyMovieModel, movieModel)
                    startActivity(intent)
                    onClickCardLiveData.value = null
                }
            })

        }
    }

    private fun setAdapter(movieModel: List<MovieModel>) {
        layoutBinding?.apply {
            rvList.apply {
                layoutManager = LinearLayoutManager(this@MovieList, RecyclerView.VERTICAL, false)
                mAdapter?.list = movieModel
                mAdapter?.movieViewModel = viewModel
                adapter = mAdapter
            }
        }
    }



}