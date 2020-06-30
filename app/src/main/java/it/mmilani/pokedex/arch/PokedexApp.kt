package it.mmilani.pokedex.arch

import android.app.Application
import android.util.Log
import it.mmilani.pokedex.api.repo.pokemonModule
import it.mmilani.pokedex.ui.home.fragmentModule
import it.mmilani.pokedex.ui.homepage.viewModelModule
import org.koin.android.ext.android.startKoin
import org.koin.core.Koin

class PokedexApp : Application() {

    companion object{
        private const val TAG = "PokedexApp"
    }


    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "FLOW -> onCreate: called")

        koinSetup()
    }

    private fun koinSetup() {
        startKoin(this@PokedexApp, modules = listOf(networkModule, pokemonModule,  viewModelModule, fragmentModule))
    }
}