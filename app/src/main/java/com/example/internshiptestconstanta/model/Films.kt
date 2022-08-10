package com.example.internshiptestconstanta.model

import com.google.gson.annotations.SerializedName

data class Films (

    val items: List<Film>
    )

data class Film (

    val title: String,

    val directorName: String,

    val releaseYear: String,

    val actors: List<Actor>
)

data class Actor (

    val actorName: String
)
