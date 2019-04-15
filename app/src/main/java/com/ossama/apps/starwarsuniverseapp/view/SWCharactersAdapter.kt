package com.ossama.apps.starwarsuniverseapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.ossama.apps.starwarsuniverseapp.R
import com.ossama.apps.starwarsuniverseapp.databinding.ItemStarWarsCharacterBinding
import com.ossama.apps.starwarsuniverseapp.model.entity.mappingEntity.RemoteSWCharacter

class SWCharacterAdapter(val listener: OnAction) : RecyclerView.Adapter<SWCharacterAdapter.SWCharacterViewHolder>() {

    private val swCharacters = mutableListOf<RemoteSWCharacter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SWCharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflate<ItemStarWarsCharacterBinding>(inflater, R.layout.item_star_wars_character, parent, false)
        return SWCharacterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return swCharacters.size
    }

    override fun onBindViewHolder(holder: SWCharacterViewHolder, position: Int) {
        holder.bind(swCharacters[position])
    }

    fun updateList(newCharacters: List<RemoteSWCharacter>) {
        swCharacters.clear()
        swCharacters.addAll(newCharacters)
        notifyDataSetChanged()
    }

    inner class SWCharacterViewHolder(private val binding: ItemStarWarsCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(swCharacter: RemoteSWCharacter) {
            binding.swCharacter = swCharacter
            binding.root.setOnClickListener { listener.onItemSelected(swCharacter) }
            binding.executePendingBindings()
        }
    }
}
