package siarhei.luskanau.android.test.task.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import org.koin.core.component.KoinComponent
import siarhei.luskanau.android.test.task.navigation.databinding.ActivityNavigationBinding

class NavigationActivity :
    AppCompatActivity(R.layout.activity_navigation),
    KoinComponent {

    private val navController: NavController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
            .navController
    }

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(
            topLevelDestinationIds = setOf(
                siarhei.luskanau.android.test.task.ui.dashboard.R.id.nav_dashboard_fragment,
                siarhei.luskanau.android.test.task.ui.permissions.R.id.nav_permissions_fragment,
                siarhei.luskanau.android.test.task.ui.splash.R.id.nav_splash_fragment
            )
        )
    }

    private val binding by lazy { ActivityNavigationBinding.bind(findViewById(R.id.container)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = KoinFragmentFactory(
            koin = getKoin(),
            appNavigation = AppNavigation(activity = this)
        )
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
}
