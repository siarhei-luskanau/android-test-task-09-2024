package siarhei.luskanau.android.test.task.core.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BootEventDao {

    @Query("SELECT * FROM boot_event")
    suspend fun getAll(): List<BootEvent>

    @Query(
        "SELECT date, count(date(date/1000, 'unixepoch')) as count " +
                "FROM  boot_event " +
                "GROUP BY date(date/1000, 'unixepoch')"
    )
    suspend fun getCountByDays(): List<BootEventCount>

    @Insert
    suspend fun insertAll(vararg bootEvents: BootEvent)
}
