package it.mmilani.pokedex.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pokemon(
        @Expose
        @SerializedName("abilities")
        var abilities : List<PokemonAbility>? = null,
        @Expose
        @SerializedName("base_experience")
        var baseExperience : Int = 0,
        @Expose
        @SerializedName("forms")
        var forms : List<GenericResult>? = null,
        @Expose
        @SerializedName("game_indices")
        var gameIndices : List<GameIndices>? = null,
        @Expose
        @SerializedName("height")
        var height : Int = 0,
        @Expose
        @SerializedName("held_items")
        var heldItems : List<HeldItem>? = null,
        @Expose
        @SerializedName("id")
        var id : Int = 0,
        @Expose
        @SerializedName("is_default")
        var isDefault : Boolean = false,
        @Expose
        @SerializedName("location_area_encounters")
        var locationAreaEncounters : String = "",
        @Expose
        @SerializedName("moves")
        var moves : List<Move>? = null,
        @Expose
        @SerializedName("species")
        var species : GenericResult? = null,
        @Expose
        @SerializedName("sprites")
        var sprites : PokemonSprite? = null,
        @Expose
        @SerializedName("stats")
        var stats : List<PokemonStat>? = null,
        @Expose
        @SerializedName("types")
        var types : List<PokemonType>? = null,
        @Expose
        @SerializedName("weight")
        var weight : Int = 0
) : BaseResponse()
{
        val imageUrl  : String?
        get() {
                return if(sprites == null) null
                else when{
                        sprites?.frontDefault?.isNotEmpty()?:false -> sprites?.frontDefault
                        sprites?.frontShiny?.isNotEmpty()?:false -> sprites?.frontShiny
                        sprites?.frontFemale?.isNotEmpty()?:false -> sprites?.frontFemale
                        sprites?.frontShinyFemale?.isNotEmpty()?:false -> sprites?.frontShinyFemale
                        sprites?.backDefault?.isNotEmpty()?:false -> sprites?.backDefault
                        sprites?.backShiny?.isNotEmpty()?:false -> sprites?.backShiny
                        sprites?.backFemale?.isNotEmpty()?:false -> sprites?.backFemale
                        sprites?.backShinyFemale?.isNotEmpty()?:false -> sprites?.backShinyFemale
                        else -> null
                }
        }


}