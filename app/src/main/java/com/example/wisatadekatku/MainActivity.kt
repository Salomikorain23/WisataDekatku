package com.example.wisatadekatku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.wisatadekatku.db.AppDatabase
import com.example.wisatadekatku.repository.CatatanRepository
import com.example.wisatadekatku.ui.theme.WisataDekatkuTheme
import com.example.wisatadekatku.navigation.AppNavHost
import com.example.wisatadekatku.viewmodel.CatatanViewModel
import com.example.wisatadekatku.viewmodel.CatatanViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val database = AppDatabase.getDatabase(context)
            val repository = CatatanRepository(database.catatanDao())
            val catatanViewModel: CatatanViewModel = viewModel(
                factory = CatatanViewModelFactory(repository)
            )

            val navController = rememberNavController()

            WisataDekatkuTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavHost(
                        navController = navController,
                        catatanViewModel = catatanViewModel
                    )
                }
            }
        }
    }
}
