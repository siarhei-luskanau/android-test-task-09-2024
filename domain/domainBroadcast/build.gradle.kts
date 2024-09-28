plugins {
    id("androidLibraryConvention")
}

android {
    namespace = "siarhei.luskanau.android.test.task.domain.broadcast"
}

dependencies {
    implementation(project(":domain:domainWork"))
    implementation(project(":core:coreStorage"))
}
