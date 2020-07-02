package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VersionDetail(
        @Expose
        @SerializedName("rarity")
        var rarity : Int = 0,
        @Expose
        @SerializedName("version")
        var version : GenericResult? = null
)