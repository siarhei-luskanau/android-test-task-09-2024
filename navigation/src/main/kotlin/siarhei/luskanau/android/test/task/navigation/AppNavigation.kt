package siarhei.luskanau.android.test.task.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import siarhei.luskanau.android.test.task.ui.dashboard.DashboardNavigationCallback
import siarhei.luskanau.android.test.task.ui.permissions.PermissionsNavigationCallback
import siarhei.luskanau.android.test.task.ui.splash.Dashboard
import siarhei.luskanau.android.test.task.ui.splash.Permissions
import siarhei.luskanau.android.test.task.ui.splash.SplashDestination
import siarhei.luskanau.android.test.task.ui.splash.SplashNavigationCallback
import siarhei.luskanau.android.test.task.ui.workmanager.WorkManagerNavigationCallback

class AppNavigation(private val activity: FragmentActivity) :
    DashboardNavigationCallback,
    PermissionsNavigationCallback,
    SplashNavigationCallback,
    WorkManagerNavigationCallback {

    private fun getNavController(): NavController =
        (activity.supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
            .navController

    override fun onSplashComplete(splashDestination: SplashDestination) {
        when (splashDestination) {
            Dashboard -> getNavController().navigate(NavRootDirections.actionSplashToDashboard())
            Permissions -> getNavController().navigate(
                NavRootDirections.actionSplashToPermissions()
            )
        }
    }

    override fun onPermissionsComplete() {
        getNavController().navigate(NavRootDirections.actionPermissionsToDashboard())
    }

    override fun onWorkManagerComplete() {
        getNavController().navigateUp()
    }

    override fun navigateToWorkManager() {
        getNavController().navigate(NavRootDirections.actionDashboardToWorkManager())
    }
}
