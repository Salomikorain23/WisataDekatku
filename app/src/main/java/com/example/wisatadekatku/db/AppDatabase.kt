package com.example.wisatadekatku.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.wisatadekatku.dao.CatatanDao
import com.example.wisatadekatku.entity.Catatan

@Database(entities = [Catatan::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catatanDao(): CatatanDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "catatan_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
