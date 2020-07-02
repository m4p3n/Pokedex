package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GameIndices(
        @Expose
        @SerializedName("game_index")
        var gameIndex : Int = 0,
        @Expose
        @SerializedName("version")
        var version : GenericResult? = null
)