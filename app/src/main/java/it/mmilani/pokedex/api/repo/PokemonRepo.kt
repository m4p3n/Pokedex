package it.mmilani.pokedex.api.repo

import it.mmilani.pokedex.api.PokemonApi
import org.koin.dsl.module.module

val pokemonModule = module {
    factory { PokemonRepository(get()) }
}

class PokemonRepository(private val pokemonApi : PokemonApi){
    suspend fun getPokemonList() = pokemonApi.getPokemonList()
    suspend fun getPokemonListWithConfig(limit : Int, offset : Int) = pokemonApi.getPokemonList(limit, offset)
    suspend fun getPokemonListByUrl(url : String) = pokemonApi.getPokemonListByUrl(url)
    suspend fun getPokemonDetailByUrl(url : String) = pokemonApi.getPokemonDetailByUrl(url)
}