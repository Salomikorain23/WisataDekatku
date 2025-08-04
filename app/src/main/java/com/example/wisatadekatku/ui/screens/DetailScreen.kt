package com.example.wisatadekatku.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wisatadekatku.data.WisataRepository
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DetailScreen(wisataId: Int, navController: NavController) {
    val context = LocalContext.current
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val wisata = WisataRepository.getWisataById(wisataId)
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    var userLocation by remember { mutableStateOf<Location?>(null) }
    var jarak by remember { mutableStateOf<Double?>(null) }

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
    }

    @SuppressLint("MissingPermission")
    LaunchedEffect(permissionState.status.isGranted) {
        if (permissionState.status.isGranted) {
            fusedLocationClient.getCurrentLocation(
                com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY,
                null
            ).addOnSuccessListener {
                if (it != null) userLocation = it
            }
        }
    }

    LaunchedEffect(userLocation, wisata) {
        if (wisata != null && userLocation != null) {
            val lokasiWisata = Location("wisata").apply {
                latitude = wisata.latitude
                longitude = wisata.longitude
            }
            jarak = userLocation!!.distanceTo(lokasiWisata) / 1000.0
        }
    }

    if (wisata != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Image(
                    painter = painterResource(id = wisata.gambarResId),
                    contentDescription = wisata.nama,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(wisata.nama, style = MaterialTheme.typography.headlineMedium)
                Text("Lokasi: ${wisata.lokasi}", style = MaterialTheme.typography.bodyMedium)
                if (jarak != null) {
                    Text("Jarak: %.2f km".format(jarak), style = MaterialTheme.typography.bodyMedium)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = wisata.deskripsi,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { backDispatcher?.onBackPressed() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Kembali")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        navController.navigate("maps/${wisata.latitude}/${wisata.longitude}/${wisata.id}")
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Lihat Peta")
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Data wisata tidak ditemukan.")
        }
    }
}
