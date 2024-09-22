plugins {
    id("androidLibraryConvention")
    alias(libs.plugins.navigation.safeargs.kotlin)
}

android {
    namespace = "siarhei.luskanau.android.test.task.ui.dashboard"
}

dependencies {
    implementation(project(":core:coreFormatter"))
    implementation(project(":core:coreStorage"))
    implementation(project(":ui:uiCommon"))
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}
