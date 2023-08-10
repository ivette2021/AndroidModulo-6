package com.example.marsapp.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.marsapp.Model.Remote.MarsRealState


@Database(entities = [MarsRealState::class],version=1) //hay que siempre agregar las anotaciones sqlite
abstract class MarsDataBase : RoomDatabase(){

    //referencia al dao
    abstract fun getMarsDao():MarsDao

    companion object {
        @Volatile
        private var INSTANCE: MarsDataBase? = null

        fun getDataBase(context: Context): MarsDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarsDataBase::class.java,
                    "MarsRealState"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }




}