package siarhei.luskanau.android.test.task.core.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BootEventDao {

    @Query("SELECT * FROM boot_event")
    suspend fun getAll(): List<BootEvent>

    @Query(
        "SELECT" +
                " (date/(26*60*60*1000)*(26*60*60*1000)) as date," +
                " count(date/(26*60*60*1000)) as count " +
                "FROM boot_event"
    )
    suspend fun getCountByDays(): List<BootEventCount>

    @Insert
    suspend fun insertAll(vararg bootEvents: BootEvent)
}
