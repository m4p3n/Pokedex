package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HeldItem(
        @Expose
        @SerializedName("item")
        var item : GenericResult? = null,
        @Expose
        @SerializedName("version_details")
        var versionDetails : List<VersionDetail>? = null
)