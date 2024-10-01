package siarhei.luskanau.android.test.task.navigation

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.androix.startup.KoinStartup.onKoinStartup
import org.koin.core.component.KoinComponent
import org.koin.dsl.module
import org.koin.ksp.generated.module
import siarhei.luskanau.android.test.task.core.formatter.CoreFormatterModule
import siarhei.luskanau.android.test.task.core.permissions.CorePermissionsModule
import siarhei.luskanau.android.test.task.core.preferences.CorePreferencesModule
import siarhei.luskanau.android.test.task.core.storage.CoreStorageModule
import siarhei.luskanau.android.test.task.domain.notifications.AppNotificationService
import siarhei.luskanau.android.test.task.domain.work.DomainWorkModule
import siarhei.luskanau.android.test.task.domain.work.WorkService
import siarhei.luskanau.android.test.task.ui.dashboard.uiDashboardModule
import siarhei.luskanau.android.test.task.ui.permissions.uiPermissionsModule
import siarhei.luskanau.android.test.task.ui.splash.uiSplashModule
import siarhei.luskanau.android.test.task.ui.workmanager.uiWorkManager

class AppApplication :
    Application(),
    KoinComponent {

    init {
        // Use AndroidX Startup for Koin
        onKoinStartup {
            androidContext(this@AppApplication)
            modules(
                CoreFormatterModule().module,
                CorePermissionsModule().module,
                CorePreferencesModule().module,
                CoreStorageModule().module,
                DomainWorkModule().module,
                uiDashboardModule,
                uiPermissionsModule,
                uiSplashModule,
                uiWorkManager,
                module {
                    single<AppNotificationService> {
                        AppNotificationServiceImpl(
                            context = androidContext(),
                            coreStorage = get(),
                            coreFormatter = get()
                        )
                    }
                }
            )
            workManagerFactory()
        }
    }

    override fun onCreate() {
        super.onCreate()
        val workService: WorkService = getKoin().get()
        workService.onBootEventReceive()
    }
}
