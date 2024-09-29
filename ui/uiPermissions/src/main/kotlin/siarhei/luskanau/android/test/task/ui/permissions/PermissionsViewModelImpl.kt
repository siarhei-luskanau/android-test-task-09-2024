package siarhei.luskanau.android.test.task.ui.permissions

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import siarhei.luskanau.android.test.task.core.permissions.CorePermissions

internal class PermissionsViewModelImpl(
    private val permissionsNavigationCallback: PermissionsNavigationCallback,
    private val corePermissions: CorePermissions
) : PermissionsViewModel() {

    override fun onResumed() {
        viewModelScope.launch {
            if (corePermissions.isAllPermissionsGranted()) {
                permissionsNavigationCallback.onPermissionsComplete()
            }
        }
    }

    override fun onRequestClicked(launchActivityResult: suspend (Intent) -> ActivityResult) {
        viewModelScope.launch {
            when {
                !corePermissions.isNotificationsPermissionGranted() ->
                    corePermissions.requestNotificationsPermission(launchActivityResult)
                !corePermissions.isBatteryUsagePermissionGranted() ->
                    corePermissions.requestBatteryPermission(launchActivityResult)
            }
        }
    }
}
