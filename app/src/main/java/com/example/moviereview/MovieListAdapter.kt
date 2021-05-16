package com.example.moviereview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviereview.databinding.CardItemBinding
import com.example.moviereview.model.MovieModel

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>() {

    var list: List<MovieModel> ? = null
    var movieViewModel: MovieViewModel? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListAdapter.MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CardItemBinding>(
            layoutInflater, R.layout.card_item, parent, false
        )
        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list?.size?:0
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val matchesResponseModel: MovieModel = list?.get(position) ?: MovieModel()
        holder.bind(matchesResponseModel)
    }

    inner class MoviesViewHolder (var cardItemBinding: CardItemBinding) :
        RecyclerView.ViewHolder(cardItemBinding.root) {

        /*
        * Used to model class bind to the XML card
        * */
        fun bind(movieModel: MovieModel?) {
            cardItemBinding.apply {
                this.movieModel = movieModel
                this.movieViewModel = this@MovieListAdapter.movieViewModel
            }
        }

    }

}