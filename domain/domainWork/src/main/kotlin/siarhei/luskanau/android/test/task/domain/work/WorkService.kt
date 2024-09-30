package siarhei.luskanau.android.test.task.domain.work

interface WorkService {
    fun onBootEventReceive()
    fun enqueueWorkWithNotification(runAttemptCount: Int)
    fun onOnNotificationDismissedReceive()
}
