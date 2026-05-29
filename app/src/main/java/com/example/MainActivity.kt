package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.MainViewModel
import com.example.ui.MainViewModelFactory
import com.example.ui.PigeonGoNavHost
import com.example.ui.theme.DeepBlack
import com.example.ui.theme.PigeonGoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PigeonGoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DeepBlack
                ) {
                    val app = application as PigeonGoApplication
                    val viewModel: MainViewModel = viewModel(
                        factory = MainViewModelFactory(app.repository)
                    )
                    val projects by viewModel.projects.collectAsStateWithLifecycle()

                    PigeonGoNavHost(
                        projects = projects,
                        onConnect = { url -> viewModel.connectTunnel(url) },
                        onDelete = { project -> viewModel.deleteProject(project) }
                    )
                }
            }
        }
    }
}
