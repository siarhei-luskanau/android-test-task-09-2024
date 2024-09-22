package siarhei.luskanau.android.test.task.ui.common

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment<T>(private val viewModelProvider: (fragment: Fragment) -> T) :
    Fragment() {

    protected val viewModel: T by lazy { viewModelProvider(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDataSources()
    }

    protected open fun observeDataSources() {
    }
}
