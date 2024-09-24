package siarhei.luskanau.android.test.task.core.preferences.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single
import siarhei.luskanau.android.test.task.core.preferences.AppPreference

@Single
internal class DataStoreAppPreference(
    private val context: Context,
    appPreferenceDataSerializer: AppPreferenceDataSerializer
) : AppPreference {
    private val Context.dataStore: DataStore<AppPreferenceData> by dataStore(
        fileName = "app.pb",
        serializer = appPreferenceDataSerializer
    )

    override fun getTotalDismissalsAllowed(): Flow<Int> =
        getFlowFromDataStore { it.totalDismissalsAllowed }

    override suspend fun setTotalDismissalsAllowed(value: Int) =
        updateDataStore { it.copy(totalDismissalsAllowed = value) }

    override fun getIntervalBetweenDismissals(): Flow<Duration> =
        getFlowFromDataStore { it.intervalBetweenDismissals.milliseconds }

    override suspend fun setIntervalBetweenDismissals(value: Duration) =
        updateDataStore { it.copy(intervalBetweenDismissals = value.inWholeMilliseconds) }

    private suspend fun updateDataStore(update: (AppPreferenceData) -> AppPreferenceData) {
        context.dataStore.updateData { update(it) }
    }

    private fun <T : Any> getFlowFromDataStore(mapData: (AppPreferenceData) -> T): Flow<T> =
        context.dataStore.data.map {
            mapData(it)
        }
}
