package it.mmilani.pokedex.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import it.mmilani.pokedex.R
import it.mmilani.pokedex.api.models.PokemonStat
import it.mmilani.pokedex.api.models.PokemonType
import it.mmilani.pokedex.arch.States.State
import it.mmilani.pokedex.databinding.FragmentDetailBinding
import it.mmilani.pokedex.ui.homepage.PokeViewModel
import it.mmilani.pokedex.ui.homepage.adapter.GenericRecylerViewAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module.module


val detailModule = module {
    factory { DetailFragment() }
}


class DetailFragment : Fragment() {

    private val homeViewModel : PokeViewModel by viewModel()
    private lateinit var binding : FragmentDetailBinding
    private lateinit var typesAdapter : GenericRecylerViewAdapter<PokemonType>
    private lateinit var statsAdapter : GenericRecylerViewAdapter<PokemonStat>

    companion object{
        private const val TAG = "DetailFragment"
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        homeViewModel.currentState.observe(viewLifecycleOwner, Observer {
            when(it){
                is State.PokemonDetailResult -> {
                    Log.d(TAG, "FLOW -> State.PokemonDetailResult -> ${it} ")
                    binding.pokemon = it.pokemon
                    typesAdapter.addItems(it.pokemon.types?: emptyList())
                    statsAdapter.addItems(it.pokemon.stats?: emptyList())
                    binding.apply {
                        mainContent.visibility = View.VISIBLE
                        errorLayout.visibility = View.GONE
                    }
                }
                is State.PokemonDetailError -> {
                    Log.d(TAG, "FLOW -> State.PokemonDetailError -> ${it} ")
                    binding.apply {
                        mainContent.visibility = View.GONE
                        errorLayout.visibility = View.VISIBLE
                    }
                }
                else -> {}
            }
        })
    }

    private fun setupViews() {
        typesAdapter = GenericRecylerViewAdapter(R.layout.pokemon_types_recycler_item)
        statsAdapter = GenericRecylerViewAdapter(R.layout.pokemon_stats_recycler_item)
        binding.typesRecycler.apply {
            this.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            this.adapter = typesAdapter
        }
        binding.statsRecycler.apply {
            this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            this.adapter = statsAdapter
        }
    }
}