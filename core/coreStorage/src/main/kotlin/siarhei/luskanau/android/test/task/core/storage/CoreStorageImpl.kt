package siarhei.luskanau.android.test.task.core.storage

import org.koin.core.annotation.Single
import java.util.Date

@Single
internal class CoreStorageImpl : CoreStorage {

    private var count: Int = 0
    private var date: Date? = null

    override suspend fun saveBootEvent() {
        count = count++
        date = Date()
    }

    override suspend fun getBootEventInfo(): BootEventInfo = BootEventInfo(
        count = count,
        date = date
    )
}
