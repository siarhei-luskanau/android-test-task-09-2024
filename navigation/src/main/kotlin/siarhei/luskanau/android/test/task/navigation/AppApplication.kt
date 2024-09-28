package siarhei.luskanau.android.test.task.navigation

import android.annotation.SuppressLint
import android.app.Application
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.androix.startup.KoinStartup.onKoinStartup
import org.koin.core.component.KoinComponent
import org.koin.dsl.module
import org.koin.ksp.generated.module
import siarhei.luskanau.android.test.task.core.formatter.CoreFormatterModule
import siarhei.luskanau.android.test.task.core.preferences.CorePreferencesModule
import siarhei.luskanau.android.test.task.core.storage.CoreStorageModule
import siarhei.luskanau.android.test.task.domain.notifications.AppNotificationService
import siarhei.luskanau.android.test.task.domain.work.domainWorkModule
import siarhei.luskanau.android.test.task.ui.dashboard.uiDashboardModule
import siarhei.luskanau.android.test.task.ui.splash.uiSplashModule

class AppApplication :
    Application(),
    KoinComponent {

    init {
        // Use AndroidX Startup for Koin
        onKoinStartup {
            androidContext(this@AppApplication)
            modules(
                CoreFormatterModule().module,
                CorePreferencesModule().module,
                CoreStorageModule().module,
                domainWorkModule,
                uiDashboardModule,
                uiSplashModule,
                module {
                    single<AppNotificationService> {
                        AppNotificationServiceImpl(
                            context = get(),
                            coreStorage = get(),
                            coreFormatter = get()
                        )
                    }
                }
            )
            workManagerFactory()
        }
    }

    @SuppressLint("MissingPermission")
    override fun onCreate() {
        super.onCreate()

        val appNotificationService: AppNotificationService = getKoin().get()
        CoroutineScope(Dispatchers.IO).launch {
            NotificationManagerCompat.from(applicationContext).notify(
                appNotificationService.getBootInfoNotificationId(),
                appNotificationService.getBootInfoNotification()
            )
        }
    }
}
