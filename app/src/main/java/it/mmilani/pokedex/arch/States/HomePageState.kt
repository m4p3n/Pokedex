package it.mmilani.pokedex.arch.States

import com.ww.roxie.BaseState
import it.mmilani.pokedex.api.models.BaseResponse
import it.mmilani.pokedex.api.models.BaseResult

sealed class HomePageState : BaseState {
    data class HomePageInit(val initialize : Boolean = false) : HomePageState()
    data class HomePageLoading(val isLoading: Boolean = false) : HomePageState()
    data class HomePagePokemonListResult(val results : BaseResponse<BaseResult>) : HomePageState()
    data class HomePagePokemonListError(val error : Throwable) : HomePageState()
}