package br.unicamp.pokedex_19176
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.unicamp.pokedex_19176.PokemonData
import com.squareup.picasso.Picasso

class PokemonAdapter(private var pokemonList: List<PokemonData>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    private var filteredPokemonList: List<PokemonData> = pokemonList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = filteredPokemonList[position]

        holder.pokemonId.text = "${position+1}:  "
        holder.pokemonName.text = pokemon.nome_pokemon
        Picasso.get()
            .load(pokemon.icone_pokemon)
            .into(holder.pokemonIcon)

        Log.d("PokemonAdapter", "ID: ${pokemon.id_data}, Name: ${pokemon.nome_pokemon}")

        holder.itemView.setOnClickListener()
        {
            val intent = Intent(it.context, pokemonInfo::class.java)
            intent.putExtra("pokemonId", pokemon.id_data)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return filteredPokemonList.size
    }

    fun filter(query: String) {
        val trimmedQuery = query.trim()
        filteredPokemonList = if (trimmedQuery.isEmpty()) {
            pokemonList
        } else {
            pokemonList.filter { pokemon ->
                pokemon.nome_pokemon.trim()
                    .contains(trimmedQuery, ignoreCase = true) // Case-insensitive and trimmed search
            }
        }
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

    fun applyFilterByOptions(filterTipo1: String, filterTipo2: String, filterDescricao: String) {
        filteredPokemonList = pokemonList.filter { pokemon ->
            val matchTipo1 = filterTipo1 == "-----" || filterTipo1 == pokemon.tipo1_pokemon
            val matchTipo2 = filterTipo2 == "-----" || filterTipo2 == pokemon.tipo2_pokemon
            val matchDescricao = filterDescricao.isEmpty() || pokemon.descricao_pokemon.contains(filterDescricao, ignoreCase = true)

            matchTipo1 && matchTipo2 && matchDescricao
        }
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonId: TextView = itemView.findViewById(R.id.pokemonId)
        val pokemonName: TextView = itemView.findViewById(R.id.pokemonName)
        val pokemonIcon: ImageView = itemView.findViewById(R.id.pokemonIcon)
    }
}