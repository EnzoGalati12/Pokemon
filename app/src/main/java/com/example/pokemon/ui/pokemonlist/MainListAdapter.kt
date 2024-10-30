package com.example.pokemon.ui.pokemonlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.data.api.model.PokemonListItem
import com.example.pokemon.databinding.PokemonItemBinding

class MainListAdapter(
    private val onItemClick: (View, PokemonListItem) -> Unit
) : PagingDataAdapter<PokemonListItem, MainListAdapter.PokemonViewHolder>(
    POKEMON_ListItem_COMPARATOR
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder
    {
        val binding = PokemonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return PokemonViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        pokemon?.let {
            holder.bind(it, onItemClick)
        }
    }
    inner class PokemonViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonListItem: PokemonListItem, onItemClick: (View, PokemonListItem) -> Unit) {
            binding.pokemonName.text = pokemonListItem.name.capitalize()
            Glide.with(binding.pokemonImage.context)
                .load(pokemonListItem.imageUrl)
                .into(binding.pokemonImage)
            // Configuração do clique para navegação com animação compartilhada
            itemView.setOnClickListener {
                onItemClick(binding.pokemonImage, pokemonListItem)
            }
        }
    }
    companion object {
        private val POKEMON_ListItem_COMPARATOR = object :
            DiffUtil.ItemCallback<PokemonListItem>() {
            override fun areItemsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem):
                    Boolean =
                oldItem.name == newItem.name
            override fun areContentsTheSame(oldItem: PokemonListItem, newItem: PokemonListItem):
                    Boolean =
                oldItem == newItem
        }
    }
}