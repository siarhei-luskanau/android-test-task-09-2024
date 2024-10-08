package siarhei.luskanau.android.test.task.core.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface BootEventDao {

    @Query("SELECT * FROM boot_event")
    suspend fun getAll(): List<BootEvent>

    @Query("SELECT * FROM boot_event ORDER BY date DESC LIMIT 2")
    fun getLastTwo(): List<BootEvent>

    @Query(
        "SELECT date, count(date(date/1000, 'unixepoch')) as count " +
            "FROM  boot_event " +
            "GROUP BY date(date/1000, 'unixepoch') " +
            "ORDER BY date DESC"
    )
    suspend fun getCountByDays(): List<BootEventCount>

    @Insert
    suspend fun insertAll(vararg bootEvents: BootEvent)
}
