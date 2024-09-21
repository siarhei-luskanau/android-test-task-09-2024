plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    runtimeOnly(libs.android.gradle.plugin)
    runtimeOnly(libs.kotlin.gradle.plugin)
}
