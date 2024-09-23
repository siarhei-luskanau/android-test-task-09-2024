package siarhei.luskanau.android.test.task.ui.dashboard

import androidx.lifecycle.viewModelScope
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import siarhei.luskanau.android.test.task.core.formatter.CoreFormatter
import siarhei.luskanau.android.test.task.core.storage.CoreStorage

class DashboardViewModelImpl(
    private val coreStorage: CoreStorage,
    private val coreFormatter: CoreFormatter
) : DashboardViewModel() {

    override fun onLaunched() {
        viewModelScope.launch {
            delay(duration = 1.seconds)
            val bootInfo = coreStorage.getBootEventInfoByDays()
            val message = coreFormatter.formatUiMessage(bootInfo)
            viewState.emit(
                DashboardViewState(
                    isLoading = false,
                    bootInfo = message
                )
            )
        }
    }

    override val viewState = MutableStateFlow(
        DashboardViewState(
            isLoading = true,
            bootInfo = ""
        )
    )
}
