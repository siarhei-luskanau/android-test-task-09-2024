package siarhei.luskanau.android.test.task.ui.workmanager

import android.widget.LinearLayout.VERTICAL
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.launch
import siarhei.luskanau.android.test.task.ui.common.BaseFragment
import siarhei.luskanau.android.test.task.ui.common.FragmentViewBinding
import siarhei.luskanau.android.test.task.ui.workmanager.databinding.FragmentWorkManagerBinding

class WorkManagerFragment(viewModelProvider: (fragment: Fragment) -> WorkManagerViewModel) :
    BaseFragment<WorkManagerViewModel, FragmentWorkManagerBinding>(
        layoutId = R.layout.fragment_work_manager,
        viewModelProvider = viewModelProvider
    ) {

    override val fragmentViewBinding = FragmentViewBinding(
        bind = { view -> FragmentWorkManagerBinding.bind(view) },
        initViews = { initViews(it) }
    )

    private fun initViews(binding: FragmentWorkManagerBinding) {
        binding.apply {
            val adapter = WorkInfoAdapter()
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    binding.recyclerView.context,
                    VERTICAL
                )
            )
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.getWorkStatusListFlow(binding.root.context).collect {
                        adapter.submitList(it)
                    }
                }
            }
        }
    }
}
