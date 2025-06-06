package edu.ucne.eddycapellan_p1_ap2.presentation.navegation

import kotlinx.serialization.Serializable

sealed class Screen {
     @Serializable
     data object tareaList: Screen()
    @Serializable
    data class tarea(val tareaId: Int): Screen()
}