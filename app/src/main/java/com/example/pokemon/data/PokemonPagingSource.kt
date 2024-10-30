package com.example.pokemon.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemon.data.api.PokeApiService
import com.example.pokemon.data.api.model.PokemonListItem

class PokemonPagingSource(private val apiService: PokeApiService) :
    PagingSource<Int, PokemonListItem>() {
    private val LIMIT = 21
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,
            PokemonListItem> {
        return try {
            val position = params.key ?: 0
            val response = apiService.getPokemonList(
                limit = LIMIT,
                offset = position * LIMIT
            )
            var pokeId = (position * LIMIT) + 1
            val pokemonListItems = response.results.map { pokemon ->
                //val imageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokeId}.png"
                val imageUrl =
                    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokeId}.png"
                val poke = PokemonListItem(pokeId, pokemon.name, imageUrl)
                pokeId++
                poke
            }
            LoadResult.Page(
                data = pokemonListItems,
                prevKey = if (position == 0) null else position - 1,
                nextKey = if (pokemonListItems.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonListItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
        }
    }
}