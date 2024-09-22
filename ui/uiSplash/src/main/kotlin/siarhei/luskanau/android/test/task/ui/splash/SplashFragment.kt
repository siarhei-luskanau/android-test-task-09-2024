package siarhei.luskanau.android.test.task.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import siarhei.luskanau.android.test.task.ui.common.BaseFragment
import siarhei.luskanau.android.test.task.ui.splash.databinding.FragmentSplashBinding

class SplashFragment(presenterProvider: (fragment: Fragment) -> SplashViewModel) :
    BaseFragment<SplashViewModel>(presenterProvider) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSplashBinding.inflate(inflater, container, false).root

    override fun observeDataSources() {
        viewModel.onLaunched()
    }
}
