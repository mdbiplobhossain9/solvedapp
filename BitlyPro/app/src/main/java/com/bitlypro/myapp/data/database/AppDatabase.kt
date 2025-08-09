import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bitlypro.myapp.data.database.SpeedTestDao
import com.bitlypro.myapp.data.database.SpeedTestEntity
import android.content.Context

package com.bitlypro.myapp.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context

@Database(
    entities = [SpeedTestEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun speedTestDao(): SpeedTestDao
    
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bitly_pro_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}