package br.unicamp.pokedex_19176

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MovesAdapter : ListAdapter<MoveData, MovesAdapter.MoveViewHolder>(MoveDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoveViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_move, parent, false)
        return MoveViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoveViewHolder, position: Int) {
        val move = getItem(position)
        holder.bind(move)
    }

    class MoveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moveNameTextView: TextView = itemView.findViewById(R.id.moveName)
        private val moveStrTextView: TextView = itemView.findViewById(R.id.movePow)
        private val moveAccTextView: TextView = itemView.findViewById(R.id.moveAcc)
        private val moveTypeTextView: TextView = itemView.findViewById(R.id.moveType)
        private val moveCategoryTextView: TextView = itemView.findViewById(R.id.moveCategory)

        fun bind(move: MoveData) {
            moveNameTextView.text = move.nome_move
            moveStrTextView.text = "Pow: ${move.str_move}"
            moveAccTextView.text = "Acc: ${move.acc_move}"
            moveTypeTextView.text = "${move.tipo_move}"
            moveCategoryTextView.text = "${move.categoria_move}"
        }
    }

    class MoveDiffCallback : DiffUtil.ItemCallback<MoveData>() {
        override fun areItemsTheSame(oldItem: MoveData, newItem: MoveData): Boolean {
            return oldItem.id_move == newItem.id_move
        }

        override fun areContentsTheSame(oldItem: MoveData, newItem: MoveData): Boolean {
            return oldItem == newItem
        }
    }
}