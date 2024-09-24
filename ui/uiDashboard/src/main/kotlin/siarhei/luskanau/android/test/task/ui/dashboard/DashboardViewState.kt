package siarhei.luskanau.android.test.task.ui.dashboard

data class DashboardViewState(
    val isLoading: Boolean,
    val bootInfo: String,
    val totalDismissalsAllowed: String,
    val totalDismissalsAllowedError: String?,
    val intervalBetweenDismissals: String,
    val intervalBetweenDismissalsError: String?
)
