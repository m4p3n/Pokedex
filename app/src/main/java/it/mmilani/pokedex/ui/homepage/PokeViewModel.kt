package it.mmilani.pokedex.ui.homepage

import androidx.lifecycle.*
import it.mmilani.pokedex.api.models.BaseResponse
import it.mmilani.pokedex.api.models.Error
import it.mmilani.pokedex.api.models.PokemonListResponse
import it.mmilani.pokedex.api.models.GenericResult
import it.mmilani.pokedex.api.models.Pokemon
import it.mmilani.pokedex.api.repo.PokemonRepository
import it.mmilani.pokedex.arch.States.State
import it.mmilani.pokedex.arch.actions.Action
import it.mmilani.pokedex.arch.changes.Change
import org.koin.dsl.module.module

val viewModelModule = module {
        single { PokeViewModel(get())}
}

typealias HomePageReducer = (state : State, change : Change) -> State

class PokeViewModel(private val pokeRepo : PokemonRepository) : ViewModel() {

    var currentState : LiveData<State> = MutableLiveData()


    val pokelist : LiveData<BaseResponse> = liveData {
        try {
            emit(pokeRepo.getPokemonList())
        }
        catch (ex : java.lang.Exception){
                emit(Error(ex))

        }
    }

    fun pokeDetail(url : String) : LiveData<BaseResponse> = liveData {
        try {
            emit(pokeRepo.getPokemonDetailByUrl(url))
        }
        catch (ex : java.lang.Exception){
            emit(Error(ex))

        }
    }



    private fun homepageReducer(change: Change) : State  {
       return when(change){
            is Change.PokemonList -> {State.PokemonListResult(change.pokemonList)}
            is Change.PokemonListError -> {State.PokemonListError(change.throwable)}
           is Change.PokemonDetail -> { State.PokemonDetailResult(change.pokemon)}
           is Change.PokemonDetailError -> { State.PokemonDetailError(change.error)}
       }
    }


    fun dispatchAction(action: Action) {
        when(action){
            is Action.LoadPokemonDefaultList -> {
                currentState  = Transformations.map(pokelist) {
                    if(it != null){
                        when(it){
                            is PokemonListResponse<*> -> {homepageReducer(Change.PokemonList(it as PokemonListResponse<GenericResult>))}
                            is Error -> homepageReducer(Change.PokemonListError(Exception()))
                            else -> null
                        }
                    }
                    else
                        null
                }
            }
            is Action.SearchPokemons ->{}
            is Action.LoadDetail -> {
                currentState = Transformations.map(pokeDetail(action.pokemonDetail)){
                    if(it != null){
                        when(it){
                            is Pokemon -> homepageReducer(Change.PokemonDetail(it))
                            is Error -> homepageReducer(Change.PokemonDetailError(Exception()))
                            else -> null
                        }
                    }
                    else
                        null



                }
            }
        }
    }





}