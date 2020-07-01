package it.mmilani.pokedex.arch

import android.app.Application
import android.util.Log
import com.ww.roxie.Roxie
import it.mmilani.pokedex.api.repo.pokemonModule
import it.mmilani.pokedex.ui.homepage.fragmentModule
import it.mmilani.pokedex.ui.homepage.viewModelModule
import org.koin.android.ext.android.startKoin

class PokedexApp : Application() {

    companion object{
        private const val TAG = "PokedexApp"
    }


    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "FLOW -> onCreate: called")
        koinSetup()
        roxieLogging()
    }

    private fun roxieLogging() {
        Roxie.enableLogging(object : Roxie.Logger{
            override fun log(msg: String) {
                Log.d("ROXIE", msg)
            }
        })
    }

    private fun koinSetup() {
        startKoin(this@PokedexApp, modules = listOf(networkModule, pokemonModule,  viewModelModule,
            fragmentModule
        ))
    }
}