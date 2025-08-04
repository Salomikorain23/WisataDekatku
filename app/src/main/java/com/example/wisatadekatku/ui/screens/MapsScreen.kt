package com.example.wisatadekatku.ui.screen

import android.Manifest
import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wisatadekatku.data.WisataRepository
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import java.util.*
import kotlin.math.pow
import kotlin.math.roundToInt

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("MissingPermission")
@Composable
fun MapsScreen(
    wisataLatitude: Double,
    wisataLongitude: Double,
    wisataId: Int,
    navController: NavController
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    var userLocation by remember { mutableStateOf<Location?>(null) }
    var address by remember { mutableStateOf("Sedang mengambil alamat...") }
    var distance by remember { mutableStateOf(0.0) }

    val wisata = remember(wisataId) { WisataRepository.getWisataById(wisataId) }
    val wisataLatLng = LatLng(wisataLatitude, wisataLongitude)

    fun perbaruiLokasi() {
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
            .addOnSuccessListener { location ->
                location?.let {
                    userLocation = it
                    distance = hitungJarak(
                        it.latitude, it.longitude,
                        wisataLatLng.latitude, wisataLatLng.longitude
                    )
                }
            }
    }

    fun ambilAlamatDariKoordinat() {
        val geocoder = Geocoder(context, Locale.getDefault())
        try {
            val result = geocoder.getFromLocation(wisataLatLng.latitude, wisataLatLng.longitude, 1)
            address = result?.firstOrNull()?.getAddressLine(0) ?: "Alamat tidak ditemukan"
        } catch (e: Exception) {
            address = "Gagal mengambil alamat"
        }
    }

    LaunchedEffect(Unit) {
        permissionState.launchPermissionRequest()
        perbaruiLokasi()
        ambilAlamatDariKoordinat()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wisata?.nama ?: "Wisata", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Alamat: $address")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Jarak dari lokasi Anda: ${"%.2f".format(distance)} km")
            Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                perbaruiLokasi()
                ambilAlamatDariKoordinat()
            }) {
                Text("Perbarui Lokasi Saya")
            }
        }

        GoogleMap(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(wisataLatLng, 15f)
            }
        ) {
            Marker(
                state = MarkerState(position = wisataLatLng),
                title = wisata?.nama ?: "Lokasi Wisata",
                snippet = address
            )
        }
    }
}

fun hitungJarak(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val radius = 6371 // Earth radius in km
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a = Math.sin(dLat / 2).pow(2.0) +
            Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
            Math.sin(dLon / 2).pow(2.0)
    val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
    return (radius * c * 100).roundToInt() / 100.0
}
