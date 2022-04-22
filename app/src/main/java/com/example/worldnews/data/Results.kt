package com.example.worldnews.data


import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "world_news_list")
data class Results(

    @PrimaryKey @NonNull
    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String? = null,

    @SerializedName("full_description")
    @Expose
    val fullDescription: String? = null,

    @SerializedName("content")
    @Expose
    val content: String? = null,

    @SerializedName("image_url")
    @Expose
    val imageUrl: String? = null
)
