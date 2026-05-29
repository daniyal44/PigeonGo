package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.Project
import com.example.data.ProjectRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProjectRepository) : ViewModel() {
    
    val projects: StateFlow<List<Project>> = repository.allProjects
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun connectTunnel(url: String) {
        viewModelScope.launch {
            if (url.isNotBlank()) {
                val newProject = Project(url = url)
                repository.insert(newProject)
            }
        }
    }

    fun deleteProject(project: Project) {
        viewModelScope.launch {
            repository.delete(project.id)
        }
    }
}
