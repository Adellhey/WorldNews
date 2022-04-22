package com.example.worldnews.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ObjectCollection(
    @SerializedName("results") @Expose
    var collection: List<Results>
)
