package siarhei.luskanau.android.test.task.navigation

import androidx.navigation.NavController
import siarhei.luskanau.android.test.task.ui.splash.SplashNavigationCallback

class AppNavigation(private val navController: NavController) : SplashNavigationCallback {
    override fun onSplashComplete() {
        navController.navigate(NavRootDirections.actionSplashToDashboard())
    }
}
