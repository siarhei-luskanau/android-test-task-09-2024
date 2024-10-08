package siarhei.luskanau.android.test.task.core.storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "boot_event")
internal data class BootEvent(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "date") val date: LocalDateTime
)
