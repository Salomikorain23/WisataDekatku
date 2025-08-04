package com.example.wisatadekatku.model

data class Wisata(
    val id: Int,
    val nama: String,
    val lokasi: String,
    val latitude: Double,
    val longitude: Double,
    val deskripsi: String,
    val jarak: Double = 0.0,
    val gambarResId: Int = 0
)
