package siarhei.luskanau.android.test.task.domain.work

interface WorkService {
    suspend fun onBootEventReceive()
    fun onOnNotificationDismissedReceive()
}
