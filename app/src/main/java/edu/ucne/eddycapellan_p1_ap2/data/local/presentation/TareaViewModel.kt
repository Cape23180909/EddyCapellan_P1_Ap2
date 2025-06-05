package edu.ucne.eddycapellan_p1_ap2.data.local.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.eddycapellan_p1_ap2.data.local.entities.TareaEntity
import edu.ucne.eddycapellan_p1_ap2.data.local.repository.TareaRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TareaViewModel(
    private val repository: TareaRepository
): ViewModel(){
    //Exponemos la lista de carros con StateFlow
    val tareaList:  StateFlow<List<TareaEntity>> = repository.getAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun agregarTarea(descripcion: String,tiempo: Int){
        val tarea = TareaEntity(
            tareaId = null,
            descripcion = descripcion,
            tiempo = tiempo
        )
        saveTarea(tarea)
    }

    fun saveTarea(tarea: TareaEntity){
        viewModelScope.launch {
            repository.save(tarea)
        }
    }
    fun delete(tarea: TareaEntity){
        viewModelScope.launch {
            repository.delete(tarea)
        }
    }
    fun update (tarea: TareaEntity){
        saveTarea(tarea)
    }
    fun getTareabyId(id: Int?): TareaEntity?{
        return tareaList.value.find { it.tareaId == id }
    }
}