package com.example.wisatadekatku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wisatadekatku.entity.Catatan
import com.example.wisatadekatku.viewmodel.CatatanViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatatanFormScreen(
    navController: NavController,
    viewModel: CatatanViewModel,
    catatanId: Int? = null
) {
    var judul by remember { mutableStateOf("") }
    var catatan by remember { mutableStateOf("") }

    LaunchedEffect(catatanId) {
        if (catatanId != null) {
            val data = viewModel.catatanList.value.find { it.id == catatanId }
            data?.let {
                judul = it.judul
                catatan = it.catatan
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (catatanId == null) "Tambah Catatan" else "Edit Catatan") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = judul,
                onValueChange = { judul = it },
                label = { Text("Judul") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            OutlinedTextField(
                value = catatan,
                onValueChange = { catatan = it },
                label = { Text("Catatan") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    val tanggal = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                    val newCatatan = if (catatanId == null) {
                        Catatan(judul = judul, catatan = catatan, tanggal = tanggal)
                    } else {
                        Catatan(id = catatanId, judul = judul, catatan = catatan, tanggal = tanggal)
                    }

                    if (catatanId == null) {
                        viewModel.insert(newCatatan) { navController.popBackStack() }
                    } else {
                        viewModel.update(newCatatan) { navController.popBackStack() }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Simpan")
            }
        }
    }
}
