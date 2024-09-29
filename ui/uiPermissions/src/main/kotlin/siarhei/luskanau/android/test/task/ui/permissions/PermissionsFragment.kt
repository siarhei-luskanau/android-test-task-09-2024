package siarhei.luskanau.android.test.task.ui.permissions

import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import siarhei.luskanau.android.test.task.core.permissions.ContinuationStartActivityInput
import siarhei.luskanau.android.test.task.ui.common.BaseFragment
import siarhei.luskanau.android.test.task.ui.common.FragmentViewBinding
import siarhei.luskanau.android.test.task.ui.permissions.databinding.FragmentPermissionsBinding

class PermissionsFragment(viewModelProvider: (fragment: Fragment) -> PermissionsViewModel) :
    BaseFragment<PermissionsViewModel, FragmentPermissionsBinding>(
        layoutId = R.layout.fragment_permissions,
        viewModelProvider = viewModelProvider
    ) {

    private var activityResultLauncher: ActivityResultLauncher<ContinuationStartActivityInput>? =
        null

    override val fragmentViewBinding = FragmentViewBinding(
        bind = { view -> FragmentPermissionsBinding.bind(view) }
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityResultLauncher = registerForStartActivityResult()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.onRequestClicked(
                    launchActivityResult(requireNotNull(activityResultLauncher))
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResumed()
    }
}
