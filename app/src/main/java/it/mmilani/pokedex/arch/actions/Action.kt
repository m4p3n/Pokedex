package it.mmilani.pokedex.arch.actions


sealed class Action {
    data class LoadPokemonDefaultList(val limit : Int = 100, val offset : Int = 200) : Action()
    data class SearchPokemons(val queryString : String) : Action()
    data class LoadDetail(val pokemonDetail : String) : Action()
}