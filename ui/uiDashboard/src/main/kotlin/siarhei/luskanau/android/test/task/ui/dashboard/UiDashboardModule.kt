package siarhei.luskanau.android.test.task.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import org.koin.dsl.module

val uiDashboardModule = module {

    factory {
        DashboardFragment { fragment: Fragment ->
            val viewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return DashboardViewModelImpl(
                        appPreference = get(),
                        coreFormatter = get(),
                        coreStorage = get()
                    ) as T
                }
            }
            ViewModelProvider(
                fragment as ViewModelStoreOwner,
                viewModelFactory
            )[DashboardViewModel::class.java]
        }
    }
}
