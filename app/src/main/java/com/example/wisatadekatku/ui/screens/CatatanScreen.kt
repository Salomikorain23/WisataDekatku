package com.example.wisatadekatku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wisatadekatku.viewmodel.CatatanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatatanScreen(navController: NavController, viewModel: CatatanViewModel) {
    val catatanList by viewModel.catatanList.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Catatan Perjalanan") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("catatan_form")
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Catatan")
            }
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            items(catatanList) { catatan ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(Modifier.padding(12.dp)) {
                        Text("Judul: ${catatan.judul}", style = MaterialTheme.typography.titleMedium)
                        Text("Tanggal: ${catatan.tanggal}", style = MaterialTheme.typography.bodySmall)
                        Spacer(Modifier.height(4.dp))
                        Text(catatan.catatan)

                        Spacer(Modifier.height(8.dp))
                        Row {
                            Button(onClick = {
                                navController.navigate("catatan_form/${catatan.id}")
                            }) {
                                Text("Edit")
                            }
                            Spacer(Modifier.width(8.dp))
                            Button(onClick = {
                                viewModel.delete(catatan)
                            }) {
                                Text("Hapus")
                            }
                        }
                    }
                }
            }
        }
    }
}
