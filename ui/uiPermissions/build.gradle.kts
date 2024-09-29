plugins {
    id("androidLibraryConvention")
    alias(libs.plugins.navigation.safeargs.kotlin)
}

android {
    namespace = "siarhei.luskanau.android.test.task.ui.permissions"
}

dependencies {
    implementation(project(":core:corePermissions"))
    implementation(project(":ui:uiCommon"))
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}
