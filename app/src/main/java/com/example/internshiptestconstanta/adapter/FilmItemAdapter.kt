package com.example.internshiptestconstanta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.internshiptestconstanta.databinding.FilmItemBinding
import com.example.internshiptestconstanta.model.Actor
import com.example.internshiptestconstanta.model.Film

class FilmItemAdapter(private val onItemClicked: (Film) -> Unit):
    ListAdapter<Film, FilmItemAdapter.FilmItemViewHolder>(FilmItemViewHolder) {

    class FilmItemViewHolder(
        private var binding: FilmItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(film: Film) {
            binding.film = film
            binding.executePendingBindings()
            binding.filmTitle.text = updatedTitle(film.title, film.releaseYear)
            binding.filmDirector.text = updatedDirectorName(film.directorName)
            binding.filmActors.text = updatedActors(film.actors)
        }

        private fun updatedTitle(titleFilm: String, releaseYear: String): String {
            return "$titleFilm ($releaseYear)"
        }

        private fun updatedDirectorName(director: String): String {
            val director = director.split(" ").toList()
            val name: List<String> = director[0].split("").toList()
            val fatherName: List<String> = director[1].split("").toList()
            val surname: String = director[2]
            return "$surname " + name[1] + ". " + fatherName[1] + ". "
        }

        private fun updatedActors(actorsFilm:List<Actor>): String {
            return actorsFilm.toMutableSet().map { actor -> actor.actorName  }.reduce { a, b -> "$a\n$b"}
        }

        companion object DiffCallback : DiffUtil.ItemCallback<Film>() {
            override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): FilmItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            return FilmItemViewHolder(
                FilmItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
            val film = getItem(position)
            holder.bind(film)
        holder.itemView.setOnClickListener {
            onItemClicked(film)
        }
        }
    }

