package com.example.pregnancyvitalstracker

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "vitals")
data class Vital(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val systolic: Int,
    val diastolic: Int,
    val heartRate: Int,
    val weight: Float,
    val kicks: Int,
    val timestamp: Long = System.currentTimeMillis()
)
