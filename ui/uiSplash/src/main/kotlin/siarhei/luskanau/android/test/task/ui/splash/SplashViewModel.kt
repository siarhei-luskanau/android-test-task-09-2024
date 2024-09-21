package siarhei.luskanau.android.test.task.ui.splash

import androidx.lifecycle.ViewModel

abstract class SplashViewModel : ViewModel() {
    abstract fun onLaunched()
}