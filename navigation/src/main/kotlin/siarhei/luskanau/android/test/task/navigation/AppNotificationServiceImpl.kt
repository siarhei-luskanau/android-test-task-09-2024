package siarhei.luskanau.android.test.task.navigation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.content.getSystemService
import org.koin.core.annotation.Single
import siarhei.luskanau.android.test.task.core.formatter.CoreFormatter
import siarhei.luskanau.android.test.task.core.storage.CoreStorage
import siarhei.luskanau.android.test.task.domain.broadcast.OnDismissBroadcastReceiver
import siarhei.luskanau.android.test.task.domain.notifications.AppNotificationService
import siarhei.luskanau.android.test.task.ui.common.R as UiCommonR

@Single
internal class AppNotificationServiceImpl(
    private val context: Context,
    private val coreStorage: CoreStorage,
    private val coreFormatter: CoreFormatter
) : AppNotificationService {

    init {
        createChannel(context = context)
    }

    override fun getBootInfoNotificationId(): Int = NOTIFICATION_ID

    override fun getBootInfoNotification(): Notification =
        NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(UiCommonR.drawable.ic_android)
            .setContentTitle(context.getString(UiCommonR.string.app_name))
            .setContentText(
                coreStorage.getBootEventLastTwo().let { events ->
                    coreFormatter.formatNotificationMessage(
                        events.firstOrNull(),
                        events.getOrNull(1)
                    )
                }
            )
            .setAutoCancel(false)
            .setOnlyAlertOnce(true)
            .setContentIntent(
                TaskStackBuilder.create(context)
                    .addNextIntentWithParentStack(
                        Intent(context, NavigationActivity::class.java)
                    )
                    .getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                    )
            )
            .setDeleteIntent(
                PendingIntent.getBroadcast(
                    context,
                    0,
                    Intent(context, OnDismissBroadcastReceiver::class.java),
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
            .build()

    override fun showBootInfoNotification() {
        context.getSystemService<NotificationManager>()?.notify(
            BOOT_INFO_NOTIFICATION_ID,
            getBootInfoNotification()
        )
    }

    override fun hideBootInfoNotification() {
        context.getSystemService<NotificationManager>()
            ?.cancel(BOOT_INFO_NOTIFICATION_ID)
    }

    private fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "channelTitle",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "channelDescription"
                enableLights(false)
                enableVibration(true)
            }
            context.getSystemService<NotificationManager>()?.also { notificationManager ->
                runCatching { notificationManager.deleteNotificationChannel(CHANNEL_ID) }
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    companion object {
        private val NOTIFICATION_ID = "siarhei.luskanau.domain.notifications".hashCode()
        private val BOOT_INFO_NOTIFICATION_ID = "boot_info_notification_id".hashCode()
        private const val CHANNEL_ID = "Channel"
    }
}
