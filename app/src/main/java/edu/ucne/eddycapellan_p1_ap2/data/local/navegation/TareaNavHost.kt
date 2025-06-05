package edu.ucne.eddycapellan_p1_ap2.data.local.navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import edu.ucne.eddycapellan_p1_ap2.data.local.presentation.TareaListScreen
import edu.ucne.eddycapellan_p1_ap2.data.local.presentation.TareaScreen
import edu.ucne.eddycapellan_p1_ap2.data.local.presentation.TareaViewModel

@Composable
fun TareaNavHost(
    navController: NavHostController,
    tareaViewModel: TareaViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "tareaList"
    ) {
        composable("tareaList") {
            val tareaList = tareaViewModel.tareaList.collectAsState().value
            TareaListScreen(
                tareaList = tareaList,
                onEdit = { tarea ->
                    navController.navigate("tarea/${tarea.tareaId}")
                },
                onCreate = {
                    navController.navigate("tarea/null")
                },
                onDelete = { tarea ->
                    tareaViewModel.delete(tarea)
                }
            )
        }

        //Navegacion del crear
        composable("tarea/{tareaId}") { backStackEntry ->
            val tareaIdString = backStackEntry.arguments?.getString("tareaId")
            val tareaId = tareaIdString?.toIntOrNull()

            val tarea = if (tareaId != null) {
                tareaViewModel.getTareabyId(tareaId)
            } else {
                null
            }
            TareaScreen(
                tarea = tarea,
                agregarTarea = { descripcion, tiempo ->
                    if (tarea != null) {
                        tareaViewModel.update(tarea.copy(descripcion = descripcion, tiempo = tiempo))
                    } else {
                        tareaViewModel.agregarTarea(descripcion, tiempo)
                    }
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}