package com.example.wisatadekatku.dao

import androidx.room.*
import com.example.wisatadekatku.entity.Catatan
import kotlinx.coroutines.flow.Flow

@Dao
interface CatatanDao {
    @Query("SELECT * FROM catatan")
    fun getAll(): Flow<List<Catatan>>

    @Query("SELECT * FROM catatan WHERE id = :id")
    suspend fun getById(id: Int): Catatan?

    @Insert
    suspend fun insert(catatan: Catatan)

    @Update
    suspend fun update(catatan: Catatan)

    @Delete
    suspend fun delete(catatan: Catatan)
}
