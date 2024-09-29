package siarhei.luskanau.android.test.task.core.permissions

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.activity.result.ActivityResult
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import org.koin.core.annotation.Single

@Single
internal class CorePermissionsImpl(private val context: Context) : CorePermissions {

    override fun isAllPermissionsGranted(): Boolean =
        isNotificationsPermissionGranted() && isBatteryUsagePermissionGranted()

    override fun isBatteryUsagePermissionGranted(): Boolean =
        context.getSystemService(PowerManager::class.java)
            ?.isIgnoringBatteryOptimizations(context.packageName)
            ?: false

    override suspend fun requestNotificationsPermission(
        launchActivityResult: suspend (Intent) -> ActivityResult
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intent = Intent().apply {
                action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
            }
            launchActivityResult.invoke(intent)
        }
    }

    @SuppressLint("BatteryLife")
    override suspend fun requestBatteryPermission(
        launchActivityResult: suspend (Intent) -> ActivityResult
    ) {
        val intent = Intent().apply {
            action = Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
            data = Uri.parse("$SCHEME_PACKAGE:${context.packageName}")
        }
        launchActivityResult.invoke(intent)
    }

    override fun isNotificationsPermissionGranted(): Boolean =
        isNotificationsPermissionGranted1() && isNotificationsPermissionGranted2()

    private fun isNotificationsPermissionGranted1(): Boolean =
        (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) ||
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

    private fun isNotificationsPermissionGranted2(): Boolean =
        NotificationManagerCompat.from(context).let { notificationManager ->
            val areNotificationsChannelsEnabled = notificationManager.notificationChannelsCompat
                .map { it.importance != NotificationManager.IMPORTANCE_NONE }
                .fold(initial = true) { accumulator, nextItem -> accumulator && nextItem }
            notificationManager.areNotificationsEnabled() && areNotificationsChannelsEnabled
        }

    companion object {
        private const val SCHEME_PACKAGE = "package"
    }
}
