package it.mmilani.pokedex.ui.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import it.mmilani.pokedex.api.models.BaseResponse
import it.mmilani.pokedex.api.models.BaseResult
import it.mmilani.pokedex.api.repo.PokemonRepository
import org.koin.dsl.module.module

val viewModelModule = module {
        factory { PokeViewModel(get())}
}

class PokeViewModel(private val pokeRepo : PokemonRepository) : ViewModel() {

    val pokelist : LiveData<BaseResponse<BaseResult>> = liveData {
        emit(pokeRepo.getPokemonList())
    }

    fun mockList() : LiveData<String> {
        val mockLiveData = MutableLiveData<String>()
        mockLiveData.value = "TEST"
        return mockLiveData
    }



}