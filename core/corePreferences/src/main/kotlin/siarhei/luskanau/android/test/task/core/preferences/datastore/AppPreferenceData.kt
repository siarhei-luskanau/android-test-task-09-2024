package siarhei.luskanau.android.test.task.core.preferences.datastore

import kotlin.time.Duration.Companion.minutes
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AppPreferenceData(
    @SerialName("totalDismissalsAllowed") val totalDismissalsAllowed: Int = 5,
    @SerialName("intervalBetweenDismissals") val intervalBetweenDismissals: Long =
        15.minutes.inWholeMilliseconds
)
