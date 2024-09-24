package siarhei.luskanau.android.test.task.ui.common

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding>(
    @LayoutRes layoutId: Int,
    private val viewModelProvider: (fragment: Fragment) -> VM
) : Fragment(layoutId) {

    protected val viewModel: VM by lazy { viewModelProvider(this) }

    protected abstract val fragmentViewBinding: FragmentViewBinding<VB>

    protected val fragmentBinding: VB?
        get() = fragmentViewBinding.get()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                owner.lifecycle.removeObserver(this)
            }
        })
        fragmentViewBinding.onViewCreated(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentViewBinding.onDestroyView()
    }
}
