package siarhei.luskanau.android.test.task.navigation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import siarhei.luskanau.android.test.task.core.formatter.CoreFormatterModule
import siarhei.luskanau.android.test.task.core.storage.CoreStorageModule
import siarhei.luskanau.android.test.task.ui.dashboard.uiDashboardModule
import siarhei.luskanau.android.test.task.ui.splash.uiSplashModule

class AppApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@AppApplication)
            // Load modules
            modules(
                CoreFormatterModule().module,
                CoreStorageModule().module,
                uiDashboardModule,
                uiSplashModule,
            )
        }
    }
}