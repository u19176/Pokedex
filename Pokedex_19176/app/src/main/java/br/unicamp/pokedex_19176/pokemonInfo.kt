package br.unicamp.pokedex_19176

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class pokemonInfo : AppCompatActivity() {

    private lateinit var apiService: PokemonService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_info)

        val intent = intent
        val pokemonId = intent.getIntExtra("pokemonId", 0)

        val txtNome = findViewById<TextView>(R.id.txtNome)
        val txtDescricao = findViewById<TextView>(R.id.txtDescricao)
        val txtTipo1 = findViewById<TextView>(R.id.txtTipo1)
        val txtTipo2 = findViewById<TextView>(R.id.txtTipo2)
        val txtAbility = findViewById<TextView>(R.id.txtAbilidade)
        val btnVoltar = findViewById<Button>(R.id.btnVoltarInfo)
        val movesRecyclerView = findViewById<RecyclerView>(R.id.movesView)

        val movesAdapter = MovesAdapter()
        movesRecyclerView.layoutManager = LinearLayoutManager(this)
        movesRecyclerView.adapter = movesAdapter

        apiService = RetrofitClient.instance.create()

        apiService.getPokemonId(pokemonId).enqueue(object : Callback<PokemonData> {
            override fun onResponse(call: Call<PokemonData>, response: Response<PokemonData>) {
                if (response.isSuccessful) {
                    val pokemon = response.body()
                    if (pokemon != null) {
                        txtNome.text = pokemon.nome_pokemon
                        txtDescricao.text = pokemon.descricao_pokemon
                        txtTipo1.text = pokemon.tipo1_pokemon
                        txtTipo2.text = pokemon.tipo2_pokemon ?: "N/A"
                    } else {
                        Log.d("Pokemon Data", "Resposta Vazia")
                    }
                } else {
                    Log.d("Pokemon Data", "Erro na API")
                }
            }
            override fun onFailure(call: Call<PokemonData>, t: Throwable) {
                Log.d("Pokemon Data", "Falha ao chamar API")
            }
        })

        apiService.getAbilityId(pokemonId).enqueue(object : Callback<List<String>> {
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful) {
                    val ability = response.body()
                    Log.d("Ability", "${response.body()}")
                    if (ability != null) {
                        txtAbility.text = ability[0]
                    } else {
                        Log.d("Pokemon Data", "Resposta Vazia")
                    }
                } else {
                    Log.d("Pokemon Data", "Erro na API")
                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d("Pokemon Data", "Falha ao chamar API")
            }
        })
        apiService.getMovesId(pokemonId).enqueue(object : Callback<List<MoveData>> {
            override fun onResponse(call: Call<List<MoveData>>, response: Response<List<MoveData>>) {
                if (response.isSuccessful) {
                    val movesList = response.body()
                    if (movesList != null) {
                        movesAdapter.submitList(movesList)
                    } else {
                        Log.d("Pokemon Data", "Resposta Vazia")
                    }
                } else {
                    Log.d("Pokemon Data", "Erro na API")
                }
            }

            override fun onFailure(call: Call<List<MoveData>>, t: Throwable) {
                Log.d("Pokemon Data", "Erro na API")
            }
        })


        btnVoltar.setOnClickListener()
        {
            val intentVoltar = Intent(this, menuPrincipal::class.java)
            startActivity(intentVoltar)
        }
    }
}
