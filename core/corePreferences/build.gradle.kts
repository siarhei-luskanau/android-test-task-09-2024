plugins {
    id("androidLibraryConvention")
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "siarhei.luskanau.android.test.task.core.preferences"
}

dependencies {
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.tink.android)
    ksp(libs.koin.ksp.compiler)
}

ksp {
    arg("KOIN_CONFIG_CHECK", "true")
}
