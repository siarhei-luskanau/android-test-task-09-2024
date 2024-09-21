package siarhei.luskanau.android.test.task.navigation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.module
import siarhei.luskanau.android.test.task.navigation.databinding.ActivityNavigationBinding
import siarhei.luskanau.android.test.task.ui.dashboard.uiDashboardModule
import siarhei.luskanau.android.test.task.ui.splash.SplashNavigationCallback
import siarhei.luskanau.android.test.task.ui.splash.uiSplashModule

class NavigationActivity : AppCompatActivity(R.layout.activity_navigation) {

    private val koin: Koin by lazy {
        startKoin {
            modules(
                uiDashboardModule,
                uiSplashModule,
                module {
                    single<Context> { applicationContext }
                    single { AppNavigation(navController = navController) }
                    single<SplashNavigationCallback> { get<AppNavigation>() }
                },
            )
        }.koin
    }


    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
            .navController
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(navController.graph)
    }

    private val binding by lazy { ActivityNavigationBinding.bind(findViewById(R.id.container)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = KoinFragmentFactory(koin = koin)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}
