package siarhei.luskanau.android.test.task.ui.splash

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import org.koin.dsl.module

val uiSplashModule = module {

    factory { (splashNavigationCallback: SplashNavigationCallback) ->
        SplashFragment { fragment: Fragment ->
            val viewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return SplashViewModelImpl(
                        splashNavigationCallback = splashNavigationCallback,
                        corePermissions = get()
                    ) as T
                }
            }
            ViewModelProvider(
                fragment as ViewModelStoreOwner,
                viewModelFactory
            )[SplashViewModel::class.java]
        }
    }
}
