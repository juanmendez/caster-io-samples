import info.adavis.plugin.BumpReadMeVersionPlugin
import info.adavis.plugin.BumpReadMeVersionPlugin.Companion.BUMP_VERSION
import info.adavis.plugin.BumpReadMeVersionPlugin.Companion.DISPLAY_VERSION
import info.adavis.plugin.BumpVersion
import info.adavis.plugin.DisplayVersion

apply<BumpReadMeVersionPlugin>()

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        applicationId = "info.adavis.gradleplugin"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(project(":mylibrary"))
    implementation("androidx.appcompat:appcompat:1.1.0")
    testImplementation("junit:junit:4.12")
}

tasks.getByName<DisplayVersion>(DISPLAY_VERSION) {
    versionName = android.defaultConfig.versionName
}

tasks.getByName<BumpVersion>(BUMP_VERSION) {
    overrideContent = "app:"
    versionName = android.defaultConfig.versionName
}
