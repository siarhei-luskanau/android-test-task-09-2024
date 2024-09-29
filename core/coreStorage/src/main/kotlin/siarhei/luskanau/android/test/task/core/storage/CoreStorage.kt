package siarhei.luskanau.android.test.task.core.storage

import kotlinx.datetime.LocalDateTime

interface CoreStorage {
    suspend fun saveBootEvent()
    suspend fun getBootEventInfoByDays(): Map<LocalDateTime, Int>
    fun getBootEventLastTwo(): List<LocalDateTime>
}
