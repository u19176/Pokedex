package br.unicamp.pokedex_19176
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {
    @GET("getdex")
    fun getPokemonList(): Call<List<PokemonData>>

}