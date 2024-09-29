package siarhei.luskanau.android.test.task.core.permissions

import android.content.Intent
import androidx.activity.result.ActivityResult

interface CorePermissions {
    fun isAllPermissionsGranted(): Boolean
    fun isNotificationsPermissionGranted(): Boolean
    fun isBatteryUsagePermissionGranted(): Boolean
    suspend fun requestNotificationsPermission(
        launchActivityResult: suspend (Intent) -> ActivityResult
    )
    suspend fun requestBatteryPermission(launchActivityResult: suspend (Intent) -> ActivityResult)
}
