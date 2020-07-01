package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import it.mmilani.pokedex.arch.ListItemViewModel

data class BaseResult(
    @Expose
    @SerializedName("name")
    var name : String = "",
    @Expose
    @SerializedName("url")
    var url : String = ""
) : ListItemViewModel()