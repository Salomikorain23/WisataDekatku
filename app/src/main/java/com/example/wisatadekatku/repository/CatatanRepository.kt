package com.example.wisatadekatku.repository


import com.example.wisatadekatku.dao.CatatanDao
import com.example.wisatadekatku.entity.Catatan
import kotlinx.coroutines.flow.Flow

class CatatanRepository(private val dao: CatatanDao) {
    fun getAll(): Flow<List<Catatan>> = dao.getAll()
    suspend fun getById(id: Int): Catatan? = dao.getById(id)
    suspend fun insert(catatan: Catatan) = dao.insert(catatan)
    suspend fun update(catatan: Catatan) = dao.update(catatan)
    suspend fun delete(catatan: Catatan) = dao.delete(catatan)
}
