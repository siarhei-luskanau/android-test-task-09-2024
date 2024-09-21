rootProject.name = "AndroidTestTask092024"
include(
    ":app",
    ":navigation",
    ":ui:uiCommon",
    ":ui:uiDashboard",
    ":ui:uiSplash"
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
