package edu.ucne.eddycapellan_p1_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import edu.ucne.eddycapellan_p1_ap2.data.local.database.TareaDb
import edu.ucne.eddycapellan_p1_ap2.data.local.navegation.TareaNavHost
import edu.ucne.eddycapellan_p1_ap2.data.local.presentation.TareaListScreen
import edu.ucne.eddycapellan_p1_ap2.data.local.presentation.TareaViewModel
import edu.ucne.eddycapellan_p1_ap2.data.local.repository.TareaRepository
import edu.ucne.eddycapellan_p1_ap2.data.local.theme.EddyCapellan_P1_Ap2Theme

class MainActivity : ComponentActivity() {

    private lateinit var tareaDb: TareaDb
    private lateinit var tareasRepository: TareaRepository
    private lateinit var tareasViewModel: TareaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

         tareaDb = Room.databaseBuilder(
             applicationContext,
             TareaDb::class.java,
             "Tarea.db"
         ).fallbackToDestructiveMigrationFrom()
             .build()

        tareasRepository = TareaRepository(tareaDb.TareaDao())
        tareasViewModel = TareaViewModel(tareasRepository)

        setContent {
            EddyCapellan_P1_Ap2Theme {
                val navController = rememberNavController()
                TareaNavHost(
                    navController = navController,
                    tareaViewModel = tareasViewModel
                )
                }
            }
        }
    }