package siarhei.luskanau.android.test.task.domain.notifications

import android.app.Notification

interface AppNotificationService {
    fun getBootInfoNotificationId(): Int
    fun getBootInfoNotification(): Notification
}
