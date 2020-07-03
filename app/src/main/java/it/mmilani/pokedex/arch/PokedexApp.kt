package it.mmilani.pokedex.arch

import android.app.Application
import android.util.Log
import it.mmilani.pokedex.api.repo.pokemonModule
import it.mmilani.pokedex.ui.detail.detailModule
import it.mmilani.pokedex.ui.homepage.fragmentModule
import it.mmilani.pokedex.ui.homepage.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

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
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@PokedexApp)
            modules(listOf(networkModule, pokemonModule,  viewModelModule,
                fragmentModule, detailModule))
        }
    }
}