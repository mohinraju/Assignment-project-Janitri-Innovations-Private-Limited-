
package com.example.pregnancyvitalstracker.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pregnancyvitalstracker.Vital
import kotlinx.coroutines.flow.Flow
@Dao
interface VitalDao {
    @Insert
    suspend fun insertVital(vital: Vital)

    @Query("SELECT * FROM vitals ORDER BY timestamp DESC")
    fun getAllVitals(): Flow<List<Vital>>
}



