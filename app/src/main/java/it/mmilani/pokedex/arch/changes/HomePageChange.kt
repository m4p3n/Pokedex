package it.mmilani.pokedex.arch.changes

import it.mmilani.pokedex.api.models.BaseResponse
import it.mmilani.pokedex.api.models.BaseResult
import it.mmilani.pokedex.api.models.Pokemon

sealed class HomePageChange {
    object Loading : HomePageChange()
    data class PokemonList(val pokemonList : BaseResponse<BaseResult>) : HomePageChange()
    data class PokemonListError(val throwable: Throwable) : HomePageChange()
}