package com.example

import android.app.Application
import com.example.data.AppDatabase
import com.example.data.ProjectRepository

class PigeonGoApplication : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { ProjectRepository(database.projectDao()) }
}
