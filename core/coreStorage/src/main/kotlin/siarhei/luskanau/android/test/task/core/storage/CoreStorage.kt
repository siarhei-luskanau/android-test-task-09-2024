package siarhei.luskanau.android.test.task.core.storage

import java.util.Date

interface CoreStorage {
    suspend fun saveBootEvent()
    suspend fun getBootEventInfo(): BootEventInfo
}

data class BootEventInfo(
    val count: Int,
    val date: Date?
)
