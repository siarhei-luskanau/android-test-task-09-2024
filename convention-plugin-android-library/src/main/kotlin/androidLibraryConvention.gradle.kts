val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = libs.findVersion("build-android-compileSdk").get().requiredVersion.toInt()
    defaultConfig {
        minSdk = libs.findVersion("build-android-minSdk").get().requiredVersion.toInt()
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(
            libs.findVersion("build-javaVersion").get().requiredVersion
        )
        targetCompatibility = JavaVersion.valueOf(
            libs.findVersion("build-javaVersion").get().requiredVersion
        )
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = libs.findVersion("build-jvmTarget").get().requiredVersion
    }
}

dependencies {
    coreLibraryDesugaring(libs.findLibrary("android-desugar").get())
    implementation(libs.findLibrary("android-material").get())
    implementation(libs.findLibrary("androidx-fragment-ktx").get())
    implementation(libs.findLibrary("koin-annotations").get())
    implementation(libs.findLibrary("koin-core").get())
    implementation(libs.findLibrary("kotlinx-coroutines-core").get())
    implementation(libs.findLibrary("kotlinx-datetime").get())
}
