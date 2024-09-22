package siarhei.luskanau.android.test.task.ui.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class DashboardViewModel : ViewModel() {
    abstract fun onLaunched()
    abstract val viewState: StateFlow<DashboardViewState>
}