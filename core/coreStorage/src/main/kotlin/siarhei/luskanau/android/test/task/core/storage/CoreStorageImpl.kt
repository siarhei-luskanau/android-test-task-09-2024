package siarhei.luskanau.android.test.task.core.storage

import java.util.Date
import org.koin.core.annotation.Single
import siarhei.luskanau.android.test.task.core.storage.room.AppDatabase
import siarhei.luskanau.android.test.task.core.storage.room.BootEvent

@Single
internal class CoreStorageImpl(private val appDatabase: AppDatabase) : CoreStorage {

    override suspend fun saveBootEvent() {
        appDatabase.bootEventDao().insertAll(BootEvent(null, Date()))
    }

    override suspend fun getBootEventInfoByDays(): Map<Date, Int> = appDatabase.bootEventDao()
        .getCountByDays()
        .associate { it.date to it.count }
}
