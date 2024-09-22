plugins {
    id("androidLibraryConvention")
}

android {
    namespace = "siarhei.luskanau.android.test.task.domain.broadcast"
}

dependencies {
    implementation(project(":core:coreStorage"))
}
