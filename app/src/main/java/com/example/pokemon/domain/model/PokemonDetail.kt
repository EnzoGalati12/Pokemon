package com.example.pokemon.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val principalImageUrl: String,
    val imagesUrl: List<String>,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
)