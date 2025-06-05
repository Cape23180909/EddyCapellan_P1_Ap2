package edu.ucne.eddycapellan_p1_ap2.data.local.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.ucne.eddycapellan_p1_ap2.data.local.entities.TareaEntity

@Composable
fun TareaListScreen(
    tareaList: List<TareaEntity>,
    onCreate: () -> Unit,
    onEdit: (TareaEntity) -> Unit,
    onDelete: (TareaEntity) -> Unit
){
    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCreate,
                containerColor = Color(0xFF43A047),
                contentColor = Color.White
            ){
                Icon(Icons.Filled.Add, contentDescription = "Agregar")
            }
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .padding()
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFE9E7EF), Color(0xFF6650a4))
                    )
                )
                .padding(paddingValues)
                .padding(horizontal = 18.dp, vertical = 18.dp)
        ){
            Text(
                text = "Lista de Tareas",
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6650a4),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(top = 39.dp))

            LazyColumn (verticalArrangement = Arrangement.spacedBy(18.dp)) {
                items(tareaList) { tarea ->
                    TareaRow(tarea, onEdit, onDelete)
                }
            }

        }
    }
}

@Composable
fun TareaRow(
    tarea: TareaEntity,
    onEdit: (TareaEntity) -> Unit,
    onDelete: (TareaEntity) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(22.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Descripcion:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = tarea.descripcion, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Tiempo:", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(text = tarea.tiempo.toString(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }

            Row {
                IconButton(onClick = { onEdit(tarea) }) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit", tint = Color(0xFF43A047))
                }
                IconButton(onClick = { onDelete(tarea) }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete", tint = Color(0xFFD81B60))
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TareaListScreenPreview() {
    val sampleTareas = remember {
        mutableStateListOf(
            TareaEntity(descripcion = " Consumir Api", tiempo = 15),
            TareaEntity(descripcion = " Hacer un api", tiempo = 15),
            TareaEntity(descripcion = " Prestamos Libros", tiempo = 15)
        )
    }

    TareaListScreen(
        tareaList = sampleTareas,
        onCreate = { sampleTareas.add(TareaEntity(descripcion = "Nueva tarea", tiempo = 0 )) },
        onDelete = { tarea -> sampleTareas.remove(tarea) },
        onEdit = { /* Simulación de edición */ }
    )
}