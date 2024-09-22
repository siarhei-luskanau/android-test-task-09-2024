package siarhei.luskanau.android.test.task.core.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [BootEvent::class], version = 1)
@TypeConverters(value = [DateConverter::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun bootEventDao(): BootEventDao
}
