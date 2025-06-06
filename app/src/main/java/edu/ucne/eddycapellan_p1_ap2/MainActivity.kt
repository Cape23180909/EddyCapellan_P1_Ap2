package edu.ucne.eddycapellan_p1_ap2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.eddycapellan_p1_ap2.data.local.database.TareaDb
import edu.ucne.eddycapellan_p1_ap2.presentation.navegation.TareaNavHost
import edu.ucne.eddycapellan_p1_ap2.data.local.theme.EddyCapellan_P1_Ap2Theme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var tareaDb: TareaDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        tareaDb = Room.databaseBuilder(
            applicationContext,
            TareaDb::class.java,
            "Tarea.db"
        ).fallbackToDestructiveMigration()
            .build()

        setContent {
            EddyCapellan_P1_Ap2Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        TareaNavHost(
                            navHostController = rememberNavController()
                        )
                    }
                }
            }
        }
    }
}