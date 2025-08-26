package com.example.pregnancyvitalstracker.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pregnancyvitalstracker.Vital

@Database(entities = [Vital::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun vitalDao(): VitalDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "vital_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
