package br.unicamp.pokedex_19176
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.unicamp.pokedex_19176.PokemonData

class PokemonAdapter(private val pokemonList: List<PokemonData>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.pokemonId.text = "ID: ${pokemon.id_data}"
        holder.pokemonName.text = pokemon.nome_pokemon
        Log.d("PokemonAdapter", "ID: ${pokemon.id_data}, Name: ${pokemon.nome_pokemon}")


    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val pokemonId: TextView = itemView.findViewById(R.id.pokemonId)
        val pokemonName: TextView = itemView.findViewById(R.id.pokemonName)
        val pokemonIcon: ImageView = itemView.findViewById(R.id.pokemonIcon)
    }
}

