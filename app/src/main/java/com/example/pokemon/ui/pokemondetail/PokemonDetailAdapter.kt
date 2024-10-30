package com.example.pokemon.ui.pokemondetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.databinding.ItemSpriteBinding

class PokemonDetailAdapter(private val sprites: List<String>) : RecyclerView.Adapter<PokemonDetailAdapter.SpriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpriteViewHolder {
        val binding = ItemSpriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SpriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SpriteViewHolder, position: Int) {
        val spriteUrl = sprites[position]
        holder.bind(spriteUrl)
    }

    override fun getItemCount(): Int = sprites.size

    inner class SpriteViewHolder(private val binding: ItemSpriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(spriteUrl: String) {
            Glide.with(binding.spriteImageView.context)
                .load(spriteUrl)
                .into(binding.spriteImageView)
        }
    }
}
