package com.example.moviereview.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MovieModel() : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var title : String? =""
    var releaseDate : String? =""
    var type : String? =""
    var about : String? =""
    var like : String? =""
    var rating : String? = ""

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        title = parcel.readString()
        releaseDate = parcel.readString()
        type = parcel.readString()
        about = parcel.readString()
        like = parcel.readString()
        rating = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(releaseDate)
        parcel.writeString(type)
        parcel.writeString(about)
        parcel.writeString(like)
        rating?.let { parcel.writeString(it) }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieModel> {
        override fun createFromParcel(parcel: Parcel): MovieModel {
            return MovieModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieModel?> {
            return arrayOfNulls(size)
        }
    }
}