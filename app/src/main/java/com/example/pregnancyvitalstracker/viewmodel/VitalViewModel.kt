package com.example.pregnancyvitalstracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pregnancyvitalstracker.Vital
import com.example.pregnancyvitalstracker.data.database.AppDatabase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class VitalViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getDatabase(application).vitalDao()

    val vitals: StateFlow<List<Vital>> = dao.getAllVitals().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addVital(vital: Vital) {
        viewModelScope.launch {
            dao.insertVital(vital)
        }
    }
}
