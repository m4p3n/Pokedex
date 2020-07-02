package it.mmilani.pokedex.arch.changes

import it.mmilani.pokedex.api.models.PokemonListResponse
import it.mmilani.pokedex.api.models.GenericResult
import it.mmilani.pokedex.api.models.Pokemon

sealed class Change {
    data class PokemonList(val pokemonList : PokemonListResponse<GenericResult>) : Change()
    data class PokemonListError(val throwable: Throwable) : Change()
    data class PokemonDetail(val pokemon : Pokemon) : Change()
    data class PokemonDetailError(val error : Throwable) : Change()
}