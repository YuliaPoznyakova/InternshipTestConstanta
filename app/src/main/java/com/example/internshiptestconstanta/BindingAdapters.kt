package com.example.internshiptestconstanta

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.internshiptestconstanta.adapter.FilmItemAdapter
import com.example.internshiptestconstanta.model.Film

@BindingAdapter("listData")
    fun bindRecyclerView(recyclerView: RecyclerView, data: List<Film>?) {
        val adapter = recyclerView.adapter as FilmItemAdapter
        adapter.submitList(data)
}
