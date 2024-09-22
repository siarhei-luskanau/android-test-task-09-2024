package siarhei.luskanau.android.test.task.ui.splash

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

internal class SplashViewModelImpl(private val splashNavigationCallback: SplashNavigationCallback) :
    SplashViewModel() {

    override fun onLaunched() {
        viewModelScope.launch {
            delay(duration = 500.milliseconds)
            splashNavigationCallback.onSplashComplete()
        }
    }
}
