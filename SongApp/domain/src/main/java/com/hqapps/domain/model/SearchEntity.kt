package com.hqapps.domain.model

import android.os.Parcel
import android.os.Parcelable

data class SearchEntity(
        val artistName: String,
        val artworkUrl100: String,
        val trackName: String
) : Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(artistName)
        writeString(artworkUrl100)
        writeString(trackName)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SearchEntity> = object : Parcelable.Creator<SearchEntity> {
            override fun createFromParcel(source: Parcel): SearchEntity = SearchEntity(source)
            override fun newArray(size: Int): Array<SearchEntity?> = arrayOfNulls(size)
        }
    }
}