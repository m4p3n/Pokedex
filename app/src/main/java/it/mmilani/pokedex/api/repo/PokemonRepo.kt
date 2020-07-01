package it.mmilani.pokedex.api.repo

import io.reactivex.Single
import it.mmilani.pokedex.api.PokemonApi
import it.mmilani.pokedex.api.models.BaseResponse
import it.mmilani.pokedex.api.models.BaseResult
import org.koin.dsl.module.module

val pokemonModule = module {
    factory { PokemonRepository(get()) }
}

class PokemonRepository(private val pokemonApi : PokemonApi){
    suspend fun getPokemonList() = pokemonApi.getPokemonList()
    suspend fun getPokemonListWithConfig(limit : Int, offset : Int) = pokemonApi.getPokemonList(limit, offset)
    suspend fun getPokemonListByUrl(url : String) = pokemonApi.getPokemonListByUrl(url)
}