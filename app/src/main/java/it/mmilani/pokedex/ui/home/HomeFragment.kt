package it.mmilani.pokedex.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import it.mmilani.pokedex.R
import it.mmilani.pokedex.ui.homepage.PokeViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module.module

val fragmentModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {

    private val homeViewModel : PokeViewModel by viewModel()

    companion object{
        private const val TAG = "HomeFragment"
    }




    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "FLOW -> onCreateView: called")
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render()
    }

    private fun render() {
        homeViewModel.pokelist.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "FLOW -> render: called -> ${it.count} returned")
        })
        homeViewModel.mockList().observe(viewLifecycleOwner, Observer {
             Log.d(TAG, "FLOW -> render: called")
        })
    }
}