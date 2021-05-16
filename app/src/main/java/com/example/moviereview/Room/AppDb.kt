package com.example.moviereview.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviereview.model.MovieModel

@Database(entities = [MovieModel::class],version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    companion object {

        /*
        * Instantiate Local Database for Application
        * */
        private var applicationDatabase: AppDb? = null

        fun getInstance(context: Context): AppDb {
            synchronized(this) {
                var instance =
                    applicationDatabase

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDb::class.java,
                        "MovieDB.db"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                    applicationDatabase = instance
                }
                return instance
            }
        }

    }
}