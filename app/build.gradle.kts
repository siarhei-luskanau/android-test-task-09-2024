plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "siarhei.luskanau.android.test.task.app"
    compileSdk = libs.versions.build.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.build.android.minSdk.get().toInt()
        targetSdk = libs.versions.build.android.targetSdk.get().toInt()
        applicationId = "siarhei.luskanau.android.test.task.app"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.valueOf(libs.versions.build.javaVersion.get())
        targetCompatibility = JavaVersion.valueOf(libs.versions.build.javaVersion.get())
    }
    kotlinOptions {
        jvmTarget = libs.versions.build.jvmTarget.get()
    }
}

dependencies {
    implementation(project(":navigation"))
    implementation(project(":ui:uiDashboard"))
    implementation(project(":ui:uiSplash"))
}
