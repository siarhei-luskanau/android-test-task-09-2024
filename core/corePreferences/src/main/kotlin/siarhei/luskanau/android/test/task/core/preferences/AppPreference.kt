package siarhei.luskanau.android.test.task.core.preferences

import kotlin.time.Duration
import kotlinx.coroutines.flow.Flow

interface AppPreference {
    fun getTotalDismissalsAllowed(): Flow<Int>
    suspend fun setTotalDismissalsAllowed(value: Int)
    fun getIntervalBetweenDismissals(): Flow<Duration>
    suspend fun setIntervalBetweenDismissals(value: Duration)
}
