package siarhei.luskanau.android.test.task.ui.workmanager

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import org.koin.dsl.module

val uiWorkManager = module {

    factory { (workManagerNavigationCallback: WorkManagerNavigationCallback) ->
        WorkManagerFragment { fragment: Fragment ->
            val viewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return WorkManagerViewModelImpl(
                        workManagerNavigationCallback = workManagerNavigationCallback
                    ) as T
                }
            }
            ViewModelProvider(
                fragment as ViewModelStoreOwner,
                viewModelFactory
            )[WorkManagerViewModel::class.java]
        }
    }
}
