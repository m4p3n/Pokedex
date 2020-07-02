package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VersionGroupDetail(
        @Expose
        @SerializedName("level_learned_at")
        var levelEarnedAt : Int = 0,
        @Expose
        @SerializedName("move_learn_method")
        var moveLearnMethod : GenericResult ? = null,
        @Expose
        @SerializedName("version_group")
        var version_group : GenericResult ? = null
)