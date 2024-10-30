package com.example.pokemon.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokemon.data.PokemonRepository

class MainViewModel(repository: PokemonRepository) : ViewModel() {
    val pokemonList = repository.getPokemonList().cachedIn(viewModelScope)
}