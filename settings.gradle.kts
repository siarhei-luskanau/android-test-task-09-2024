rootProject.name = "BootCounterApp"
include(
    ":app",
    ":core:coreFormatter",
    ":core:corePermissions",
    ":core:corePreferences",
    ":core:coreStorage",
    ":domain:domainBroadcast",
    ":domain:domainNotifications",
    ":domain:domainWork",
    ":navigation",
    ":ui:uiCommon",
    ":ui:uiDashboard",
    ":ui:uiPermissions",
    ":ui:uiSplash",
    ":ui:uiWorkManager"
)

pluginManagement {
    includeBuild("convention-plugin-android-library")
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
