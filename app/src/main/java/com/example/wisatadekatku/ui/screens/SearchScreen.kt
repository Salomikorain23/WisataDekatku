package com.example.wisatadekatku.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wisatadekatku.data.WisataRepository
import com.example.wisatadekatku.model.Wisata

@Composable
fun SearchScreen(navController: NavController) {
    val context = LocalContext.current
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val allWisata = remember { WisataRepository.getAllWisata() }

    val filteredWisata by remember(query.text) {
        mutableStateOf(
            allWisata.filter {
                it.nama.contains(query.text, ignoreCase = true) ||
                        it.lokasi.contains(query.text, ignoreCase = true)
            }
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            label = { Text("Cari wisata") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (filteredWisata.isEmpty()) {
            Text(
                "Tidak ada wisata ditemukan",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn {
                items(filteredWisata) { wisata ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                navController.navigate("detail/${wisata.id}")
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(wisata.nama, style = MaterialTheme.typography.titleMedium)
                            Text(wisata.lokasi, style = MaterialTheme.typography.bodySmall)
                            Text(wisata.deskripsi, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
