plugins {
    id("androidLibraryConvention")
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.navigation.safeargs.kotlin)
}

android {
    namespace = "siarhei.luskanau.android.test.task.navigation"
}

dependencies {
    implementation(project(":core:coreFormatter"))
    implementation(project(":core:corePermissions"))
    implementation(project(":core:corePreferences"))
    implementation(project(":core:coreStorage"))
    implementation(project(":domain:domainBroadcast"))
    implementation(project(":domain:domainNotifications"))
    implementation(project(":domain:domainWork"))
    implementation(project(":ui:uiCommon"))
    implementation(project(":ui:uiDashboard"))
    implementation(project(":ui:uiPermissions"))
    implementation(project(":ui:uiSplash"))
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.koin.androidx.startup)
    implementation(libs.koin.androidx.workmanager)
    ksp(libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
