package edu.ucne.eddycapellan_p1_ap2.presentation.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.eddycapellan_p1_ap2.presentation.tareas.TareaListScreen
import edu.ucne.eddycapellan_p1_ap2.presentation.tareas.TareaScreen

@Composable
fun TareaNavHost(
    navHostController: NavHostController,
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.tareaList
    ) {
        //pantalla lista de tecnicos
        composable<Screen.tareaList>{

            TareaListScreen(
                goToTarea = { id ->
                    navHostController.navigate(Screen.tarea(id ?: 0))
                },
                createTarea = {
                    navHostController.navigate((Screen.tarea(0)))
                }
            )
        }

        //pantalla formulario de tecnico
        composable <Screen.tarea>{ backStack ->
            val tareaId = backStack.toRoute<Screen.tarea>().tareaId
            TareaScreen(
                tareaId = tareaId,
                //viewModel = tareasViewModel,
                goBack = { navHostController.popBackStack() }
            )
        }

    }
    }