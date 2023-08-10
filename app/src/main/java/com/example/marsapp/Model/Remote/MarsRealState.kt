package com.example.marsapp.Model.Remote

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


//representa los datos de internet de la Api
@Entity(tableName = "mars_table")
data class MarsRealState(
    @SerializedName("id")
    @PrimaryKey
    //mantenemos los datos del formato json
    val id: String,
    @SerializedName("price")
    val price : Long,
    @SerializedName("type")
    val type : String,
    @SerializedName("img_src")
    val img_src: String


)