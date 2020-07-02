package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import it.mmilani.pokedex.arch.ListItemViewModel

data class PokemonStat(
        @Expose
        @SerializedName("base_stat")
        var baseStat : Int = 0,
        @Expose
        @SerializedName("effort")
        var effort : Int = 0,
        @Expose
        @SerializedName("stat")
        var stat : GenericResult? = null
) : ListItemViewModel(){

        val baseStatString
        get() = baseStat.toString()

        val effortString
        get() = effort.toString()
}