package it.mmilani.pokedex.arch.actions

import com.ww.roxie.BaseAction

sealed class HomePageAction : BaseAction {
    data class LoadPokemonDefaultList(val limit : Int = 100, val offset : Int = 200) : HomePageAction()
    data class SearchPokemons(val queryString : String) : HomePageAction()
    data class GoOnDetail(val pokemonDetail : String) : HomePageAction()
}