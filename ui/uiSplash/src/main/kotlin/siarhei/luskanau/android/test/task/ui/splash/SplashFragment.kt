package siarhei.luskanau.android.test.task.ui.splash

import androidx.fragment.app.Fragment
import siarhei.luskanau.android.test.task.ui.common.BaseFragment
import siarhei.luskanau.android.test.task.ui.common.FragmentViewBinding
import siarhei.luskanau.android.test.task.ui.splash.databinding.FragmentSplashBinding

class SplashFragment(viewModelProvider: (fragment: Fragment) -> SplashViewModel) :
    BaseFragment<SplashViewModel, FragmentSplashBinding>(
        layoutId = R.layout.fragment_splash,
        viewModelProvider = viewModelProvider
    ) {

    override val fragmentViewBinding = FragmentViewBinding(
        bind = { view -> FragmentSplashBinding.bind(view) }
    )

    override fun onResume() {
        super.onResume()
        viewModel.onLaunched()
    }
}
