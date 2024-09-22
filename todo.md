* Comment: App architecture was prepared before the test. There were Koin DI, navigation,
  multi-module splitting.
* Comment: App module is responsible for signing, flavours and other deployment settings.
* Comment: Navigation module is responsible for building navigation graph and setting
  FragmentFactory with DI declared Fragment constructors.
* Comment: Core modules implements one functionality and shouldn't uses other modules.
* Comment: Domain modules uses Core modules to implement business logic.
* Comment: Apk files (debug and release variants) is uploads to GitHub actions
  artifacts https://github.com/siarhei-luskanau/android-test-task-09-2024/actions/workflows/actionsBuild.yml.

* ToDo: Implement storage with Room and aggregation query.
* ToDo: Use java.time.LocalDateTime with desugaring for API level 24. We can use kotlinx-datetime
  library.
* ToDo: Add Unit tests to check CoreFormatterImpl formatting logic. 



