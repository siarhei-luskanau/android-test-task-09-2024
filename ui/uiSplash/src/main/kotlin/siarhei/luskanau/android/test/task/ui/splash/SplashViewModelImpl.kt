package siarhei.luskanau.android.test.task.ui.splash

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.annotation.Factory
import siarhei.luskanau.android.test.task.core.storage.CoreStorage
import kotlin.time.Duration.Companion.milliseconds

@Factory
internal class SplashViewModelImpl(
    private val coreStorage: CoreStorage,
    private val splashNavigationCallback: SplashNavigationCallback
) : SplashViewModel() {

    override fun onLaunched() {
        viewModelScope.launch {
            coreStorage.saveBootEvent()
            delay(duration = 500.milliseconds)
            splashNavigationCallback.onSplashComplete()
        }
    }
}
