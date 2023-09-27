package br.unicamp.pokedex_19176

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.unicamp.pokedex_19176.PokemonData
import br.unicamp.pokedex_19176.PokemonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class menuPrincipal : AppCompatActivity() {

    private lateinit var call: Call<List<PokemonData>>
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val apiService = RetrofitClient.instance.create<PokemonService>()

        call = apiService.getPokemonList()

        call.enqueue(object : Callback<List<PokemonData>> {
            override fun onResponse(call: Call<List<PokemonData>>, response: Response<List<PokemonData>>) {
                if (response.isSuccessful) {
                    val pokemonList = response.body()
                    if (pokemonList != null) {
                        for (pokemon in pokemonList) {
                            val id = pokemon.id_data
                            val name = pokemon.nome_pokemon
                            val description = pokemon.descricao_pokemon
                            val image = pokemon.imagem_pokemon
                            val icon = pokemon.icone_pokemon
                            val type1 = pokemon.tipo1_pokemon
                            val type2 = pokemon.tipo2_pokemon ?: "N/A"
                            Log.d("Pokemon", "ID: ${pokemon.id_data}, Name: ${pokemon.nome_pokemon}")
                        }
                        val recyclerView = findViewById<RecyclerView>(R.id.pokemonRecyclerView)
                        adapter = PokemonAdapter(pokemonList)
                        recyclerView.layoutManager = LinearLayoutManager(this@menuPrincipal)
                        recyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.e("Pokemon", "API call failed with code: ${response.code()}")
                    }
                } else {
                    Log.e("Pokemon", "API call failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<PokemonData>>, t: Throwable) {
            }
        })

        // Get reference to the search button and search EditText
        val searchButton = findViewById<ImageButton>(R.id.searchButton)
        val searchEditText = findViewById<EditText>(R.id.searchEditText)

        // Initialize the adapter and RecyclerView (moved from inside onResponse)
        val recyclerView = findViewById<RecyclerView>(R.id.pokemonRecyclerView)
        adapter = PokemonAdapter(emptyList()) // Initially, show an empty list
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Set an OnClickListener for the search button
        searchButton.setOnClickListener {
            val query = searchEditText.text.toString()
            adapter.filter(query)
        }
    }
}
