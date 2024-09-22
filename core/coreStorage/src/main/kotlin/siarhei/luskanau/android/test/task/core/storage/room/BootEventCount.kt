package siarhei.luskanau.android.test.task.core.storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.Date

@Entity(tableName = "boot_event")
data class BootEventCount(
    @ColumnInfo(name = "date") val date: Date,
    @ColumnInfo(name = "count") val count: Int
)
