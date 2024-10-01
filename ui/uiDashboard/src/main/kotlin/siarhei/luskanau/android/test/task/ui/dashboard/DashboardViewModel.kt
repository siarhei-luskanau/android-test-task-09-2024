package siarhei.luskanau.android.test.task.ui.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class DashboardViewModel : ViewModel() {
    abstract val viewState: StateFlow<DashboardViewState>
    abstract fun onLaunched()
    abstract fun onTotalDismissalsAllowedChanged(text: String)
    abstract fun onIntervalBetweenDismissalsChanged(text: String)
    abstract fun onWorkManagerButtonClicked()
}
