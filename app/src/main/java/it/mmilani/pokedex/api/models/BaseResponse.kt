package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @Expose
    @SerializedName("count")
    var count : Int = 0,
    @Expose
    @SerializedName("next")
    var next : String = "",
    @Expose
    @SerializedName("previous")
    var previous : String = "",
    @Expose
    @SerializedName("results")
    var results : List<T>? = null
)