package siarhei.luskanau.android.test.task.core.storage

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.annotation.Single
import siarhei.luskanau.android.test.task.core.storage.room.AppDatabase
import siarhei.luskanau.android.test.task.core.storage.room.BootEvent

@Single
internal class CoreStorageImpl(private val appDatabase: AppDatabase) : CoreStorage {

    override suspend fun saveBootEvent() {
        appDatabase.bootEventDao().insertAll(
            BootEvent(
                id = null,
                date = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            )
        )
    }

    override suspend fun getBootEventInfoByDays(): Map<LocalDateTime, Int> =
        appDatabase.bootEventDao()
            .getCountByDays()
            .associate { it.date to it.count }

    override fun getBootEventLastTwo(): List<LocalDateTime> = appDatabase.bootEventDao()
        .getLastTwo()
        .map { it.date }
}
