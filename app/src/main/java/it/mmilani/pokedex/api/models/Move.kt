package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Move(
        @Expose
        @SerializedName("move")
        var move : GenericResult? = null,
        @Expose
        @SerializedName("version_group_detail")
        var versionGroupDetail : List<VersionGroupDetail>? = null
)