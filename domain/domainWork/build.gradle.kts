plugins {
    id("androidLibraryConvention")
}

android {
    namespace = "siarhei.luskanau.android.test.task.domain.work"
}

dependencies {
    implementation(project(":domain:domainNotifications"))
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.koin.androidx.workmanager)
}
