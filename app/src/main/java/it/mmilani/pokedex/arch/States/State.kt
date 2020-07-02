package it.mmilani.pokedex.arch.States

import it.mmilani.pokedex.api.models.PokemonListResponse
import it.mmilani.pokedex.api.models.Pokemon

sealed class State{
    data class PokemonListResult(val results : PokemonListResponse<it.mmilani.pokedex.api.models.GenericResult>) : State()
    data class PokemonListError(val error : Throwable) : State()
    data class PokemonDetailResult(val pokemon : Pokemon) : State()
    data class PokemonDetailError(val error : Throwable) : State()
}