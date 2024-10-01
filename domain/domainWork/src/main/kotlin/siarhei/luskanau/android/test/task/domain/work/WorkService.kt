package siarhei.luskanau.android.test.task.domain.work

interface WorkService {
    fun onBootEventReceive()
    fun enqueueWorkWithNotification()
    fun onOnNotificationDismissedReceive()
}
