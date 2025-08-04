// ListWisataScreen.kt
package com.example.wisatadekatku.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ListWisataScreen(navController: NavController) {
    val context = LocalContext.current
    val wisataList = WisataRepository.getAllWisata()
    var query by remember { mutableStateOf(TextFieldValue("")) }

    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    LaunchedEffect(Unit) {
        if (!permissionState.status.isGranted) {
            permissionState.launchPermissionRequest()
        }
    }

    var userLocation by remember { mutableStateOf<Location?>(null) }

    @SuppressLint("MissingPermission")
    LaunchedEffect(permissionState.status.isGranted) {
        if (permissionState.status.isGranted) {
            try {
                val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
                val location = fusedLocationProviderClient.lastLocation.await()
                if (location != null) {
                    userLocation = location
                }
            } catch (e: Exception) {
                Log.e("LocationError", "Gagal mendapatkan lokasi", e)
            }
        }
    }

    val sortedWisata = remember(userLocation, wisataList, query.text) {
        val filtered = if (query.text.isBlank()) wisataList
        else wisataList.filter {
            it.nama.contains(query.text, ignoreCase = true)
        }

        if (userLocation == null) filtered
        else {
            filtered.map { wisata ->
                val lokasiWisata = Location("wisata").apply {
                    latitude = wisata.latitude
                    longitude = wisata.longitude
                }
                val jarak = userLocation!!.distanceTo(lokasiWisata) / 1000
                wisata.copy(jarak = jarak.toDouble())
            }.sortedBy { it.jarak }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Cari wisata...") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Cari")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(sortedWisata) { wisata ->
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
                        Text(
                            String.format("Jarak: %.2f km", wisata.jarak),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
