package siarhei.luskanau.android.test.task.ui.splash

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import kotlin.time.Duration.Companion.seconds

@Factory
internal class SplashViewModelImpl(
    private val splashNavigationCallback: SplashNavigationCallback
) : SplashViewModel() {
    override fun onLaunched() {
        viewModelScope.launch {
            delay(duration = 3.seconds)
            splashNavigationCallback.onSplashComplete()
        }
    }
}
