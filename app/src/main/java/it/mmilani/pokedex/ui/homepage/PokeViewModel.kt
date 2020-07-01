package it.mmilani.pokedex.ui.homepage

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.ww.roxie.BaseViewModel
import com.ww.roxie.Reducer
import io.reactivex.Observable
import io.reactivex.rxjava3.kotlin.Observables
import it.mmilani.pokedex.api.models.BaseResponse
import it.mmilani.pokedex.api.models.BaseResult
import it.mmilani.pokedex.api.repo.PokemonRepository
import it.mmilani.pokedex.arch.States.HomePageState
import it.mmilani.pokedex.arch.actions.HomePageAction
import it.mmilani.pokedex.arch.changes.HomePageChange
import org.koin.dsl.module.module
import java.util.*

val viewModelModule = module {
        factory { PokeViewModel(get())}
}

typealias HomePageReducer = (state : HomePageState, change : HomePageChange) -> HomePageState

class PokeViewModel(private val pokeRepo : PokemonRepository) : BaseViewModel<HomePageAction, HomePageState>() {

    override val initialState: HomePageState
        get() = HomePageState.HomePageInit(true)

    var currentState : LiveData<HomePageState> = MutableLiveData()


    val pokelist : LiveData<BaseResponse<BaseResult>> = liveData {
        emit(pokeRepo.getPokemonList())
    }




//    private val homepageReducer : HomePageReducer = { state, change ->
//        when(change){
//            is HomePageChange.Loading -> {HomePageState.HomePageLoading(true)}
//            is HomePageChange.PokemonList -> {HomePageState.HomePagePokemonListResult(change.pokemonList)}
//            is HomePageChange.PokemonListError -> {HomePageState.HomePagePokemonListError(change.throwable)}
//        }
//    }

    private fun homepageReducer(change: HomePageChange) : HomePageState  {
       return when(change){
            is HomePageChange.Loading -> {HomePageState.HomePageLoading(true)}
            is HomePageChange.PokemonList -> {HomePageState.HomePagePokemonListResult(change.pokemonList)}
            is HomePageChange.PokemonListError -> {HomePageState.HomePagePokemonListError(change.throwable)}
        }
    }

//    init {
//        bindActions()
//    }

//    private fun bindActions() {
//        val loadPokemonChange = actions.ofType<HomePageAction.LoadPokemonDefaultList>()
//            .switchMap {
//                pokeRepo.getPokemonList()
//                .toObservable()
//                .map<HomePageChange> {HomePageChange.PokemonList(it.results!!)}
//                .defaultIfEmpty(HomePageChange.PokemonList(emptyList()))
//                .onErrorReturn { HomePageChange.PokemonListError(it) }
//                .startWith(HomePageChange.Loading)
//            }
//
//        val loadPokemonChange2 : Observable<HomePageChange>? = actions.ofType<HomePageAction.LoadPokemonDefaultList>()
//            .switchMap {
//                pokeRepo.getPokemonList()
//                    .toObservable()
//                    .map<HomePageChange> {HomePageChange.PokemonList(it.results!!)}
//                    .defaultIfEmpty(HomePageChange.PokemonList(emptyList()))
//                    .onErrorReturn { HomePageChange.PokemonListError(it) }
//                    .startWith(HomePageChange.Loading)
//            }
//
//        val allchanges = Observable.merge(loadPokemonChange, loadPokemonChange2)
//
//        disposables += allchanges
//            .scan(initialState, homepageReducer)
//            .distinctUntilChanged()
//            .subscribe(state::postValue)




//    }

    fun dispatchAction(action: HomePageAction) : LiveData<HomePageState>{
        when(action){
            is HomePageAction.LoadPokemonDefaultList -> {
                currentState  = Transformations.map(pokelist) {
                    if(it != null)
                        homepageReducer(HomePageChange.PokemonList(it))
                    else
                        homepageReducer(HomePageChange.PokemonListError(Exception()))
                }
            }
            is HomePageAction.SearchPokemons ->{}
            is HomePageAction.GoOnDetail -> {}
        }
        return currentState
    }





}