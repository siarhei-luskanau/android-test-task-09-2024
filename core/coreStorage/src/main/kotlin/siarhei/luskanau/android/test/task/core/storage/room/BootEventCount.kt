package siarhei.luskanau.android.test.task.core.storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "boot_event")
internal data class BootEventCount(
    @ColumnInfo(name = "date") val date: LocalDateTime,
    @ColumnInfo(name = "count") val count: Int
)
