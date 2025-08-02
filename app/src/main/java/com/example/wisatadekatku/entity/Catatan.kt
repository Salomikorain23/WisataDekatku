package com.example.wisatadekatku.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "catatan")
data class Catatan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val judul: String,
    val catatan: String,
    val tanggal: String
)
