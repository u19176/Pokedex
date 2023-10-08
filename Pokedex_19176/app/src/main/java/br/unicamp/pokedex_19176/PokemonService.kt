package br.unicamp.pokedex_19176
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("getdex")
    fun getPokemonList(): Call<List<PokemonData>>

    @GET("getid/{id}")
    fun getPokemonId(@Path("id") id: Int): Call<PokemonData>

    @GET("getdexab/{id}")
    fun getAbilityId(@Path("id") id: Int): Call<List<String>>

    @GET("getdexmv/{id}")
    fun getMovesId(@Path("id") id: Int): Call<List<MoveData>>

}