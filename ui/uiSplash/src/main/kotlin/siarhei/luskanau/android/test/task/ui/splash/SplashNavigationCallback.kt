package siarhei.luskanau.android.test.task.ui.splash

interface SplashNavigationCallback {
    fun onSplashComplete(splashDestination: SplashDestination)
}

sealed interface SplashDestination
data object Permissions : SplashDestination
data object Dashboard : SplashDestination
