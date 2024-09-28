package siarhei.luskanau.android.test.task.ui.dashboard

import androidx.lifecycle.viewModelScope
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import siarhei.luskanau.android.test.task.core.formatter.CoreFormatter
import siarhei.luskanau.android.test.task.core.preferences.AppPreference
import siarhei.luskanau.android.test.task.core.storage.CoreStorage

internal class DashboardViewModelImpl(
    private val appPreference: AppPreference,
    private val coreFormatter: CoreFormatter,
    private val coreStorage: CoreStorage
) : DashboardViewModel() {

    override val viewState = MutableStateFlow(
        DashboardViewState(
            isLoading = true,
            totalDismissalsAllowed = "",
            totalDismissalsAllowedError = null,
            intervalBetweenDismissals = "",
            intervalBetweenDismissalsError = null,
            bootInfo = ""
        )
    )

    override fun onLaunched() {
        viewModelScope.launch {
            delay(duration = 1.seconds)
            val totalDismissalsAllowed = appPreference.getTotalDismissalsAllowed()
                .first().toString()
            val intervalBetweenDismissals = appPreference.getIntervalBetweenDismissals()
                .first().inWholeMinutes.toString()
            val bootInfo = coreStorage.getBootEventInfoByDays()
            val message = coreFormatter.formatUiMessage(bootInfo)
            viewState.emit(
                DashboardViewState(
                    isLoading = false,
                    totalDismissalsAllowed = totalDismissalsAllowed,
                    totalDismissalsAllowedError = null,
                    intervalBetweenDismissals = intervalBetweenDismissals,
                    intervalBetweenDismissalsError = null,
                    bootInfo = message
                )
            )
        }
    }

    override fun onTotalDismissalsAllowedChanged(text: String) {
        viewModelScope.launch {
            try {
                val value = text.trim().toInt()
                appPreference.setTotalDismissalsAllowed(value)
                viewState.emit(
                    viewState.value.copy(
                        totalDismissalsAllowed = text.trim(),
                        totalDismissalsAllowedError = null
                    )
                )
            } catch (error: Throwable) {
                viewState.emit(
                    viewState.value.copy(
                        totalDismissalsAllowed = text.trim(),
                        totalDismissalsAllowedError = error.message
                    )
                )
            }
        }
    }

    override fun onIntervalBetweenDismissalsChanged(text: String) {
        viewModelScope.launch {
            try {
                val value = text.trim().toLong().minutes
                appPreference.setIntervalBetweenDismissals(value)
                viewState.emit(
                    viewState.value.copy(
                        intervalBetweenDismissals = text.trim(),
                        intervalBetweenDismissalsError = null
                    )
                )
            } catch (error: Throwable) {
                viewState.emit(
                    viewState.value.copy(
                        intervalBetweenDismissals = text.trim(),
                        intervalBetweenDismissalsError = error.message
                    )
                )
            }
        }
    }
}
