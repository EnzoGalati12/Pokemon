package com.example.pokemon.ui.pokemondetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.pokemon.data.PokemonRepository
import com.example.pokemon.data.api.ApiClient
import com.example.pokemon.databinding.ActivityPokemonDetailBinding

class PokemonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokemonDetailBinding
    private val viewModel: PokemonDetailViewModel by viewModels {
        PokemonDetailViewModelFactory(PokemonRepository(ApiClient.createPokeApiService()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa o View Binding
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchPokemon(intent.getIntExtra("pokeId", 0))

        viewModel.pokemonDetail.observe(this) { pokemonDetail ->
            // Exibir o nome do Pokémon em maiúsculas
            binding.pokemonNameTextView.text = pokemonDetail.name.uppercase()

            // Carregar a imagem principal do Pokémon usando Glide
            Glide.with(this)
                .load(pokemonDetail.principalImageUrl) // Certifique-se de que principalImageUrl é a URL correta
                .into(binding.ivPrincipalImage)

            // Exibir a lista de sprites
            val adapter = PokemonDetailAdapter(pokemonDetail.imagesUrl) // Certifique-se de que sprites é uma lista de URLs
            binding.rvSprites.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvSprites.adapter = adapter

            //hp
            binding.hp.text = pokemonDetail.hp.toString()

            //atack
            binding.attack.text = pokemonDetail.attack.toString()

            //defense
            binding.defense.text = pokemonDetail.defense.toString()
            //speed
            binding.speed.text = pokemonDetail.speed.toString()

        }


    }
}
