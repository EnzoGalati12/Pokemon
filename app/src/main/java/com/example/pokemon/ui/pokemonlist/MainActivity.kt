package com.example.pokemon.ui.pokemonlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemon.data.PokemonRepository
import com.example.pokemon.data.api.ApiClient
import com.example.pokemon.databinding.ActivityMainBinding
import com.example.pokemon.domain.model.PokemonDetail
import com.example.pokemon.ui.pokemondetail.PokemonDetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(PokemonRepository(ApiClient.createPokeApiService()))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = MainListAdapter { view, pokemon ->
            val detailIntent = Intent(this, PokemonDetailActivity::class.java)
            detailIntent.putExtra("pokeId", pokemon.id)
            startActivity(detailIntent)
        }
        //binding.pokemonRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.pokemonRecyclerView.layoutManager = GridLayoutManager(this, 3)
        binding.pokemonRecyclerView.adapter = adapter
        viewModel.pokemonList.observe(this) {
            adapter.submitData(lifecycle, it)
        }
    }
}