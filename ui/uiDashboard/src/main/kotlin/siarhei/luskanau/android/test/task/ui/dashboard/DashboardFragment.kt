package siarhei.luskanau.android.test.task.ui.dashboard

import android.text.Editable
import android.text.TextWatcher
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import siarhei.luskanau.android.test.task.ui.common.BaseFragment
import siarhei.luskanau.android.test.task.ui.common.FragmentViewBinding
import siarhei.luskanau.android.test.task.ui.dashboard.databinding.FragmentDashboardBinding

class DashboardFragment(viewModelProvider: (fragment: Fragment) -> DashboardViewModel) :
    BaseFragment<DashboardViewModel, FragmentDashboardBinding>(
        layoutId = R.layout.fragment_dashboard,
        viewModelProvider = viewModelProvider
    ) {

    private val totalDismissalsAllowedTextWatcher: TextWatcher by lazy {
        object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onTotalDismissalsAllowedChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun afterTextChanged(s: Editable?) = Unit
        }
    }

    private val intervalBetweenDismissalsTextWatcher: TextWatcher by lazy {
        object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onIntervalBetweenDismissalsChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun afterTextChanged(s: Editable?) = Unit
        }
    }

    override val fragmentViewBinding = FragmentViewBinding(
        bind = { view -> FragmentDashboardBinding.bind(view) },
        initViews = { initViews(it) },
        clearViews = { clearViews(it) }
    )

    private fun initViews(binding: FragmentDashboardBinding) {
        binding.apply {
            totalDismissalsAllowedEditText.addTextChangedListener(totalDismissalsAllowedTextWatcher)
            intervalBetweenDismissalsEditText.addTextChangedListener(
                intervalBetweenDismissalsTextWatcher
            )
            workManagerButton.setOnClickListener {
                viewModel.onWorkManagerButtonClicked()
            }
            lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewModel.viewState.collect { viewState ->
                        loadingProgressContainer.isVisible = viewState.isLoading
                        workManagerButton.isVisible = !viewState.isLoading
                        textBootInfo.isVisible = !viewState.isLoading
                        if (viewState.totalDismissalsAllowed !=
                            totalDismissalsAllowedEditText.text.toString()
                        ) {
                            totalDismissalsAllowedEditText.also {
                                it.setText(viewState.totalDismissalsAllowed)
                                it.setSelection(it.length())
                                it.requestFocus()
                            }
                        }
                        totalDismissalsAllowedInputLayout.error =
                            viewState.totalDismissalsAllowedError
                        totalDismissalsAllowedInputLayout.isVisible = !viewState.isLoading
                        if (viewState.intervalBetweenDismissals !=
                            intervalBetweenDismissalsEditText.text.toString()
                        ) {
                            intervalBetweenDismissalsEditText.also {
                                it.setText(viewState.intervalBetweenDismissals)
                                it.setSelection(it.length())
                                it.requestFocus()
                            }
                        }
                        intervalBetweenDismissalsInputLayout.error =
                            viewState.intervalBetweenDismissalsError
                        intervalBetweenDismissalsInputLayout.isVisible = !viewState.isLoading
                        textBootInfo.text = viewState.bootInfo
                    }
                }
            }
        }
        viewModel.onLaunched()
    }

    private fun clearViews(binding: FragmentDashboardBinding) {
        binding.apply {
            totalDismissalsAllowedEditText.removeTextChangedListener(
                totalDismissalsAllowedTextWatcher
            )
            intervalBetweenDismissalsEditText.removeTextChangedListener(
                intervalBetweenDismissalsTextWatcher
            )
            workManagerButton.setOnClickListener(null)
        }
    }
}
