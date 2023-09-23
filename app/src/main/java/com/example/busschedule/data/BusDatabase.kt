package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BusSchedule::class), version = 1)
abstract class BusDatabase : RoomDatabase() {
    abstract fun busDao(): BusDao

    companion object {
        @Volatile
        private var INSTANCE: BusDatabase? = null

        fun getDatabase(context: Context): BusDatabase {
            return INSTANCE ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    BusDatabase::class.java, "bus_database"
                )
                    .createFromAsset("database/bus_schedule.db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also{
                        INSTANCE = it
                    }

            }
        }
    }

}