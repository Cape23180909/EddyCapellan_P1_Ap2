package edu.ucne.eddycapellan_p1_ap2.presentation.tareas

import edu.ucne.eddycapellan_p1_ap2.data.local.entities.TareaEntity

data class TareaUiState(
    val tareaId: Int? = null,
    val descripcion: String = "",
    val tiempo: Int = 0,
    val errorMessage: String? = null,
    val tareas: List<TareaEntity> = emptyList()
)
