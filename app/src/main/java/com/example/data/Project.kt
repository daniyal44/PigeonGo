package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val url: String,
    val latency: String = "12ms",
    val fps: String = "60fps",
    val timestamp: Long = System.currentTimeMillis()
)
