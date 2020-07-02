package it.mmilani.pokedex.ui.homepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.mmilani.pokedex.R
import it.mmilani.pokedex.api.models.GenericResult
import it.mmilani.pokedex.arch.States.State
import it.mmilani.pokedex.arch.actions.Action
import it.mmilani.pokedex.databinding.FragmentHomeBinding
import it.mmilani.pokedex.ui.homepage.adapter.GenericRecylerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module.module

val fragmentModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {

    private val homeViewModel : PokeViewModel by viewModel()
    private lateinit var binding : FragmentHomeBinding
    lateinit var pokemonAdapter: GenericRecylerViewAdapter<GenericResult>

    companion object{
        private const val TAG = "HomeFragment"
    }




    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "FLOW -> onCreateView: called")
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.dispatchAction(Action.LoadPokemonDefaultList())
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        pokemonAdapter = GenericRecylerViewAdapter(R.layout.pokemon_list_recycler_item)
        pokemonAdapter.setOnListItemViewClickListener(object : GenericRecylerViewAdapter.OnListItemViewClickListener{
            override fun onClick(view: View, position: Int) {
                val selectedPokemon = pokemonAdapter.getItem(position)
                Log.d(TAG, "onClick: on $selectedPokemon")
                homeViewModel.dispatchAction(Action.LoadDetail(selectedPokemon.url))
                val navController = findNavController()
                navController.navigate(R.id.homepageToSecondLevel)
            }
        })

        binding.pokemonList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = pokemonAdapter
        }
    }

    private fun setupObservers() {
        homeViewModel.currentState.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "FLOW -> nextState: called -> ${it} ")
            when(it){
                is State.PokemonListResult -> {
                    Log.d(TAG, "FLOW -> State.PokemonListResult -> ${it} ")
                    pokemonAdapter.addItems(it.results.results?: emptyList())
                    binding.apply {
                        errorLayout.visibility = View.GONE
                        pokemonList.visibility = View.VISIBLE
                    }
                }
                is State.PokemonListError -> {
                    Log.d(TAG, "FLOW -> State.PokemonListError -> ${it} ")
                    binding.apply {
                        errorLayout.visibility = View.VISIBLE
                        pokemonList.visibility = View.GONE
                    }
                }
            }
        })
    }


}