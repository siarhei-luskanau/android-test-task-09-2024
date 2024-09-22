package siarhei.luskanau.android.test.task.navigation

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import siarhei.luskanau.android.test.task.ui.splash.SplashNavigationCallback

class AppNavigation(private val activity: FragmentActivity) : SplashNavigationCallback {

    private fun getNavController(): NavController =
        (activity.supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
            .navController

    override fun onSplashComplete() {
        getNavController().navigate(NavRootDirections.actionSplashToDashboard())
    }
}
