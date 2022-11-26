package com.sem08.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sem08.data.LugarDao
import com.sem08.model.Lugar
import com.sem08.repository.LugarRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository : LugarRepository = LugarRepository(LugarDao())
    val obtenerLugares : MutableLiveData<List<Lugar>>

    init{
        obtenerLugares = repository.obtenerLugares
    }

    fun guardarLugar(lugar : Lugar){
        repository.guardarLugar(lugar)
    }
    fun eliminarLugar(lugar: Lugar){
        viewModelScope.launch { repository.elimiarLugar(lugar) }
    }
}