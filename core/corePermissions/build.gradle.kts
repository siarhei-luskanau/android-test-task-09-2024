plugins {
    id("androidLibraryConvention")
    alias(libs.plugins.google.ksp)
}

android {
    namespace = "siarhei.luskanau.android.test.task.core.permissions"
}

dependencies {
    ksp(libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}