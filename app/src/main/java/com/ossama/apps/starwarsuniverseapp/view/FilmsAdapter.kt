package com.ossama.apps.starwarsuniverseapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.ossama.apps.starwarsuniverseapp.R
import com.ossama.apps.starwarsuniverseapp.databinding.ItemFilmBinding
import com.ossama.apps.starwarsuniverseapp.model.entity.Film

class FilmsAdapter(private val films: List<Film>) : RecyclerView.Adapter<FilmsAdapter.FilmViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = inflate<ItemFilmBinding>(inflater, R.layout.item_film, parent, false)
        return FilmViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return films.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(films[position])
    }

    inner class FilmViewHolder(private val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.film = film
            binding.executePendingBindings()
        }
    }
}
