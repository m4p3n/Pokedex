package it.mmilani.pokedex.arch

import it.mmilani.pokedex.ui.homepage.adapter.GenericRecylerViewAdapter

abstract class ListItemViewModel {

    var adapterPosition: Int = -1
    var onListItemViewClickListener: GenericRecylerViewAdapter.OnListItemViewClickListener? = null

}