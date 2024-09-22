package siarhei.luskanau.android.test.task.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import siarhei.luskanau.android.test.task.ui.common.BaseFragment
import siarhei.luskanau.android.test.task.ui.dashboard.databinding.FragmentDashboardBinding

class DashboardFragment(
    presenterProvider: (fragment: Fragment) -> DashboardViewModel
) : BaseFragment<DashboardViewModel>(presenterProvider) {

    private var fragmentBinding: FragmentDashboardBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDashboardBinding.inflate(inflater, container, false)
        .also { fragmentBinding = it }
        .root

    override fun observeDataSources() {
        viewModel.onLaunched()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.viewState.collect { viewState ->
                    fragmentBinding?.loadingProgressContainer?.isVisible = viewState.isLoading
                    fragmentBinding?.textBootInfo?.text = viewState.bootInfo
                }
            }
        }
    }
}
