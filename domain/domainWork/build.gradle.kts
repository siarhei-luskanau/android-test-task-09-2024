plugins {
    id("androidLibraryConvention")
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "siarhei.luskanau.android.test.task.domain.work"
}

dependencies {
    implementation(project(":domain:domainNotifications"))
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.koin.androidx.workmanager)
    ksp(libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
