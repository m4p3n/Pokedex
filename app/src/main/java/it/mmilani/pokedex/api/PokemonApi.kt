package it.mmilani.pokedex.api

import it.mmilani.pokedex.api.models.Pokemon
import it.mmilani.pokedex.api.models.PokemonListResponse
import it.mmilani.pokedex.api.models.GenericResult
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {

    @GET("v2/pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit : Int = 100,
        @Query("offset") offset : Int = 200) : PokemonListResponse<GenericResult>

    @GET
    suspend fun getPokemonDetailByUrl(@Url url : String) : Pokemon

}