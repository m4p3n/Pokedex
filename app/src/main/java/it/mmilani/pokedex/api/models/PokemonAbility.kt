package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokemonAbility (
        @Expose
        @SerializedName("ability")
        var ability : GenericResult? = null,
        @Expose
        @SerializedName("is_hidden")
        var isHidden : Boolean = false,
        @Expose
        @SerializedName("slot")
        var slot : Int = 0
)