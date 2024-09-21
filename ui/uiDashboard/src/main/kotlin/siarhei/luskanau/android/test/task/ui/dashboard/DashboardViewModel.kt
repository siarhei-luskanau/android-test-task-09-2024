package siarhei.luskanau.android.test.task.ui.dashboard

import androidx.lifecycle.ViewModel

abstract class DashboardViewModel : ViewModel() {
    abstract fun onLaunched()
}