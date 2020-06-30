package it.mmilani.pokedex.api

import it.mmilani.pokedex.api.models.BaseResponse
import it.mmilani.pokedex.api.models.BaseResult
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface PokemonApi {

    @GET("v2/pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit : Int = 100,
        @Query("offset") offset : Int = 200) : BaseResponse<BaseResult>

    @GET
    suspend fun getPokemonListByUrl(@Url url : String) : BaseResponse<BaseResult>
}