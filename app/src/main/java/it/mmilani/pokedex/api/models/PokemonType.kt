package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import it.mmilani.pokedex.arch.ListItemViewModel

data class PokemonType(
        @Expose
        @SerializedName("slot")
        var slot : Int = 0,
        @Expose
        @SerializedName("type")
        var type: GenericResult? = null
) : ListItemViewModel()