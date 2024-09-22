package siarhei.luskanau.android.test.task.core.storage

import org.koin.core.annotation.Single
import java.util.Date
import java.util.concurrent.TimeUnit

@Single
internal class CoreStorageImpl : CoreStorage {

    private var count: Int = 0
    private var date: Date? = null

    override suspend fun saveBootEvent() {
        count = count++
        date = Date()
    }

    override suspend fun getBootEventInfoByDays(): Map<Date, Int> = mapOf(
        Date() to count,
        Date(Date().time - TimeUnit.DAYS.toMillis(1)) to 5,
        Date(Date().time - TimeUnit.DAYS.toMillis(2)) to 10,
        Date(Date().time - TimeUnit.DAYS.toMillis(3)) to 3
    )
}
