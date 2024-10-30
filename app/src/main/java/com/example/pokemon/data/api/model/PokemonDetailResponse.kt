package com.example.pokemon.data.api.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailResponse(
    val name: String,
    val id: Int,
    val sprites: Sprites,
    val stats: List<Stats>,
)

class Other(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork
)

class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String

)

data class Sprites(
    @SerializedName("back_default") val backDefault: String,
    @SerializedName("back_shiny") val backShiny: String,
    @SerializedName("front_default") val frontDefault: String,
    @SerializedName("front_shiny") val frontShiny: String,
    val other: Other
)

data class Stats(
    @SerializedName("base_stat") val baseStat: Int,
    val stat: Stat
)

data class Stat(
    val name: String
)