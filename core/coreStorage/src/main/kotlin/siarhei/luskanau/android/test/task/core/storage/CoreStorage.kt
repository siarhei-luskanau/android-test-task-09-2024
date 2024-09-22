package siarhei.luskanau.android.test.task.core.storage

import java.util.Date

interface CoreStorage {
    suspend fun saveBootEvent()
    suspend fun getBootEventInfoByDays(): Map<Date, Int>
}
