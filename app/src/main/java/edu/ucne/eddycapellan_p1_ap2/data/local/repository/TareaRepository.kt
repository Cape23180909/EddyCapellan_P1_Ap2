package edu.ucne.eddycapellan_p1_ap2.data.local.repository

import edu.ucne.eddycapellan_p1_ap2.data.local.dao.TareaDao
import edu.ucne.eddycapellan_p1_ap2.data.local.entities.TareaEntity
import kotlinx.coroutines.flow.Flow

class TareaRepository(
    private val dao: TareaDao
) {
    suspend fun save(tarea: TareaEntity) = dao.save(tarea)

    suspend fun find(id: Int): TareaEntity? = dao.find(id)

    suspend fun delete(tarea: TareaEntity) = dao.delete(tarea)

    fun getAll(): Flow<List<TareaEntity>> = dao.getAll()
}