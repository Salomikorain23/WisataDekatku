package com.example.wisatadekatku.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wisatadekatku.entity.Catatan
import com.example.wisatadekatku.repository.CatatanRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatatanViewModel(private val repository: CatatanRepository) : ViewModel() {
    private val _catatanList = MutableStateFlow<List<Catatan>>(emptyList())
    val catatanList: StateFlow<List<Catatan>> = _catatanList.asStateFlow()

    init {
        loadAll()
    }

    fun loadAll() {
        viewModelScope.launch {
            repository.getAll().collect {
                _catatanList.value = it
            }
        }
    }

    fun insert(catatan: Catatan, onDone: () -> Unit) {
        viewModelScope.launch {
            repository.insert(catatan)
            onDone()
        }
    }

    fun update(catatan: Catatan, onDone: () -> Unit) {
        viewModelScope.launch {
            repository.update(catatan)
            onDone()
        }
    }
    fun delete(catatan: Catatan) {
        viewModelScope.launch {
            repository.delete(catatan)
        }
    }
}
