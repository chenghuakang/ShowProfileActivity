package com.example.lab

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Reservation::class], version = 1, exportSchema = false)
abstract class db : RoomDatabase() {

    abstract fun Dao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: db? = null

        fun getDatabase(context: Context): db{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    db::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
