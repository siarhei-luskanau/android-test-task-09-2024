package siarhei.luskanau.android.test.task.core.storage.room

import androidx.room.TypeConverter
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

internal class DateConverter {
    @OptIn(ExperimentalTime::class)
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDateTime? = value?.let {
        Instant.fromEpochMilliseconds(it).toLocalDateTime(TimeZone.currentSystemDefault())
    }

    @OptIn(ExperimentalTime::class)
    @TypeConverter
    fun dateToTimestamp(date: LocalDateTime?): Long? = date
        ?.toInstant(TimeZone.currentSystemDefault())
        ?.toEpochMilliseconds()
}
