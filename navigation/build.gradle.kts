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
    implementation(project(":core:coreStorage"))
    implementation(project(":ui:uiCommon"))
    implementation(project(":ui:uiDashboard"))
    implementation(project(":ui:uiSplash"))
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    ksp(libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
