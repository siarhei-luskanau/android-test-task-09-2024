package siarhei.luskanau.android.test.task.core.storage.room

import androidx.room.TypeConverter
import java.util.Date

internal class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}
