package com.example.apidog.Model.Local.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.apidog.Model.Local.DogDao
import com.example.apidog.Model.Local.Entities.DogDetailEntity
import com.example.apidog.Model.Local.Entities.DogEntity

@Database(entities = [DogEntity:: class, DogDetailEntity::class], version = 1, //colocamos las dos entidades que definimos anteriormente
    exportSchema = false)
@TypeConverters(DogTypeConverter::class)
abstract class DogDataBase: RoomDatabase() {

    // referencia del dao
    abstract fun getDogDao(): DogDao

    companion object{

        @Volatile
        private var
                INSTANCE : DogDataBase? = null
        fun getDataBase(context: Context) : DogDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DogDataBase::class.java, "Dog_App")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}