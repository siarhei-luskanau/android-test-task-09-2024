package siarhei.luskanau.android.test.task.ui.permissions

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import org.koin.dsl.module

val uiPermissionsModule = module {

    factory { (permissionsNavigationCallback: PermissionsNavigationCallback) ->
        PermissionsFragment { fragment: Fragment ->
            val viewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    @Suppress("UNCHECKED_CAST")
                    return PermissionsViewModelImpl(
                        permissionsNavigationCallback = permissionsNavigationCallback,
                        corePermissions = get()
                    ) as T
                }
            }
            ViewModelProvider(
                fragment as ViewModelStoreOwner,
                viewModelFactory
            )[PermissionsViewModel::class.java]
        }
    }
}
