package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonSprite(
        @Expose
        @SerializedName("back_defaul")
        var backDefault : String = "",
        @Expose
        @SerializedName("back_female")
        var backFemale : String = "",
        @Expose
        @SerializedName("back_shiny")
        var backShiny : String = "",
        @Expose
        @SerializedName("back_shiny_female")
        var backShinyFemale : String = "",
        @Expose
        @SerializedName("front_default")
        var frontDefault : String = "",
        @Expose
        @SerializedName("front_female")
        var frontFemale : String = "",
        @Expose
        @SerializedName("front_shiny")
        var frontShiny : String = "",
        @Expose
        @SerializedName("front_shiny_female")
        var frontShinyFemale : String = ""
)