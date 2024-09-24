* Comment: App architecture was prepared before the test. There were Koin DI, navigation,
  multi-module splitting.
* Comment: App module is responsible for signing, flavours and other deployment settings.
* Comment: Navigation module is responsible for building navigation graph and setting
  FragmentFactory with DI declared Fragment constructors.
* Comment: Core modules implements one functionality and shouldn't uses other modules.
* Comment: Domain modules uses Core modules to implement business logic.
* Comment: Apk files (debug and release variants) is uploaded to GitHub actions
  artifacts https://github.com/siarhei-luskanau/android-test-task-09-2024/actions/workflows/actionsBuild.yml.

* Comment: CoreStorage is implemented with Room and aggregation query.
* ToDo: Implement CoreStorage with Flow approach. This will take possibility to update UI
  immediately after Boot event is triggered.
* ToDo: Use java.time.LocalDateTime with desugaring for API level 24. We can use kotlinx-datetime
  library.
* ToDo: Add Unit tests to check CoreFormatterImpl formatting logic.
* Comment: CorePreferences is implemented using DataStore to store user settings, related the “Total
  dismissals allowed” and “Interval between dismissals” values.

* ToDo: Add Jetpack WorkManager Jetpack Startup library to initialize WorkManager with Koin DI.
  Workers should contain domain dependencies in constructor.
* ToDo: App Notification should be updated using Periodic Worker of WorkManager.

