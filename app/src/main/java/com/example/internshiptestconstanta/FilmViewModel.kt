package com.example.internshiptestconstanta

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.internshiptestconstanta.model.Film
import com.example.internshiptestconstanta.network.FilmApi.retrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class FilmApiStatus { LOADING, ERROR, DONE }

class FilmViewModel: ViewModel() {

    private val _filmObjects = MutableLiveData<List<Film>>()
    val filmObjects: LiveData<List<Film>> = _filmObjects

    private val _currentFilm = MutableLiveData<Film>()
    val currentFilm: LiveData<Film> = _currentFilm

    init {
        getFilm()
    }

    private fun getFilm() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofitService.getFilms()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _filmObjects.value = response.body()?.items!!.sortedBy { it.releaseYear.toInt() }
                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }

    fun updateCurrentFilm(film: Film) {
        _currentFilm.value = film
    }
}
