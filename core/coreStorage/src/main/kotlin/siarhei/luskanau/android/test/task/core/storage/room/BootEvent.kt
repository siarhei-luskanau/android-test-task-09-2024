package siarhei.luskanau.android.test.task.core.storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "boot_event")
data class BootEvent(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "date") val date: Date,
)
