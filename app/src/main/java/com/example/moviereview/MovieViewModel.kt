package com.example.moviereview

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviereview.Room.AppDb
import com.example.moviereview.model.MovieModel

class MovieViewModel :  ViewModel(){

    private val arrayList = ArrayList<MovieModel>()//Creating an empty arraylist
    var moviesModelListLiveData = MutableLiveData<List<MovieModel>>().apply { value = arrayListOf() }
    var onClickCardLiveData = MutableLiveData<MovieModel>().apply { value = null }

    /*
    * Add the static data for the some movies
    * */
   private fun addMovies() : ArrayList<MovieModel>{

       arrayList.add(0,MovieModel().apply {
           title = "Adventures in Odyssey: Escape from the Forbidden Matrix"
           releaseDate = "01 Jan 2021"
           type = "Romantic Thriller"
           about = "Bandhan, who lives in the chawl of Mumbai, finds a bag full of antique black coins that are wanted by the Mumbai police and underworld. He falls in love with Roma, a high society girl. Bandhan gifts one gold coin to Roma, the girl he loves. Her boyfriend Sam asks her to get more coins from Bandhan. While trying to con Bandhan, Roma falls in love with him."
           like = "200k"
       })
       arrayList.add(1,MovieModel().apply {
           title = "Armitage III: Poly Matrix"
           releaseDate = "23 June 1996"
           type = "Drama"
           about = "A loving grandson embarks on a complex mission to reunite his ailing grandmother with her ancestral home in Lahore."
           like = "200"
       })
       arrayList.add(2,MovieModel().apply {
           title = "Armitage: Dual Matrix"
           releaseDate = "12 April 2002"
           type = "Romantic Thriller"
           about = "Toofan tells the story of a boxer and the struggles he faces on his journey to get to the national level competition. "
           like = "293k"
       })
       arrayList.add(3,MovieModel().apply {
           title = "Elektro Mathematrix"
           releaseDate = "31 Dec 2016"
           type = "Action Thriller"
           about = "Enemy is a Tamil movie starring Arya, Mirnalini and Vishal Krishna in prominent roles. It is a drama directed by Anand Shankar."
           like = "93"
       })
       arrayList.add(4,MovieModel().apply {
           title = "The American Matrix - Age Of Deception"
           releaseDate = "11 Aug 2011"
           type = "Action Drama"
           about = "Kodiyil Oruvan is a Tamil movie starring Vijay Antony, Aathmika and Ramachandra Raju in prominent roles. It is an action directed by Ananda Krishnan with Nivas Prasanna as musician, forming part of the crew."
           like = "172"
       })
       arrayList.add(5,MovieModel().apply {
           title = "The Animatrix"
           releaseDate = "22 Feb 2003"
           type = "Drama"
           about = "If you are a representative of the production house, please share the details of the film with synopsis@bookmyshow.com"
           like = "146"
       })
       arrayList.add(6,MovieModel().apply {
           title = "The Conjuring 3: The Devil Made Me Do It"
           releaseDate = "4 Jun, 2021"
           type = "Horror Thriller"
           about = "Paranormal investigators Ed and Lorraine Warren are faced with one of their most challenging cases when a murder suspect claims to be possessed by a demon."
           like = "13.4K"
       })
       arrayList.add(7,MovieModel().apply {
           title = "Proud To Be A Sikh 3"
           releaseDate = "22 Nov, 2020"
           type = "Drama"
           about = "Proud To Be A Sikh 3 is a Punjabi movie starring Harvinder Singh and Gursimran Kaur in prominent roles. It is a drama directed by Rupinder Singh and Manjot Singh. "
           like = "100"
       })
       return arrayList
   }

    /*
    * Get the movies from the local db when there is no movies in the local db
    * */
    fun getMovieListFromDB(movieList: MovieList) {
        AppDb.getInstance(movieList).movieDao().apply {

            if (getMovieList().isEmpty()){
                // delete the data from local db
                deleteAllMovieList()
                // add the movie into local db
                insert(addMovies())
                moviesModelListLiveData.postValue(getMovieList())
            }else{
                moviesModelListLiveData.postValue(getMovieList())
            }
        }
    }

    /*
    * Set click listner on card
    * */
    fun onClickCard(view: View, masterModel: MovieModel) {
        onClickCardLiveData.value = masterModel
    }


}