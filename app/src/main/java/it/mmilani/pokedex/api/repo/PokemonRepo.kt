package it.mmilani.pokedex.api.repo

import it.mmilani.pokedex.api.PokemonApi
import org.koin.dsl.module

val pokemonModule = module {
    factory { PokemonRepository(get()) }
}

open class PokemonRepository(private val pokemonApi : PokemonApi){
    suspend fun getPokemonList() = pokemonApi.getPokemonList()
    suspend fun getPokemonDetailByUrl(url : String) = pokemonApi.getPokemonDetailByUrl(url)
}