package siarhei.luskanau.android.test.task.ui.splash

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import siarhei.luskanau.android.test.task.core.permissions.CorePermissions

internal class SplashViewModelImpl(
    private val splashNavigationCallback: SplashNavigationCallback,
    private val corePermissions: CorePermissions
) : SplashViewModel() {

    override fun onLaunched() {
        viewModelScope.launch {
            splashNavigationCallback.onSplashComplete(
                if (corePermissions.isAllPermissionsGranted()) {
                    Dashboard
                } else {
                    Permissions
                }
            )
        }
    }
}
