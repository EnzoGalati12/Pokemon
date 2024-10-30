package com.example.pokemon.ui.pokemondetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.PokemonRepository
import com.example.pokemon.domain.model.PokemonDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokemonDetailViewModel(private val repository: PokemonRepository) : ViewModel() {

    val pokemonDetail = MutableLiveData<PokemonDetail>()

    fun fetchPokemon(id: Int) {
        viewModelScope.launch {
            val pokemon = repository.getPokemonData(id)
            withContext(Dispatchers.Main) {
                pokemonDetail.value = pokemon
            }
        }
    }

}