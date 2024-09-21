package siarhei.luskanau.android.test.task.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import siarhei.luskanau.android.test.task.ui.common.BaseFragment
import siarhei.luskanau.android.test.task.ui.dashboard.databinding.FragmentDashboardBinding

class DashboardFragment(
    presenterProvider: (fragment: Fragment) -> DashboardViewModel
) : BaseFragment<DashboardViewModel>(presenterProvider) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDashboardBinding.inflate(inflater, container, false).root

    override fun observeDataSources() {
        viewModel.onLaunched()
    }
}
